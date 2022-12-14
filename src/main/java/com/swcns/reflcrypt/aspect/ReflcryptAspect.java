package com.swcns.reflcrypt.aspect;

import com.swcns.reflcrypt.annotation.SecurityParam;
import com.swcns.reflcrypt.util.ObjectDecryptor;
import com.swcns.reflcrypt.util.ObjectEncryptor;
import com.swcns.reflcrypt.util.ReflectionUtil;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@RequiredArgsConstructor
@Aspect
public class ReflcryptAspect {
    private final ObjectEncryptor encryptor;
    private final ObjectDecryptor decryptor;

    @Around("@annotation(com.swcns.reflcrypt.annotation.EncryptParams)")
    public Object encryptParams(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        for(int i = 0; i < args.length; i++) {
            if(ReflectionUtil.isParamHasAnnotation(i, joinPoint, SecurityParam.class))
                args[i] = encryptor.getEncryptedObject(args[i]);
        }

        return joinPoint.proceed(args);
    }

    @Around("@annotation(com.swcns.reflcrypt.annotation.EncryptReturns)")
    public Object encryptReturns(ProceedingJoinPoint joinPoint) throws Throwable {
        return encryptor.getEncryptedObject(joinPoint.proceed(joinPoint.getArgs()));
    }

    @Around("@annotation(com.swcns.reflcrypt.annotation.DecryptParams)")
    public Object decryptParams(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        for(int i = 0; i < args.length; i++) {
            if(ReflectionUtil.isParamHasAnnotation(i, joinPoint, SecurityParam.class))
                args[i] = decryptor.getDecryptedObject(args[i]);
        }

        return joinPoint.proceed(args);
    }

    @Around("@annotation(com.swcns.reflcrypt.annotation.DecryptReturns)")
    public Object decryptReturns(ProceedingJoinPoint joinPoint) throws Throwable {
        return decryptor.getDecryptedObject(joinPoint.proceed(joinPoint.getArgs()));
    }
}
