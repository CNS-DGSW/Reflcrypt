package com.swcns.reflcrypt.util;

import com.swcns.reflcrypt.annotation.EncryptedField;
import com.swcns.reflcrypt.core.EncryptionManager;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@RequiredArgsConstructor
@Component
public class ObjectDecryptor {
    private final EncryptionManager manager;

    public <T> T getDecryptedObject(T obj) throws Exception {
        T instance = (T)obj.getClass().newInstance();

        Field[] fields = obj.getClass().getDeclaredFields();
        for(Field field : fields) {
            field.setAccessible(true);
            if(field.getAnnotation(EncryptedField.class) != null) {
                Object decrypted = manager.decrypt(field.get(obj));
                field.set(instance, decrypted);
            }else{
                field.set(instance, field.get(obj));
            }
        }

        return instance;
    }
}
