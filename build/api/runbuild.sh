#!/bin/bash
# 获取脚本所在目录的绝对路径
SIGN_BASE_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
echo "开始打包 当前打包的根目录是：${SIGN_BASE_DIR}"
docker buildx build --build-arg SIGN_BASE_DIR=${SIGN_BASE_DIR} --platform linux/amd64,linux/arm64 -t liyibo888/signature-api:v2.1 --push .
