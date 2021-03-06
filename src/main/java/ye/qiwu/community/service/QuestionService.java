package ye.qiwu.community.service;

import ye.qiwu.community.model.Question;

import java.util.List;

public interface QuestionService {
 int insertQuestion(Question question);

 List<Question> getSelQuesList(Integer start, Integer pageSize, String regexpSerch);

 int selQuesCount(String serch);

 List<Question> selQuesByUser1Id(Integer creator);

 Question selQuesByQuesId(Long id);

 int updateQuestion(Question question);

 void updateQuesView(Question question);

 List<Question> selectRelated(Question question);
}
