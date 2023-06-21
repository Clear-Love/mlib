import subprocess

# 在 mlib-backend 目录中运行 docker compose up
subprocess.run(['docker', 'compose', 'up', '-d', '--build'], cwd='./mlib-backend')

# 构建镜像
subprocess.run(['docker', 'build', '-t', 'mlib-ui', './mlib-ui'])

# 运行容器
subprocess.run(['docker', 'run', '-d', '--name', 'mlib-ui', '-p', '3000:80', 'mlib-ui'])
