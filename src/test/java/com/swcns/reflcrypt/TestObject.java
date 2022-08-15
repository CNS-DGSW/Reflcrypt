package com.swcns.reflcrypt;

import com.swcns.reflcrypt.annotation.EncryptedField;
import lombok.*;

@ToString
@AllArgsConstructor @Builder
@NoArgsConstructor @Getter
public class TestObject {
    @EncryptedField
    private String encryptedField1;
    @EncryptedField
    private byte[] encryptedField2;

    private Integer nonEncryptedField1;
    private String nonEncryptedField2;
}
