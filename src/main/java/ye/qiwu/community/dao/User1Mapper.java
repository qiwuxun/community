package ye.qiwu.community.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import ye.qiwu.community.model.User1;

@Component
@Mapper
public interface User1Mapper {
 boolean insertUser1(User1 user1);
}