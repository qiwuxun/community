package ye.qiwu.community.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ye.qiwu.community.dao.UserMapper;
import ye.qiwu.community.model.User;


@Service
public class UserServiceImp implements UserService {

 @Autowired
private UserMapper userMapper;


 @Override
 public User loginIn(String username, String password) {

  return userMapper.loginIn(username,password);
 }
}
