package ye.qiwu.community.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ye.qiwu.community.dto.QuestionDto;
import ye.qiwu.community.model.Question;
import ye.qiwu.community.model.User1;
import ye.qiwu.community.service.QuestionService;
import ye.qiwu.community.service.User1Service;

@Controller
public class QuestionController {
 @Autowired
 private User1Service user1Service;
 @Autowired
 private QuestionService questionService;
 /*
 * 问题详情
 * */
 @GetMapping("/question/{id}")//{id}
 public String question(@PathVariable(name = "id") String id, Model model){
  //查询该id对应的question消息
 //QuestionDto questionDto= user1Service.findByQuestionId(id);
 // long id=23;
 // Long id=new Long(23);
  long quesId = Long.parseLong(id);
  System.out.println("id:  "+id);

   //设置阅读数增加1
  UpdateQuesView(quesId);
  Question question =questionService.selQuesByQuesId(quesId);
  User1 user1 = user1Service.findByUser1Id(question.getCreator());
  QuestionDto questionDto = new QuestionDto();
  BeanUtils.copyProperties(question,questionDto);
  questionDto.setUser1(user1);
  model.addAttribute("questionDto",questionDto);
  return "question";
 }

 //修改阅读数，每次打开问题详情页，阅读数加一
 private  void   UpdateQuesView(Long id){
  Question question = new Question();
  question.setId(id);
  question.setViewCount(1);
  questionService.updateQuesView(question);
 }
}
