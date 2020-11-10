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

 List<Question> getSelQuesList(@Param(value = "start") Integer start, @Param(value = "pageSize") Integer pageSize,@Param(value = "title")  String title);

 int selQuesCount(String title);

 List<Question> selQuesByUser1Id(@Param(value = "creator") Integer creator);

 Question selQuesByQuesId(Long id);

 int updateQuestion(Question question);
 //修改阅读数
 void updateQuesView(Question question);
 //修改评论数
 void updateQuesComment(Question question);
  List<Question> selectRelated(Question question);
}
