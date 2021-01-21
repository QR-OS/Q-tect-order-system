#!/usr/bin/env bash

REPOSITORY=/home/ubuntu/QOS/build
cd $REPOSITORY

APP_NAME=qos-deploy
JAR_NAME=$(ls $REPOSITORY | grep '.jar' | tail -n 1)
JAR_PATH=$REPOSITORY/$JAR_NAME

CURRENT_PID=$(pgrep -f $APP_NAME)

if [ -z "$CURRENT_PID"]
then
        echo ">실행 중인 jar 없음." >> /home/ubuntu/log/b_deploy.log
else
        echo ">sudo kill -15 $CURRENT_PID" >> /home/ubuntu/log/b_deploy.log
        kill -15 "$CURRENT_PID" >> /home/ubuntu/log/b_deploy.log 2>&1
        sleep 5
fi


echo ">$JAR_PATH 배포"
nohup java -jar "$JAR_PATH" >> /home/ubuntu/log/b_deploy.log 2>&1 &

echo "[$(date)] server deploy" >> /home/ubuntu/log/b_deploy.log
