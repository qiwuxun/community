package ye.qiwu.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import ye.qiwu.community.dto.FileDTO;
import ye.qiwu.community.exception.CustomizeErrorCode;
import ye.qiwu.community.exception.CustomizeException;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Controller
public class FileController {

@RequestMapping("/file/upload")
@ResponseBody
  public FileDTO upload(@RequestParam(value = "editormd-image-file", required = false) MultipartFile file,
                        HttpServletRequest request){
   //System.out.println(file.toString());
   //file.getOriginalFilename()拿到图片的name
   System.out.println(file.getOriginalFilename());
   System.out.println(file.getContentType());
   //实现保存，并返回虚拟相对路径
   saveImageFile(file,request);
    //返回虚拟相对路径
   String url = "http://localhost:8887/images/upImg/" + file.getOriginalFilename();
   FileDTO fileDTO = new FileDTO();
   fileDTO.setSuccess(1);
   fileDTO.setMessage("上传成功");
   fileDTO.setUrl("/images/img/qiwu.jpg");
   return fileDTO;
  }
//待完成，看是用云存储还是本地储存
 private void saveImageFile(MultipartFile file, HttpServletRequest request) {
  String generatedFileName;
  String fileName=file.getOriginalFilename();
  String[] filePaths = fileName.split("\\.");
  if (filePaths.length > 1) {
   generatedFileName = UUID.randomUUID().toString() + "." + filePaths[filePaths.length - 1];
  } else {
   throw new CustomizeException(CustomizeErrorCode.FILE_UPLOAD_FAIL);
  }

  String realPath = request.getContextPath();
  //request.getServletContext().getRealPath("/images/upImg");

  System.out.println(realPath);
/*映射请求到图片路径下

相对路径：registry.addResourceHandler("/static/**").addResourceLocations("classpath:/templates/static/");

绝对路径：registry.addResourceHandler("/image/**").addResourceLocations("file:" + SystemAPI.filePath);*/
  //file.transferTo(new File(""));
 // String realPath = UPLOADED_FOLDER;
 }
}
