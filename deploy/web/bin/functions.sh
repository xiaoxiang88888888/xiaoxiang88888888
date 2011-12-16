#!/bin/bash

if [ -f /etc/sysconfig/init ]; then
    . /etc/sysconfig/init
else
  SETCOLOR_SUCCESS=
  SETCOLOR_FAILURE=
  SETCOLOR_WARNING=
  SETCOLOR_NORMAL=
fi

success () {
    if [ "$BOOTUP" = "color" ]; then
        $SETCOLOR_SUCCESS
        if [ -z "$*" ]; then
            echo "ok"
        else
            echo -e "$*"
        fi
        $SETCOLOR_NORMAL
    else
        if [ -z "$*" ]; then
            echo "ok"
        else
            echo -e "$*"
        fi
    fi
    return
}

failed () {
    if [ "$BOOTUP" = "color" ]; then
        $SETCOLOR_FAILURE
        if [ -z "$*" ]; then
            echo "failed"
        else
            echo -e "$*"
        fi
        $SETCOLOR_NORMAL
    else
        if [ -z "$*" ]; then
            echo "failed"
        else
            echo -e "$*"
        fi
    fi
    return 1
}

warning () {
    if [ "$BOOTUP" = "color" ]; then
        $SETCOLOR_WARNING
        if [ -z "$*" ]; then
            echo "warning"
        else
            echo -e "$*"
        fi
        $SETCOLOR_NORMAL
    else
        if [ -z "$*" ]; then
            echo "warning"
        else
            echo -e "$*"
        fi
    fi
    return
}

exit_root () {
    if [ `id -u` = 0 ]; then
    	failed "ERROR! root (the superuser) can't run this script."
    	exit 1
    fi
}

remove_ipcs () {
    pid=$*
    who=`whoami`
    if [ -z $pid ]; then
        warning "Warning: Removed share memory and semaphore--gived pid is NULL"
        return
    fi
    
    shmids=`ipcs -mp |grep -P "\d+[ ]+$who[ ]+$pid" |awk '{print $1}'`
    for id in $shmids ; do
        ipcrm -m $id
    done

    shsemids=`ipcs -sp |grep -P "\d+[ ]+$who[ ]+$pid" |awk '{print $1}'`
    for id in $shsemids ; do
        ipcrm -s $id
    done
}

get_pid() {	
	STR=$1 ##指定查询字符串
    if $cygwin; then
        JAVA_CMD="$JAVA_HOME\bin\java"
        JAVA_CMD=`cygpath --path --unix $JAVA_CMD`
        JAVA_PID=`ps |grep $JAVA_CMD |awk '{print $1}'`
    else
        STR=`ps -C java -f --width 1000 | grep "$STR"`
        if [ ! -z "$STR" ]; then
            JAVA_PID=`ps -C java -f --width 1000|grep "$STR"|grep -v grep|awk '{print $2}'`
        fi
    fi
    echo $JAVA_PID;
}

check_monitor_ok() {
	##检查monitor
	CHECK_STARTUP_URL="http://127.0.0.1:${APP_PORT}/home/ok.htm"
	STARTUP_SUCCESS_MSG="ok"
	
	if  $production_mode ; then 
		## 如果是生产模式,必须是check ok
		while [ true ]; do
			COUNT=`curl -m 3 -s $CHECK_STARTUP_URL | grep -ic "$STARTUP_SUCCESS_MSG"`
	        if [ $COUNT -ge 1 ]; then                 
	             break
	        fi
		    sleep 3
	    done
		success "Oook!"
	else ## 测试/开发环境,30次重试机会
		LOOPS=0
		while [ $LOOPS -lt 30 ]; do
			COUNT=`curl -m 3 -s $CHECK_STARTUP_URL | wc -l`
	        if [ $COUNT -ge 1 ]; then                 
	             break
	        fi
	        let LOOPS=LOOPS+1
		    sleep 3
	    done
	    COUNT=`curl -m 3 -s $CHECK_STARTUP_URL | grep -ic "$STARTUP_SUCCESS_MSG" `
	    if [ $COUNT -lt 1 ] ; then
	    	warning "check [$CHECK_STARTUP_URL] is failed"
	    else
			success "Oook!"
	    fi
	fi
}
# ==========================================================================
# 						start/stop  (jetty,httpd)
# ==========================================================================

