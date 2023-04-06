#!/bin/bash
# 模拟部署在nginx上的效果
docker stop sign-ui
docker rm sign-ui

rm -rf ./dist
npm install
npm run build

mkdir -p ./html/signui/
mv ./dist/* ./html/signui/
mv ./html/* ./dist/
rm -rf ./html


docker container run \
  -d \
  -p 8080:80 \
  --name sign-ui \
  --volume "$PWD/dist":/usr/share/nginx/html \
  nginx
