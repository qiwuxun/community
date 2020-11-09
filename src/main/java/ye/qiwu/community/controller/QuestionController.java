package ye.qiwu.community.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ye.qiwu.community.dto.CommentDto;
import ye.qiwu.community.dto.QuestionDto;
import ye.qiwu.community.enums.CommentEnum;
import ye.qiwu.community.exception.CustomizeErrorCode;
import ye.qiwu.community.exception.CustomizeException;
import ye.qiwu.community.model.Question;
import ye.qiwu.community.model.User1;
import ye.qiwu.community.service.CommentService;
import ye.qiwu.community.service.QuestionService;
import ye.qiwu.community.service.User1Service;

import java.util.List;

@Controller
public class QuestionController {
 @Autowired
 private User1Service user1Service;
 @Autowired
 private QuestionService questionService;
 @Autowired
 private CommentService commentService;

 /*
  * 问题详情
  * */
 @GetMapping("/question/{id}")
 public String question(@PathVariable(name = "id") String id, Model model) {

    long quesId = Long.parseLong(id);
    System.out.println("id:  " + id);

    Question question = questionService.selQuesByQuesId(quesId);
    //设置阅读数增加1
    if (question == null) {
      throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
    }
    UpdateQuesView(quesId);
    User1 user1 = user1Service.findByUser1Id(question.getCreator());
    QuestionDto questionDto = new QuestionDto();
    BeanUtils.copyProperties(question, questionDto);
    questionDto.setUser1(user1);
    //相关问题tag
    List<Question> questionRelated = questionService.selectRelated(question);
    model.addAttribute("questionDto", questionDto);
    //根据问题id查询到它下面的所有评论列表
    List<CommentDto> commentDtos = commentService.selCommentByParentId(quesId, CommentEnum.QUESTION);
    model.addAttribute("commentDtos", commentDtos);
    model.addAttribute("questionRelated", questionRelated);
    return "question";
 }

 //修改阅读数，每次打开问题详情页，阅读数加一
 private void UpdateQuesView(Long id) {
    Question question = new Question();
    question.setId(id);
    question.setViewCount(1);
    questionService.updateQuesView(question);
 }
}
