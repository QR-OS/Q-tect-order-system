#!/usr/bin/env bash

echo "[$(date)] client deploy" >> /home/ubuntu/log/f_deploy.log

sudo service nginx restart
