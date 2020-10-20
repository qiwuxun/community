package ye.qiwu.community.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import ye.qiwu.community.model.User;


@Component
@Mapper
public interface UserMapper {

 User loginIn(@Param("username") String username,@Param("password") String password);
}
