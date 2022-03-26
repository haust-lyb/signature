package io.gitee.haust_lyb.signature.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

/**
 * Created by 李一博 on 22:37 2020/9/21.
 */
@Configuration
public class BehindRunningCfg implements CommandLineRunner {

    @Value("${server.port}")
    private String serverport;

    @Value("${server.servlet.context-path}")
    private String contextpath;


    @Override
    public void run(String... args) {
        System.out.println("#################################################################################################");
        System.out.println("# 项目管理平台访问地址：http://localhost:" + serverport + contextpath);
        System.out.println("# ###############################################################################################");
    }
}
