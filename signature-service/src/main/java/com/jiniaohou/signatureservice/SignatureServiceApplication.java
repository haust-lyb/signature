package com.jiniaohou.signatureservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SignatureServiceApplication {

    // TODO: 2023/4/17 增加配置页面，和数据库，将前端界面和后端集成在一起 @liyibo
    // 应用启动，首先进入配置页面，配置签字版二维码的地址前缀，管理员的用户名和密码

    // TODO: 2023/4/17 修改打包和构建方案，简化部署，将前端打包后发布到后端的static目录，与后端打包在一起 @liyibo



    public static void main(String[] args) {
        SpringApplication.run(SignatureServiceApplication.class, args);
    }

}
