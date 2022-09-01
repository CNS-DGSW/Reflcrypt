package com.swcns.reflcrypt;

import com.swcns.reflcrypt.util.ObjectEncryptor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.aop.AopAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collection;
import java.util.List;

@SpringBootTest(classes = { ReflcryptAutoConfiguration.class, TestBeanConfiguration.class, AopAutoConfiguration.class })
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

        testMethodClass.encryptedInstanceParams(normal.getEncryptedField1(), normal.getEncryptedField2(), normal.getNonEncryptedField1(), normal.getNonEncryptedField2(), List.of(
                normal.getEncryptedField1(), "wow"
        ));
        testMethodClass.decryptedInstanceParams(encrypted.getEncryptedField1(), encrypted.getEncryptedField2(), encrypted.getNonEncryptedField1(), encrypted.getNonEncryptedField2(), List.of(
                encrypted.getEncryptedField1(), encryptor.getEncryptedObject("wow")
        ));
    }

    @Test
    void checkType() {
        List<String> type = List.of("asdf", "1234");
        System.out.println(Collection.class.isAssignableFrom(type.getClass()));
    }
}
