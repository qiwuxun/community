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

   User1 user1 = new User1();
   System.out.println(user1.getId());
   user1.setName(githubUser.getName());
   user1.setAccount_Id(UUID.randomUUID().toString());
   user1.setToken(String.valueOf(githubUser.getId()));
   user1.setGmt_Create(System.currentTimeMillis());
   user1.setGmt_Modified(user1.getGmt_Create());
    /*//  List<User> users = userMapper.selectUser();
      for (User  : users) {
       System.out.println();
      }*/
   boolean b = user1Service.insertUse1(user1);
   System.out.println(b);
   //userService.insertUser(user);
   //   userMapper.insert(user);
   request.getSession().setAttribute("user1", githubUser);
   //   return response.sendRedirect();
   return "redirect:/";
  } else {
   return "redirect:/";
  }


 }
 }
