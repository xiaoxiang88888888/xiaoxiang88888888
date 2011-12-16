#!/bin/bash

. `dirname $0`/functions.sh

## root启动判断
exit_root

##初始化
prepare_env
## 启动应用容器
start_jetty

## 启动apache
start_httpd
