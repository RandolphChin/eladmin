#!/bin/bash

docker stop eladmin
docker rm eladmin
docker rmi eladmin

cd /home/eladmin
docker build -t eladmin .

docker run -d \
--name eladmin --restart always \
-p 8001:8001 \
-e "TZ=Asia/Shanghai" \
-e DB_HOST=192.168.2.31 \
-e DB_PORT=3306 \
-e DB_NAME=lims8000 \
-e DB_USER=root \
-e DB_PWD=root \
-e REDIS_HOST=192.168.2.31 \
-e REDIS_PWD=zsb \
-e MINIO_HOST=192.168.2.31 \
-e MINIO_PORT=9000 \
-e MINIO_ACCESS_KEY=bfrj \
-e MINIO_SECRET_KEY=bfrj8526660 \
-e "spring.profiles.active=dev" \
-v /home/eladmin/logs/:/logs/ \
--net host \
eladmin
