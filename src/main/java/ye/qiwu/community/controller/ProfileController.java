package ye.qiwu.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ye.qiwu.community.model.Question;
import ye.qiwu.community.model.User1;
import ye.qiwu.community.service.QuestionService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ProfileController {

/* @Autowired
 private User1Service user1Service;*/
 @Autowired
 private QuestionService questionService;

 @GetMapping("/profile/{action}")
 public String profile(HttpServletRequest request, Model model,
                       @PathVariable(name = "action") String action){
  User1 user1;
   user1 = (User1)request.getSession().getAttribute("user1");
  if (user1==null){
   return "redirect:/";
  }
  if ("questions".equals(action)){
   model.addAttribute("section","questions");
   model.addAttribute("sectionName","我的提问");
  }else if ("reprise".equals(action)){
   model.addAttribute("section","reprise");
   model.addAttribute("sectionName","最新回复");
  }
  System.out.println("user1.getId()： "+user1.getId());
 List<Question> list= questionService.selQuesByUser1Id(user1.getId());
  System.out.println("selQuesByUser1Id: "+list);
  model.addAttribute("questions",list);
  model.addAttribute("user1",user1);
  return "profile";
 }


}
