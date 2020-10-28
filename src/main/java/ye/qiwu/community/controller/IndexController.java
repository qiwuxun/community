package ye.qiwu.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ye.qiwu.community.dto.PageInfo;
import ye.qiwu.community.dto.QuestionDto;
import ye.qiwu.community.model.Question;
import ye.qiwu.community.model.User1;
import ye.qiwu.community.service.QuestionService;
import ye.qiwu.community.service.User1Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class IndexController {

 @Autowired
 private User1Service user1Service;
 @Autowired
 private QuestionService questionService;
 @GetMapping("/")
 public String index(HttpServletRequest request, Model model,
   @RequestParam(name = "page",defaultValue = "1") Integer page,
    @RequestParam(name = "pageSize",defaultValue = "2") Integer pageSize){
  System.out.println("page:"+page);
  System.out.println("pageSize"+pageSize);
    //分页问题
    //page 当前是第几页
      //pageSize  每一页显示条数

    //将用户的登录信息保存在session中
    Cookie[] cookies = request.getCookies();
    if (cookies!=null) {
        for (Cookie cookie : cookies) {
           if (cookie.getName().equals("token")) {
               String token = cookie.getValue();
                   User1 user1 = user1Service.findByUser1(token);
               if (user1 != null&&user1.getId()!=null) {
                   request.getSession().setAttribute("user1", user1);
                }
                break;
           }
        }
    }
    //除了question对象集合外，还要图片地址

  // 获得提问总记录数
    int num= questionService.selQuesCount();
  System.out.println("num:"+num);
  PageInfo pageInfo = new PageInfo();
  pageInfo.setPage(page);
  pageInfo.setPageSize(pageSize);
  //获得当前页的起始页

  pageInfo.setTotal(num);

  //设置分页相关内容
  pageInfo.setPagination(pageInfo.getTotal(),pageInfo.getPage());
  Integer start=(pageInfo.getPageSize())*(pageInfo.getPage()-1);
  System.out.println("start: "+start);
  List<QuestionDto> list= user1Service.getQuestionList(start,pageSize);
    // int num= user1Service.selCountNum();
    System.out.println("pageInfo: "+pageInfo);
    model.addAttribute("questionDtos",list);
    model.addAttribute("pageInfo",pageInfo);
  System.out.println("list:");
  System.out.println(list);
    //model.addAttribute("name",name);
    return  "index";
 }
/*
@RequestMapping("/")
@ResponseBody
public Map<String,Object> queryIndexList(Model model,
    @RequestParam(name = "page",defaultValue = "1") Integer pageNum,
    @RequestParam(name = "size",defaultValue = "5") Integer size){
    Map<String, Object> map = new HashMap<>();
   //分页问题
    //page 当前是第几页
    //size  每一页显示几条数据
   //除了question对象集合外，还要图片地址
    List<QuestionDto> list= user1Service.getQuestionList();
    // int num= user1Service.selCountNum();
    //获得提问总数
    int nums= questionService.selQuesCount();
    model.addAttribute("questionDtos",list);
    System.out.println("list:");
    System.out.println(list);

    return map;
}
*/



}
