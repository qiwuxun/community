package ye.qiwu.community.service;

import ye.qiwu.community.dto.QuestionDto;
import ye.qiwu.community.model.User1;

import java.util.List;

public interface User1Service {
 boolean insertUse1(User1 user1);

 User1 findByUser1(String token);
User1 findByUser1Id(Integer id);
 List<QuestionDto> getQuestionList(Integer start, Integer pageSize);

 int selCountNum();
}
