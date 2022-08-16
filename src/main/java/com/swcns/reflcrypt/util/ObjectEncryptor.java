package com.swcns.reflcrypt.util;

import com.swcns.reflcrypt.annotation.SecurityField;
import com.swcns.reflcrypt.core.EncryptionManager;
import lombok.RequiredArgsConstructor;

import java.lang.reflect.Field;

@RequiredArgsConstructor
public class ObjectEncryptor {
    private final EncryptionManager manager;

    private <T> T encryptInstance(Object obj) throws Exception {
        return manager.encrypt((T)obj);
    }

    private <T> T encryptFields(Object obj) throws Exception {
        T instance = (T)obj.getClass().newInstance();

        Field[] fields = obj.getClass().getDeclaredFields();
        for(Field field : fields) {
            field.setAccessible(true);
            if (field.getAnnotation(SecurityField.class) != null) {
                Object encrypted = manager.encrypt(field.get(obj));
                field.set(instance, encrypted);
            } else {
                field.set(instance, field.get(obj));
            }
        }
        return instance;
    }

    public <T> T getEncryptedObject(T obj) {
        try {
            if (ReflectionUtil.isAbleToEncryptInstance(obj)) {
                return encryptInstance(obj);
            } else {
                return encryptFields(obj);
            }
        } catch (Exception ex) {
            throw new UnsupportedOperationException("Unable to encrypt: " + obj.getClass().getName());
        }
    }
}
