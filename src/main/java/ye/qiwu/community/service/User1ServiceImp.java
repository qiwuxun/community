package ye.qiwu.community.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ye.qiwu.community.dao.User1Mapper;
import ye.qiwu.community.dto.QuestionDto;
import ye.qiwu.community.model.Question;
import ye.qiwu.community.model.User1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
 public List<QuestionDto> getQuestionList(Integer start, Integer pageSize, String serch) {
  String regexpSerch = null;
  if (!StringUtils.isEmpty(serch)){
   String[] serchs=serch.split(" ");
    regexpSerch = Arrays.stream(serchs).collect(Collectors.joining("|"));
   //question.setTag(regexpTag);

  }


 List<Question> list=  questionService.getSelQuesList(start,pageSize,regexpSerch);
  //System.out.println("list:  "+list);
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

 @Override
 public void updateUser1(User1 user1) {
  //首先查询传过来的user1的token是否存在数据库
  user1Mapper.updateUser1(user1);
 }

 @Override
 public User1 selUser1ByAccountId(String accountId) {
  return user1Mapper.selUser1ByAccountId(accountId);
 }
}
