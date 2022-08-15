package com.swcns.reflcrypt;

import com.swcns.reflcrypt.annotation.DecryptParams;
import com.swcns.reflcrypt.annotation.EncryptParams;
import com.swcns.reflcrypt.annotation.EncryptReturns;
import com.swcns.reflcrypt.util.ObjectEncryptor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = { ReflcryptAutoConfiguration.class })
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AnnotationsTest {
    @Autowired
    private ObjectEncryptor encryptor;

    @DecryptParams
    void decryptParams(TestObject decrypted) {
        System.out.println(decrypted);
    }

    @EncryptParams
    void encryptParams(TestObject encrypted) {
        System.out.println(encrypted);
    }

    @EncryptReturns
    TestObject encryptReturns(TestObject obj) {
        return obj;
    }

    @DecryptParams
    TestObject decryptReturns(TestObject obj) {
        return obj;
    }

    @Test
    void test() throws Exception {
        TestObject normal = new TestObject("안녕하세요", "wow".getBytes(), 1024, "wow!");
        TestObject encrypted = encryptor.getEncryptedObject(normal);

        decryptParams(encrypted);
        encryptParams(normal);
        System.out.println(encryptReturns(normal));
        System.out.println(decryptReturns(encrypted));
    }
}
