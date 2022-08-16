package com.swcns.reflcrypt.util;

import com.swcns.reflcrypt.annotation.SecurityField;
import com.swcns.reflcrypt.core.EncryptionManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@RequiredArgsConstructor
@Component
public class ObjectDecryptor {
    private final EncryptionManager manager;

    private <T> T decryptInstance(Object obj) throws Exception {
        return manager.decrypt((T)obj);
    }

    private <T> T decryptFields(Object obj) throws Exception {
        T instance = (T)obj.getClass().newInstance();

        Field[] fields = obj.getClass().getDeclaredFields();
        for(Field field : fields) {
            field.setAccessible(true);
            if (field.getAnnotation(SecurityField.class) != null) {
                Object decrypted = manager.decrypt(field.get(obj));
                field.set(instance, decrypted);
            } else {
                field.set(instance, field.get(obj));
            }
        }
        return instance;
    }

    public <T> T getDecryptedObject(T obj) throws Exception {
        if(ReflectionUtil.isAbleToEncryptInstance(obj)) {
            return decryptInstance(obj);
        } else {
            return decryptFields(obj);
        }
    }
}
