package ye.qiwu.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ye.qiwu.community.dto.FileDTO;

@Controller
public class FileController {

@RequestMapping("/file/upload")
@ResponseBody
  public FileDTO upload(){
   FileDTO fileDTO = new FileDTO();
   fileDTO.setSuccess(1);
   fileDTO.setMessage("上传成功");
   fileDTO.setUrl("/images/img/qiwu.jpg");
   return fileDTO;
  }
}
