package com.swcns.reflcrypt;

import com.swcns.reflcrypt.annotation.*;
import com.swcns.reflcrypt.util.ObjectEncryptor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

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
                                 int nonEncryptedField1, String nonEncryptedField2, @SecurityParam List<String> encryptedCollection) {
        System.out.printf("Encrypted: %s, %s, %d, %s, %s\n", encryptedField1, Arrays.toString(encryptedField2), nonEncryptedField1, nonEncryptedField2, encryptedCollection.toString());
    }

    @DecryptParams
    void decryptedInstanceParams(@SecurityParam String encryptedField1, @SecurityParam byte[] encryptedField2,
                                 int nonEncryptedField1, String nonEncryptedField2, @SecurityParam List<String> encryptedCollection) {
        System.out.printf("Decrypted: %s, %s, %d, %s, %s\n", encryptedField1, Arrays.toString(encryptedField2), nonEncryptedField1, nonEncryptedField2, encryptedCollection.toString());
    }
}
