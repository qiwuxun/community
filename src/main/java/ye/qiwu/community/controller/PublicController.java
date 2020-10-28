package ye.qiwu.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ye.qiwu.community.model.Question;
import ye.qiwu.community.model.User1;
import ye.qiwu.community.service.QuestionService;
import ye.qiwu.community.service.User1Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
//import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PublicController {

 @Autowired
 private QuestionService questionService;
 @Autowired
 private User1Service user1Service;
 //发布
 @GetMapping("/public")
 public  String  Public(){

  return "public";
 }
@PostMapping("/public")
@ResponseBody
 public Map<String,Object> doPublic(Question question, HttpServletRequest request, Model model){
     Map<String, Object> map = new HashMap<>();
 //获得user对象
 System.out.println("打印一下question");
 System.out.println(question);
     User1 user1=null;
     Cookie[] cookies = request.getCookies();
     if (cookies!=null) {
         for (Cookie cookie : cookies) {
             if (cookie.getName().equals("token")) {
                 String token = cookie.getValue();
                 user1 = user1Service.findByUser1(token);
                 if (user1 != null&&user1.getId()!=null) {
                    request.getSession().setAttribute("user1", user1);
                 }
                 break;
             }
         }
     }
      if (user1==null){
       model.addAttribute("status","0");
       model.addAttribute("error","用户未登录");
       map.put("status","0");
       map.put("Msg","亲，您还未登录!!");
       return map;
     //  return "public";
      }
      //获得提问人的用户id
      question.setCreator(user1.getId());
      //可以修改一下时间格式
 long l = System.currentTimeMillis();
 Date date = new Date(l);
 System.out.println("date:   "+date);
 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 String s = sdf.format(date);
 System.out.println("当前发布时间为："+s);
 question.setGmtCreate(System.currentTimeMillis());
     question.setGmtModified(question.getGmtCreate());
  question.setCreator(user1.getId());

   int num=questionService.insertQuestion(question);
         //成功则返回首页
if (num>0){
 System.out.println("插入成功！！");
 map.put("status","1");
 map.put("Msg","发布成功！！");
}else {
 System.out.println("插入失败！！");
 map.put("status","0");
 map.put("Msg","发布失败！！");
}
   return map;
 }

}
