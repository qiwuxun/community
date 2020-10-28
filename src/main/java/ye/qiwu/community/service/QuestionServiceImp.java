package ye.qiwu.community.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ye.qiwu.community.dao.QuestionMapper;
import ye.qiwu.community.model.Question;

import java.util.List;


@Service
public class QuestionServiceImp implements QuestionService {

 @Autowired
 private QuestionMapper questionMapper;
 @Override
 public int insertQuestion(Question question) {


 return questionMapper.insertQuestion(question);
 }

 @Override
 public List<Question> getSelQuesList(Integer start, Integer pageSize) {
  return questionMapper.getSelQuesList(start,pageSize);
 }

 @Override
 public int selQuesCount() {
  return questionMapper.selQuesCount();
 }
}
