#!/bin/bash

#APP_NAME=`dirname $0`/monitor-0.0.1-SNAPSHOT.jar
APP_NAME=$(cd "$(dirname $0)";pwd)/Summary-0.0.1-SNAPSHOT.jar

usage() {
    echo "Usage: monitor.sh [start|stop|restart|status]"
    exit 1
}

#is running
is_exist(){
  pid=`ps -ef|grep $APP_NAME|grep -v grep|awk '{print $2}'`
  #如果不存在返回1，存在返回0     
  if [ -z "${pid}" ]; then
   return 1
  else
    return 0
  fi
}


start(){
  is_exist
  if [ $? -eq 0 ]; then
    echo "${APP_NAME} is already running. pid=${pid}"
  else
	nohup java -jar -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=10067 ${APP_NAME}  >/dev/null 2>&1 &
	pid=$(ps -ef | grep ${APP_NAME} |grep -v grep |awk '{print$2}')
	echo $pid
  fi
}


stop(){
  is_exist
  if [ $? -eq "0" ]; then
    kill -9 $pid
  else
    echo "${APP_NAME} is not running"
  fi  
}

status(){
  is_exist
  if [ $? -eq "0" ]; then
    echo "${APP_NAME} is running. Pid is ${pid}"
  else
    echo "${APP_NAME} is not running."
  fi
}


restart(){
  stop
  sleep 2
  start
}


case "$1" in
  "start")
    start
    ;;
  "stop")
    stop
    ;;
  "status")
    status
    ;;
  "restart")
    restart
    ;;
  *)
    usage
    ;;
esac
