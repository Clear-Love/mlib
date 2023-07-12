#!/bin/bash

# 后端构建
cd mlib-backend || exit
docker compose up -d --build


cd ../ || exit
# 构建镜像
export VITE_BASE_URL=http://localhost:8080
docker kill mlib-ui
docker rm mlib-ui
docker build -t mlib-ui ./mlib-ui

# 运行容器
docker run -d --name mlib-ui -p 80:80 mlib-ui