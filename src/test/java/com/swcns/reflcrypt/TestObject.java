package com.swcns.reflcrypt;

import com.swcns.reflcrypt.annotation.SecurityField;
import lombok.*;

@ToString
@AllArgsConstructor @Builder
@NoArgsConstructor @Getter
public class TestObject {
    @SecurityField
    private String encryptedField1;
    @SecurityField
    private byte[] encryptedField2;

    private Integer nonEncryptedField1;
    private String nonEncryptedField2;
}
