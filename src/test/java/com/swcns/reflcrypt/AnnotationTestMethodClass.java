package com.swcns.reflcrypt;

import com.swcns.reflcrypt.annotation.*;
import com.swcns.reflcrypt.util.ObjectEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class AnnotationTestMethodClass {
    @Autowired
    private ObjectEncryptor encryptor;

    @DecryptParams
    void decryptParams(@SecurityParam TestObject decrypted) {
        System.out.println(decrypted);
    }

    @EncryptParams
    void encryptParams(@SecurityParam TestObject encrypted) {
        System.out.println(encrypted);
    }

    @EncryptReturns
    TestObject encryptReturns(TestObject obj) {
        return obj;
    }

    @DecryptReturns
    TestObject decryptReturns(TestObject obj) {
        return obj;
    }

    @EncryptParams
    void encryptedInstanceParams(@SecurityParam String encryptedField1, @SecurityParam byte[] encryptedField2,
                                 int nonEncryptedField1, String nonEncryptedField2) {
        System.out.printf("Encrypted: %s, %s, %d, %s\n", encryptedField1, Arrays.toString(encryptedField2), nonEncryptedField1, nonEncryptedField2);
    }

    @DecryptParams
    void decryptedInstanceParams(@SecurityParam String encryptedField1, @SecurityParam byte[] encryptedField2,
                                 int nonEncryptedField1, String nonEncryptedField2) {
        System.out.printf("Decrypted: %s, %s, %d, %s\n", encryptedField1, Arrays.toString(encryptedField2), nonEncryptedField1, nonEncryptedField2);
    }
}
