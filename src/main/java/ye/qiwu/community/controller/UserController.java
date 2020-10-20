package ye.qiwu.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ye.qiwu.community.model.User;
import ye.qiwu.community.model.User1;
import ye.qiwu.community.service.UserService;

@Controller
public class UserController {

 @Autowired
 private UserService userService;
 @RequestMapping("/login")
 public String show(){
  return "login";
 }

 @RequestMapping(value = "/loginIn",method = RequestMethod.POST)
 public String login(String name,String password){
  System.out.println("name: "+name);
  System.out.println("password:"+password);
  User user = userService.loginIn(name,password);

  if(user!=null){
   return "success";
  }else {
   return "error";
  }
 }

}
