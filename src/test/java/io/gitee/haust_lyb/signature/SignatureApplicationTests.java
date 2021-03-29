package io.gitee.haust_lyb.signature;

import io.xjar.XCryptos;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SignatureApplicationTests {

    @Test
    void contextLoads() throws Exception {
        //
        XCryptos.encryption()
                .from("/Users/liyibo/IdeaProjects/signature/target/signature-0.0.1-SNAPSHOT.jar")
                .use("haust_lyb11218888")
                .include("/io/gitee/**/*.class")
                .exclude("/static/**/*")
                .exclude("/templates/**/*")
                .to("/Users/liyibo/IdeaProjects/signature/target/encrypted/signature-0.0.1-SNAPSHOT.jar");
    }

}
