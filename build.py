import subprocess

# 后端构建
subprocess.run(["docker", "compose", "up", "-d", "--build"], cwd="mlib-backend", check=True)

# 构建镜像
subprocess.run(["docker", "kill", "mlib-ui"], check=False)
subprocess.run(["docker", "rm", "mlib-ui"], check=False)
subprocess.run(["docker", "build", "-t", "mlib-ui", "./mlib-ui"], check=True)

# 运行容器
subprocess.run(["docker", "run", "-d", "--name", "mlib-ui", "-p", "80:80", "mlib-ui"], check=True)
