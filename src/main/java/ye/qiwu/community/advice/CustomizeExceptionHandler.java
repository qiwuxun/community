package ye.qiwu.community.advice;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import ye.qiwu.community.dto.ResultDTO;
import ye.qiwu.community.exception.CustomizeErrorCode;
import ye.qiwu.community.exception.CustomizeException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;


@ControllerAdvice
public class CustomizeExceptionHandler {

 @ExceptionHandler(Exception.class)
  ModelAndView handle(Throwable e, HttpServletRequest request, Model model, HttpServletResponse response) {
  String contentType = request.getContentType();
  Logger logger = LoggerFactory.getLogger(CustomizeExceptionHandler.class);
  logger.error("ERROR");
  logger.error("error","");
  if ("application/json".equals(contentType)){
   //返回json
   ResultDTO resultDTO;
   if (e instanceof CustomizeException){
     resultDTO = ResultDTO.errorOf((CustomizeException) e);
   }else{
     resultDTO = ResultDTO.errorOf(CustomizeErrorCode.SYS_ERROR);
   // model.addAttribute("message","服务冒烟了，要不你稍后试试！！！");
   }
   try {
        response.setContentType("application/json");
        response.setStatus(200);
        response.setCharacterEncoding("utf-8");
        PrintWriter writer = response.getWriter();
       writer.write(JSON.toJSONString(resultDTO));
      writer.close();
   } catch (Exception e1) {
       e1.printStackTrace();
   }
   return null;
  }else{
   if (e instanceof CustomizeException){
    model.addAttribute("message", e.getMessage());
   }else{
    model.addAttribute("message",CustomizeErrorCode.SYS_ERROR.getMessage());
   }
   return new ModelAndView("error");
  }



 }
/* @ExceptionHandler(IOException.class)
 public ResponseEntity<String> handleIOException(IOException ex) {
  // prepare responseEntity

  return  new ResponseEntity<>("Hello World!", HttpStatus.OK);
 }*/

}
