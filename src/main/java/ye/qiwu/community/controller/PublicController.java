package ye.qiwu.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import ye.qiwu.community.cache.TagCache;
import ye.qiwu.community.model.Question;
import ye.qiwu.community.model.User1;
import ye.qiwu.community.service.QuestionService;
import ye.qiwu.community.service.User1Service;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


@Controller
public class PublicController {

 @Autowired
 private QuestionService questionService;
 @Autowired
 private User1Service user1Service;
 //发布
 @GetMapping("/public")
 public  String  Public(Model model){
  model.addAttribute("tags", TagCache.getTags());
  return "public";
 }
@PostMapping("/public")
@ResponseBody
 public Map<String,Object> doPublic(Question question, HttpServletRequest request, Model model){
      Map<String, Object> map = new HashMap<>();
      //获得user对象
      //System.out.println("打印一下question");
      //System.out.println(question);
      User1 user1;
      user1 = (User1) request.getSession().getAttribute("user1");
      if (user1==null){
       model.addAttribute("status","0");
       model.addAttribute("error","用户未登录");
       map.put("status","0");
       map.put("Msg","亲，您还未登录!!");
       return map;
     //  return "public";
      }
      String invalid = question.getTag();

     if (StringUtils.isEmpty(invalid)){
      //model.addAttribute("status","0");
      model.addAttribute("error","输入非法标签");
      map.put("status","tag");
      map.put("Msg","输入非法标签");
     }
        //获得提问人的用户id
      question.setCreator(user1.getId());
      //可以修改一下时间格式

    //判断是更新还是添加新问题
    int num;

   // System.out.println("public-发布");
   // System.out.println(question.getId());
    //System.out.println(StringUtils.isEmpty(question.getId()));
    Long questionId = question.getId();
    if (questionId==null){
     //插入
     question.setGmtCreate(System.currentTimeMillis());
     question.setGmtModified(question.getGmtCreate());
     question.setCreator(user1.getId());
     num=questionService.insertQuestion(question);
     System.out.println("插入成功num:"+num);
    }else{
     //更新
     question.setGmtModified(System.currentTimeMillis());
     num= questionService.updateQuestion(question);
     System.out.println("更新成功num:"+num);
    }
         //成功则返回首页
    if (num>=0){
       //model.addAttribute("question",question);
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
@GetMapping("/public/{id}")
 public String questionPublic(@PathVariable(name = "id") String id,Model model){
 long quesId = Long.parseLong(id);
 System.out.println("public-id: "+id);
 Question question = questionService.selQuesByQuesId(quesId);
 model.addAttribute("question",question);
 //System.out.println("public-question:"+question);
 model.addAttribute("tags", TagCache.getTags());
 return "public";
 //return "redirect:/public";
}

//所谓编辑，
// 1.首先拿到question的id查询该条的详细信息
 //回显，重新显示在发布public页面，
 //然后重新发布，保存
 //注意，这个不是插入而是修改，

 //查询要修改的用户信息
 @RequestMapping("/UpPublic")
 @ResponseBody
 public Map<String,Object> UpdatePublic(@RequestParam String id){
  Map<String, Object> map = new HashMap<>();
  long quesId = Long.parseLong(id);
  System.out.println("public-id: "+id);
  Question question = questionService.selQuesByQuesId(quesId);
  //model.addAttribute("question",question);
  //System.out.println("public-question:"+question);
  map.put("question",question);

  return map;
 }

}
