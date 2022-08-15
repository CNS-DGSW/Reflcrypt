package com.swcns.reflcrypt;

import com.swcns.reflcrypt.util.ObjectDecryptor;
import com.swcns.reflcrypt.util.ObjectEncryptor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = { ReflcryptAutoConfiguration.class })
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UtilTest {

    @Autowired
    private ObjectEncryptor encryptor;

    @Autowired
    private ObjectDecryptor decryptor;

    @Test
    void test() throws Exception {
        TestObject beforeEncrypt = new TestObject("안녕하세요", "wow".getBytes(), 1024, "wow!");
        System.out.printf("Before encrypt: %s\n", beforeEncrypt);

        TestObject afterEncrypt = encryptor.getEncryptedObject(beforeEncrypt);
        System.out.printf("After encrypt: %s\n", afterEncrypt);

        TestObject afterDecrypt = decryptor.getDecryptedObject(afterEncrypt);
        System.out.printf("After decrypt: %s\n", afterDecrypt);
    }
}
