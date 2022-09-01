package com.swcns.reflcrypt.util;

import com.swcns.reflcrypt.annotation.SecurityField;
import com.swcns.reflcrypt.core.EncryptionManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.Collection;
import java.util.stream.Collectors;

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
                if(ReflectionUtil.isCollectionType(field.get(obj))) {
                    Collection<?> collection = (Collection<?>) field.get(obj);
                    field.set(instance, (T) collection.stream()
                            .map(it -> getDecryptedObject(it))
                            .collect(Collectors.toList()));
                }else{
                    Object decrypted = manager.decrypt(field.get(obj));
                    field.set(instance, decrypted);
                }
            } else {
                field.set(instance, field.get(obj));
            }
        }
        return instance;
    }

    public <T> T getDecryptedObject(T obj) {
        try {
            if(ReflectionUtil.isAbleToEncryptInstance(obj)) {
                return decryptInstance(obj);
            } else if(ReflectionUtil.isCollectionType(obj)) {
                Collection<?> result = (Collection<?>) obj;
                return (T) result.stream()
                        .map(it -> getDecryptedObject(it))
                        .collect(Collectors.toList());
            } else {
                return decryptFields(obj);
            }
        } catch (Exception ex) {
            throw new UnsupportedOperationException("Unable to decrypt: " + obj.getClass().getName());
        }
    }
}