prepare_env() {
	cd `dirname $0`
	BASE_BIN_DIR=`pwd`
	export  LANG=zh_CN.GB18030
	# public env
	. $BASE_BIN_DIR/base_env.sh
	
	# private env
	if [ -f $BASE_BIN_DIR/env.sh ]; then
		. $BASE_BIN_DIR/env.sh
	fi
	
	HOST_NAME=`hostname`
	LOG_DIR=$OUTPUT_HOME/logs
	##以前的日志保存
	LOGS_SAVED="$LOG_DIR/logs_saved"
	TIMESTAMP=`date +%Y_%m_%d_%H_%M`
	KILLWS_LOG=$LOG_DIR/killws.log
	#backup logs
	if [ ! -d $LOGS_SAVED ]; then
	   mkdir -p $LOGS_SAVED
	fi
	
	if [ -f $KILLWS_LOG ]; then
   		mv -f $KILLWS_LOG $LOGS_SAVED/killws.log.log.$TIMESTAMP
	fi
	
	export BASE_BIN_DIR HOST_NAME LOG_DIR LOGS_SAVED TIMESTAMP
}

start_jetty () {
	JETTY_CHECK_LOG="$LOG_DIR/jetty_stdout.log"
	#jetty server
	if [ -f $JETTY_CHECK_LOG ]; then
	    mv -f $JETTY_CHECK_LOG $LOGS_SAVED/jetty_stdout.log.$TIMESTAMP
	fi
	
	##check if started before
	STR=''
	if $cygwin; then
	    JAVA_CMD="$JAVA_HOME\bin\java"
	    JAVA_CMD=`cygpath --path --unix $JAVA_CMD`
	    STR=`ps | grep "$JAVA_CMD"`		
	else
	    STR=`ps -C java -f --width 1000 | grep "$JETTY_SERVER_HOME"`
	fi
	if [ ! -z "$STR" ]; then
	        echo "Jetty server already started"
	        exit;
	fi
	
	## start jetty 
	echo -e "$HOST_NAME: starting jetty ...\c"
	$BASE_BIN_DIR/jettyctl.sh start 1>$JETTY_CHECK_LOG 2>$JETTY_CHECK_LOG &
	
	## check monitor
	check_monitor_ok
}

stop_jetty() {
	JETTY_JAVA_PID=`get_pid "$JETTY_SERVER_HOME"`
    if [ ! -z "$JETTY_JAVA_PID" ] ; then
	    echo -e "$HOST_NAME: stopping jetty ... "
	    $BASE_BIN_DIR/jettyctl.sh stop >> $KILLWS_LOG 2>&1
	    ##check if stop failed ,do kill -9
	    JETTY_JAVA_PID=`get_pid "$JETTY_SERVER_HOME"`
	    if [ ! -z "$JETTY_JAVA_PID" ] ; then
	        echo -e "kill java process $JETTY_JAVA_PID ..."
	        /bin/kill -9 $JETTY_JAVA_PID >> $KILLWS_LOG 2>&1
	    fi
	    success "Oook!"
	else
	   warning "$HOST_NAME: jetty not running, who care?"
	fi
}

start_httpd() {
	##检查的日志文件
	APACHE_LOG="$LOG_DIR/apache_error.log"
	
	if [ -f $APACHE_LOG ]; then
    	mv -f $APACHE_LOG $LOGS_SAVED/apache_error.log.$TIMESTAMP
	fi
	
	##启动apache
	if ! $cygwin; then
	    echo -e "$HOST_NAME: starting httpd ..."
	    chmod +x $BASE_BIN_DIR/apachectl
	    $BASE_BIN_DIR/apachectl restart > $APACHE_LOG 2>&1 &
	    success "Oook!"
	    echo -e "$HOSTNAME: reloadws_alone done!"
	else
	    echo -e "$HOSTNAME: Cygwin mode, skip start apache!"
	fi
}

stop_httpd() {
	APACHE_PID_FILE=$OUTPUT_HOME/logs/httpd.pid
	if [ -f $APACHE_PID_FILE ] ; then
		httpd_pid=`cat $APACHE_PID_FILE`
	    echo -e "$HOST_NAME: stopping httpd ...\c"
	    chmod +x $BASE_BIN_DIR/apachectl
	    $BASE_BIN_DIR/apachectl stop >> $KILLWS_LOG 2>&1
	    sleep 5
	    killall -9 httpd >> $KILLWS_LOG 2>&1
	    killall -9 hummockclient >> $KILLWS_LOG 2>&1
	    ## 删除共享内存
	    remove_ipcs $httpd_pid
	    success "Oook!"
	else
	    warning "$HOST_NAME: httpd not running, who care?"
	fi
}