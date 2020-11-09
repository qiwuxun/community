package ye.qiwu.community.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ye.qiwu.community.dao.QuestionMapper;
import ye.qiwu.community.model.Question;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;


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

 @Override
 public List<Question> selQuesByUser1Id(Integer creator) {
  return questionMapper.selQuesByUser1Id(creator);
 }

 @Override
 public Question selQuesByQuesId(Long id) {
  return questionMapper.selQuesByQuesId(id);
 }

 @Override
 public int updateQuestion(Question question) {
  return questionMapper.updateQuestion(question);
 }

 @Override
 public void updateQuesView(Question question) {
  questionMapper.updateQuesView(question);
 }

 @Override
 public List<Question> selectRelated(Question question) {
  if (StringUtils.isEmpty(question.getTag())){
   //tag为空
   return new ArrayList<>();
  }
  String[] tags = StringUtils.split(question.getTag(), ",");
  //希望分割成tag1|tag1
  String regexpTag = Arrays.stream(tags).collect(Collectors.joining("|"));
    question.setTag(regexpTag);
  return questionMapper.selectRelated(question);
 }
}
