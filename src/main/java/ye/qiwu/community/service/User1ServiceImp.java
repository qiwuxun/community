package ye.qiwu.community.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ye.qiwu.community.dao.User1Mapper;
import ye.qiwu.community.model.User1;

@Service
public class User1ServiceImp implements User1Service {

 @Autowired
 private User1Mapper user1Mapper;

 @Override
 public boolean insertUse1(User1 user1) {
  return user1Mapper.insertUser1(user1);
 }
}
