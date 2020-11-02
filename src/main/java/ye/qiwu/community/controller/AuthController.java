package ye.qiwu.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ye.qiwu.community.dto.AccessTokenTo;
import ye.qiwu.community.dto.GithubUser;
import ye.qiwu.community.model.User1;
import ye.qiwu.community.provider.GithubProvider;
import ye.qiwu.community.service.User1Service;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class AuthController {

 @Autowired
 private GithubProvider githubProvider;

 @Value("${github.client.id}")
 private String  client_Id;
 @Value("${github.client.secret}")
 private String client_Secret;
 @Value("${github.redirect.uri}")
 private String redirect_Uri;
 @Autowired
 private User1Service user1Service;
 @GetMapping("/callback")
  public String callback(@RequestParam(name="code") String code,
                        @RequestParam(name="state") String state,
                        HttpServletRequest request, HttpServletResponse response) {
     AccessTokenTo accessTokenTo = new AccessTokenTo();
  //    System.out.println("code:"+code);
  //  System.out.println("state:"+state);

     accessTokenTo.setCode(code);
     accessTokenTo.setState(state);
     accessTokenTo.setClient_id(client_Id);
     accessTokenTo.setClient_secret(client_Secret);
     accessTokenTo.setRedirect_uri(redirect_Uri);
     String accessToken = githubProvider.getAccessToken(accessTokenTo);
     GithubUser githubUser = githubProvider.getGithubUser(accessToken);
     if (githubUser != null) {
        String token = UUID.randomUUID().toString();
        User1 user1 = new User1();
        System.out.println(user1.getId());
        user1.setName(githubUser.getName());
        user1.setToken(token);
       // user1.setaccountId(String.valueOf(githubUser.getId()));
        String accountId=String.valueOf(githubUser.getId());
        user1.setAvatarUrl(githubUser.getAvatarUrl());
        //应该是更新user1
       //先查询user1是否存在数据库，存在更新，不存在执行插入
       //根据accountId查询这个user1是否存在
        User1 user2=  user1Service.selUser1ByAccountId(accountId);
        if (user2!=null){
         //更新
         user1.setId(user2.getId());
           user1Service.updateUser1(user1);
        }else{
           user1.setAccountId(String.valueOf(githubUser.getId()));
           user1.setGmtCreate(System.currentTimeMillis());
           user1.setGmtModified(user1.getGmtCreate());
         //插入
           user1Service.insertUse1(user1);
        }

        //  user1Service.updateUser1(user1);
        // System.out.println(b);
        Cookie cookie = new Cookie("token", token);
        cookie.setMaxAge(60*60*24);
        response.addCookie(cookie);

       //  request.getSession().setAttribute("user1", user1);
       //   return response.sendRedirect();
        return "redirect:/";
     } else {
        return "redirect:/";
     }


  }
  //退出登录
  @GetMapping("/logout")
  public String logout(HttpServletRequest request,HttpServletResponse response){
  // Object user1 = request.getSession().getAttribute("user1");
   //删除user1的session对象
   request.getSession().removeAttribute("user1");
   //删除cookie
   Cookie cookie = new Cookie("token", null);
   cookie.setMaxAge(0);
   response.addCookie(cookie);
   return "redirect:/";
  }
 }
