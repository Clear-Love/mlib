# mlib

## 如何部署
### 前端
```shell
docker build -t mlib-ui . # 部署
docker run -p 3000:80 -d mlib-ui # 运行
```
### 后端
```shell
docker compose up -d 
```
### 如果你需要将前后端部署到一个服务器
```shell
python docker_build.py
```
或

```shell
./docker_build
```