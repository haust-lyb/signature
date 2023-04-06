#!/bin/bash

# 进入项目目录
cd /Users/liyibo/IdeaProjects/signature/signature-ui

# 创建 dist 目录
mkdir dist

# 创建 Dockerfile 文件并添加以下内容
cat << EOF > Dockerfile
FROM node:latest
WORKDIR /app
COPY package.json .
RUN npm install
COPY . .
RUN npm run build

CMD ["cp", "-r", "dist", "/output"]
EOF

# 构建 Docker 镜像
docker build -t my-vite-app .

# 运行容器
docker run --rm -v $(pwd)/dist:/output/dist my-vite-app
