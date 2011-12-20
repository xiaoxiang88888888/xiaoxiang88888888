#!/bin/bash

. `dirname $0`/functions.sh

##初始化
prepare_env
## 关闭apache
stop_httpd
## 关闭应用容器
stop_jetty