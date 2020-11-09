package ye.qiwu.community.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import ye.qiwu.community.model.User1;
import ye.qiwu.community.service.NotificationService;
import ye.qiwu.community.service.User1Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class SessionInterptor implements HandlerInterceptor {

 @Autowired
 private User1Service user1Service;
 @Autowired
 private NotificationService notificationService;
 @Override
 public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//将用户的登录信息保存在session中
    Cookie[] cookies = request.getCookies();
    if (cookies!=null) {
       for (Cookie cookie : cookies) {
          if (cookie.getName().equals("token")) {
              String token = cookie.getValue();
              User1 user1 = user1Service.findByUser1(token);
              if (user1 != null&&user1.getId()!=null) {
                 request.getSession().setAttribute("user1", user1);
               Long unReadCount=notificationService.selUnReadCount(user1.getId());
               request.getSession().setAttribute("unReadCount",unReadCount);
              }
              break;
          }
       }
    }

  return true;
 }

 @Override
 public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

 }

 @Override
 public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

 }
}
