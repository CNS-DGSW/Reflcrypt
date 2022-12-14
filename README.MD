# Reflcrypt
> Spring Framework에서의 객체 암호화를 위한 라이브러리   
> (Reflection + Encrypt = Reflcrypt)
 * Spring AOP 및 Reflection을 통한 간편 암호화 제공
 * Annotation을 이용하여 객체 내 필드를 간단히 암호화 할 수 있습니다.

## API 목록
### [for Field] `@SecurityField`
> Reflcrypt 작동 시 암호화 및 복호화 될 필드를 지정합니다.
 * 지원하는 타입:
   * String
   * byte[] 및 Byte[]

### [for Param] `@SecurityParam`
> Reflcrypt 작동 시 암호화 및 복호화 될 필드를 지정합니다.
* 지원하는 타입:
   * String
   * byte[] 및 Byte[]

### [for Method] `@EncryptParams`
> 파라미터로 넘어오는 객체 자체, 혹은 내부의 필드가 암호화됩니다.
 * JPA에서 repository의 메서드 호출에 적용하면 유용합니다.
    * `.save()` 메서드에 적용하여 데이터 저장 시 자동으로 암호화되도록

### [for Method] `@DecryptParams`
> 파라미터로 넘어오는 객체 자체, 혹은 내부의 필드가 복호화됩니다.

### [for Method] `@EncryptReturns`
> 리턴되는 객체의 필드가 암호화됩니다.

### [for Method] `@DecryptReturns`
> 리턴되는 객체의 필드가 복호화됩니다.
 * JPA에서 repository의 메서드 호출에 적용하면 유용합니다.
   * 메서드 호출 시, 자동으로 내용이 복호화되어 리턴

## 🧑‍💻 개발진
 * **[이승민 (Eric Lee)](https://github.com/ericlee05)** - CNS 3기 백엔드팀