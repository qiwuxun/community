package ye.qiwu.community.provider;

import com.alibaba.fastjson.JSON;
import okhttp3.*;
import org.springframework.stereotype.Component;

import ye.qiwu.community.dto.AccessTokenTo;
import ye.qiwu.community.dto.GithubUser;

@Component
public class GithubProvider {
 public String getAccessToken(AccessTokenTo accessTokenTo){
  // System.out.println("JSON  :==    "+JSON.toJSONString(accessTokenTo).toString());
  String s = JSON.toJSONString(accessTokenTo);
  String[] split = s.split(",");
  for (String s1:split) {
   System.out.println(s1);
  }
  MediaType mediaType = MediaType.get("application/json; charset=utf-8");
  //JSON.parse(accessTokenTo)

  OkHttpClient client = new OkHttpClient();
  //   RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenTo));
  //RequestBody body = RequestBody.create(JSON.toJSONString(accessTokenTo), mediaType);
  //okhttp3.RequestBody requestBody = okhttp3.RequestBody.create(mediaType, JSON.toJSONString(accessTokenTo));
  RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenTo));
  Request request = new Request.Builder()
   .url("https://github.com/login/oauth/access_token")
   .post(body)
   .build();
  try (Response response = client.newCall(request).execute()) {
   String string = response.body().string();
   System.out.println("==============");
   //  System.out.println("access_token:    "+string);
   /*access_token=aa038c095d7a5041aeb770edaf76edae9f83ad7b&scope=user&token_type=bearer*/
       /* String[] split = string.split("&");
        String[] split1 = split[0].split("=");*/
   // String s1=string.split("&")[1]
   String token = string.split("&")[0].split("=")[1];
   // System.out.println("token  :"+token);
   return token;
  } catch (Exception e) {
   e.printStackTrace();
  }

  return null;
 }
 public GithubUser getGithubUser(String accessToken){
  //get请求
  OkHttpClient client = new OkHttpClient();
  Request request = new Request.Builder()
   .url("https://api.github.com/user?")
   //.url("https://api.github.com/user?access_token=" + accessToken)
   .header("Authorization","token "+accessToken)
   .build();
  try {
   //这一步有问题
   Response  response = client.newCall(request).execute();
   String string = response.body().string();

   GithubUser githubUser = JSON.parseObject(string, GithubUser.class);
   return githubUser;
  } catch (Exception e) {
   // e.printStackTrace();
  }

  return null;
 }
}