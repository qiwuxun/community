package ye.qiwu.community.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ye.qiwu.community.dao.User1Mapper;
import ye.qiwu.community.dto.QuestionDto;
import ye.qiwu.community.model.Question;
import ye.qiwu.community.model.User1;

import java.util.ArrayList;
import java.util.List;

@Service
public class User1ServiceImp implements User1Service {

 @Autowired
 private User1Mapper user1Mapper;

@Autowired
 private QuestionService questionService;
 @Override
 public boolean insertUse1(User1 user1) {
  return user1Mapper.insertUser1(user1);
 }

 @Override
 public User1 findByUser1(String token) {
  return user1Mapper.findByUser1(token);
 }

 @Override
 public User1 findByUser1Id(Integer id) {
  return user1Mapper.findByUser1Id(id);
 }

 @Override
 public List<QuestionDto> getQuestionList(Integer start, Integer pageSize) {
 List<Question> list=  questionService.getSelQuesList(start,pageSize);
  System.out.println("list:  "+list);
  List<QuestionDto> list1 = new ArrayList<>();

  for (Question question : list) {
// User1 user1= user1Mapper.getSelUser1ById(question.getCreator());
   User1 user1 = user1Mapper.findByUser1Id(question.getCreator());
   QuestionDto questionDto = new QuestionDto();
   BeanUtils.copyProperties(question,questionDto);
   questionDto.setUser1(user1);
   list1.add(questionDto);
  }
  return list1;
 }

 @Override
 public int selCountNum() {
  return 0;
 }
}
