package com.swcns.reflcrypt;

import com.swcns.reflcrypt.annotation.DecryptParams;
import com.swcns.reflcrypt.annotation.EncryptParams;
import com.swcns.reflcrypt.annotation.EncryptReturns;
import com.swcns.reflcrypt.util.ObjectEncryptor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = { ReflcryptAutoConfiguration.class, TestBeanConfiguration.class })
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AnnotationsTest {
    @Autowired
    private ObjectEncryptor encryptor;

    @Autowired AnnotationTestMethodClass testMethodClass;

    @Test
    void test() throws Exception {
        TestObject normal = new TestObject("안녕하세요", "wow".getBytes(), 1024, "wow!");
        TestObject encrypted = encryptor.getEncryptedObject(normal);

        System.out.println(testMethodClass);

        System.out.print("평문: ");
        testMethodClass.decryptParams(encrypted);

        System.out.print("암호화: ");
        testMethodClass.encryptParams(normal);

        System.out.print("암호화: ");
        System.out.println(testMethodClass.encryptReturns(normal));

        System.out.print("평문: ");
        System.out.println(testMethodClass.decryptReturns(encrypted));

        testMethodClass.encryptedInstanceParams(normal.getEncryptedField1(), normal.getEncryptedField2(), normal.getNonEncryptedField1(), normal.getNonEncryptedField2());
        testMethodClass.decryptedInstanceParams(encrypted.getEncryptedField1(), encrypted.getEncryptedField2(), encrypted.getNonEncryptedField1(), encrypted.getNonEncryptedField2());
    }
}
