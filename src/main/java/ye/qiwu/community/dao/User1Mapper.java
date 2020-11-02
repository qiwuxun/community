package ye.qiwu.community.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import ye.qiwu.community.model.User1;

@Component
@Mapper
public interface User1Mapper {
 boolean insertUser1(User1 user1);

 User1 findByUser1(String token);

// User1 getSelUser1ById(Integer creator);

 User1 findByUser1Id(Integer id);

 User1 selUser1ByAccountId(String accountId);

 void updateUser1(User1 user1);
}
