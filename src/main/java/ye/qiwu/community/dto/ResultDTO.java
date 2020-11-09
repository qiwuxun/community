package ye.qiwu.community.dto;

import lombok.Data;
import ye.qiwu.community.exception.CustomizeErrorCode;
import ye.qiwu.community.exception.CustomizeException;

@Data
public class ResultDTO <T>{

 private Integer code;
 private String message;
 private T data;

 public static ResultDTO errorOf(Integer code,String message){
  ResultDTO resultDTO = new ResultDTO();
  resultDTO.setCode(code);
  resultDTO.setMessage(message);
  return resultDTO;
 }

 public static ResultDTO errorOf(CustomizeErrorCode errorCode) {
  return ResultDTO.errorOf(errorCode.getCode(),errorCode.getMessage());
 }

 public static ResultDTO errorOf(CustomizeException e) {

  return errorOf(e.getCode(),e.getMessage());

 }
 public static ResultDTO okOff(){
  ResultDTO resultDTO = new ResultDTO();
  resultDTO.setCode(200);
  resultDTO.setMessage("成功");
  return resultDTO;
 }

 public static <T>ResultDTO okOff(T t){
  ResultDTO resultDTO = new ResultDTO();
  resultDTO.setCode(200);
  resultDTO.setMessage("成功");
  resultDTO.setData(t);
  return resultDTO;
 }
}
