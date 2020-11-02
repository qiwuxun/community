package ye.qiwu.community.dao;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import ye.qiwu.community.model.Question;

import java.util.List;

@Component
@Mapper
public interface QuestionMapper {

 int insertQuestion(Question question);

 List<Question> getSelQuesList(@Param(value = "start") Integer start,@Param(value = "pageSize") Integer pageSize);

 int selQuesCount();

 List<Question> selQuesByUser1Id(@Param(value = "creator") Integer creator);

 Question selQuesByQuesId(Long id);

 int updateQuestion(Question question);

 void updateQuesView(Question question);
}
