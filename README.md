# Mobile online signature board

[![standard-readme compliant](https://img.shields.io/badge/standard--readme-OK-green.svg?style=flat-square)](https://github.com/RichardLitt/standard-readme)
TODO: Put more badges here.

一款移动端的在线签字版软件，可以通过手机浏览器等扫码签字

TODO: 2023年上半年，重新构建了签字版的架构，为笔迹增加了粗细变化，更好的模拟了签字笔的效果，同时签字板模拟了纸张的背景，增强了用户体验。

TODO：In the first half of 2023, the structure of the signature board was rebuilt, adding thickness changes to the handwriting, better simulating the effect of a signature pen, and at the same time, the signature board simulates the background of paper, enhancing the user experience.

## Table of Contents

- [Background](#background)
- [Effect](#effect)
- [Install](#install)
- [Usage](#usage)
- [API](#api)
- [Maintainers](#maintainers)
- [Contributing](#contributing)
- [License](#license)

## Background

The original reason for creating this project was that a certain project of the company involved some audit steps, which required the signature of the auditor. At the beginning, a lot of signed version hardware was purchased, but the limitations were too large, the hardware was easily damaged, and the price was high. And it is not easy to carry. Later, I decided to use the solution of scanning the code to sign. Put a button where the audit node needs to sign, click the button to pop up the QR code, scan the QR code on the mobile phone and sign, and return to the PC to get the signature picture after signing.

创建这个项目的最初原因是公司的某个项目涉及到了一些审核步骤，这些审核步骤需要审核人亲自签字，一开始采购了很多签字版硬件，但局限性太大，硬件容易损坏，价格不菲，而且不易携带，后来就决定使用扫码签字的方案，在审核节点需要签字的地方放一个按钮，点击按钮弹出二维码，手机微信扫码后签字，签字完成再返回到PC端获取签名图片即可。


## Effect

![img1.png](img1.png)

## Install

- with Docker

### docker compose
1、创建如下目录结构和文件

![img2.png](img2.png)

2、将下列内容分别粘贴到对应的文件中
> docker-compose.yaml
```
version: "3"
services:
  sign-api:
    image: liyibo888/signature-api:v1
    healthcheck:
      test: "curl --fail --silent localhost:1994/signature | grep signature || exit 1"
      interval: 20s
      timeout: 5s
      retries: 5
  sign-ui:
    image: liyibo888/signature-ui:v1
  nginx:
    depends_on:
      - sign-ui
      - sign-api
    image: nginx
    ports:
      - 8090:80
    volumes:
      - ./conf/nginx.conf:/etc/nginx/nginx.conf:ro
```

> nginx.conf
```
events {
    worker_connections 1024;
}


http {
    include mime.types;
    default_type application/octet-stream;

    sendfile on;

    keepalive_timeout 300;
    server {
        listen 80;
        server_name localhost;


        location / {
            proxy_pass http://sign-ui;
            proxy_redirect default;
        }

        location /signature/ {
            proxy_pass http://sign-api:1994;
            proxy_redirect default;
        }


        error_page 500 502 503 504 /50x.html;
        location = /50x.html {
            root html;
        }
    }
}
```

3、使用docker compose启动
```shell
docker compose up -d
```

4、打开浏览器测试

http://127.0.0.1:8090/?signKey=asdfasdf


- build from source

```shell
coming soon ...
```

## Usage

参考[springboot-demo](https://github.com/haust-lyb/signature/tree/main/springboot-demo)

## API

coming soon ...

## Maintainers

[@haust-lyb](https://github.com/haustlyb)

## Contributing

PRs accepted.

Small note: If editing the README, please conform to the [standard-readme](https://github.com/RichardLitt/standard-readme) specification.

## License

[Apache-2.0 license](https://github.com/haust-lyb/signature/blob/main/LICENSE) © 2023 [haustlyb](https://github.com/haust-lyb)
