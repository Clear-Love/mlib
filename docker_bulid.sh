#!/bin/bash
# 构建镜像
docker build -t mlib-ui ./mlib-ui
# 运行容器
docker run -d --name mlib-ui -p 3000:3000 mlib-ui
# 后端构建
cd mlib-backend || exit
docker compose up -d