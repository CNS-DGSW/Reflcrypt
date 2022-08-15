package com.swcns.reflcrypt.util;

import com.swcns.reflcrypt.annotation.EncryptedField;
import com.swcns.reflcrypt.core.EncryptionManager;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

import java.lang.reflect.Field;

@RequiredArgsConstructor
public class ObjectEncryptor {
    private final EncryptionManager manager;

    public <T> T getEncryptedObject(T obj) throws Exception {
        T instance = (T)obj.getClass().newInstance();

        Field[] fields = obj.getClass().getDeclaredFields();
        for(Field field : fields) {
            field.setAccessible(true);
            if(field.getAnnotation(EncryptedField.class) != null) {
                Object encrypted = manager.encrypt(field.get(obj));
                field.set(instance, encrypted);
            }else{
                field.set(instance, field.get(obj));
            }
        }

        return instance;
    }
}
