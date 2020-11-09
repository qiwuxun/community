package ye.qiwu.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ye.qiwu.community.dto.NotificationDTO;
import ye.qiwu.community.enums.NotificationTypeEnum;
import ye.qiwu.community.model.User1;
import ye.qiwu.community.service.NotificationService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class NotificationController {

 @Autowired
 private NotificationService notificationService;

 @GetMapping("/notification/{id}")
 public String profile(HttpServletRequest request,
                       @PathVariable(name = "id") Long id){
    User1 user1;
    user1 = (User1)request.getSession().getAttribute("user1");
    if (user1==null){
     return "redirect:/";
    }
    NotificationDTO notificationDTO=notificationService.selNotificationById(user1,id);
     if (NotificationTypeEnum.REPLY_QUESTION.getType()==notificationDTO.getType()
        ||NotificationTypeEnum.REPLY_COMMENT.getType()==notificationDTO.getType()){

         return "redirect:/question/"+notificationDTO.getOuterid();
     }
     return "redirect:/";
 }
}
