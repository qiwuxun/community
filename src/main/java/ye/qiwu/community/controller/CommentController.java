package ye.qiwu.community.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import ye.qiwu.community.dto.CommentDto;
import ye.qiwu.community.dto.ResultDTO;
import ye.qiwu.community.enums.CommentEnum;
import ye.qiwu.community.exception.CustomizeErrorCode;
import ye.qiwu.community.model.Comment;
import ye.qiwu.community.model.User1;
import ye.qiwu.community.service.CommentService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CommentController {
 @Autowired
 private CommentService commentService;

 @RequestMapping(value = "/comment",method = RequestMethod.POST)
 @ResponseBody
 public Object comment(@RequestBody Comment comment, HttpServletRequest request){
  //@RequestBody接收前端的json格式
  // @ResponseBody返回给前端json格式
  Logger logger = LoggerFactory.getLogger(CommentController.class);
  logger.error("ERROR");
  logger.error("error","");
  Map<String, Object> map = new HashMap<>();
  System.out.println("==================");
  //System.out.println(comment);
  if (comment==null|| StringUtils.isEmpty(comment.getContent())){
   return ResultDTO.errorOf(CustomizeErrorCode.CONTENT_IS_EMPTY);
  }
  User1 user1 =(User1) request.getSession().getAttribute("user1");
  if (user1==null){
   //ResultDTO resultDTO = ResultDTO.errorOf(2002, "亲，未登录呢，不能评论哦，请先登录");
   return  ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
  }
  comment.setCommentCount(0l);
  comment.setGmtCreate(System.currentTimeMillis());
  comment.setGmtModified(comment.getGmtCreate());
  comment.setCommentator(user1.getId());
 int num= commentService.insertComment(comment,user1);

  //System.out.println(num);
// map.put("message","成功");
  return ResultDTO.okOff();
 }
/*//修改回复下的评论数，使加一
 private void upCommentCount(Comment comment) {
  comment.setCommentCount(1l);
  commentService.upCommentCount(comment);
 }*/

 //查询该回复下的评论列表
 @RequestMapping(value = "/comment/{id}",method = RequestMethod.GET)
 @ResponseBody
 public ResultDTO<List<CommentDto>> commentTwo(@PathVariable(name = "id") Long id){
  List<CommentDto> commentDtos = commentService.selCommentByParentId(id, CommentEnum.COMMENT);

  return ResultDTO.okOff(commentDtos);
 }

}
