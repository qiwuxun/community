package ye.qiwu.community.exception;

import lombok.Data;

@Data
public class CustomizeException extends RuntimeException{
 private String message;
 private Integer code;
 @Override
 public String getMessage() {
  return message;
 }
 public CustomizeException(ICustomizeErrorCode errorCode) {
  this.code=errorCode.getCode();
  this.message = errorCode.getMessage();
 }

 public Integer getCode() {
  return code;
 }
}
