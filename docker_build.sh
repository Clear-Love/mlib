#!/bin/bash

# 后端构建
cd mlib-backend || exit
docker compose up -d

# 构建镜像
docker build -t mlib-ui ./mlib-ui

cd ../ || exit
# 运行容器
docker run -d --name mlib-ui -p 80:80 mlib-ui