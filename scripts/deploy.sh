#!/usr/bin/env bash

mvn clean package

echo 'Copy files...'

scp -i ~/.ssh/id_rsa \
    target/webfb-1.0-SNAPSHOT.jar \
    pan@192.168.1.7:/home/pan/

echo 'Restart server...'

ssh -i ~/.ssh/id_rsa pan@192.168.1.7 << EOF
pgrep java | xargs kill -9
nohup java -jar webfb-1.0-SNAPSHOT.jar > log.txt &
EOF

echo 'Bye'