package ye.qiwu.community.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import ye.qiwu.community.dao.CommentMapper;
import ye.qiwu.community.dao.NotificationMapper;
import ye.qiwu.community.dao.QuestionMapper;
import ye.qiwu.community.dao.User1Mapper;
import ye.qiwu.community.dto.CommentDto;
import ye.qiwu.community.enums.CommentEnum;
import ye.qiwu.community.enums.NotificationStatusEnum;
import ye.qiwu.community.exception.CustomizeErrorCode;
import ye.qiwu.community.exception.CustomizeException;
import ye.qiwu.community.model.Comment;
import ye.qiwu.community.model.Notification;
import ye.qiwu.community.model.Question;
import ye.qiwu.community.model.User1;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImp implements CommentService {

 @Autowired
 private CommentMapper commentMapper;

 @Autowired
 private QuestionMapper questionMapper;
 @Autowired
 private User1Mapper user1Mapper;
 @Autowired
 private NotificationMapper notificationMapper;

 @Override
 @Transactional
 public int insertComment(Comment comment,User1 commentator) {
  //判断传过来的问题id在不在
  if (comment.getParentId()==null||comment.getParentId()==0){
   throw new CustomizeException(CustomizeErrorCode.TARGET_NOT_FOUND);
  }
  if (comment.getType()==null|| !CommentEnum.isExist(comment.getType())){
   throw  new CustomizeException(CustomizeErrorCode.TYPE_PARAM_WRONG);
  }
  Question question;
  if (comment.getType()==CommentEnum.QUESTION.getType()){
     question = questionMapper.selQuesByQuesId(comment.getParentId());
     if (question==null){
      throw new CustomizeException(CustomizeErrorCode.TARGET_NOT_FOUND);
     }
   //回复问题
   //修改问题回复数
      question.setCommentCount(1);
   questionMapper.updateQuesComment(question);
   //创建通知
   createNotifiy(comment, question.getCreator().longValue(),commentator.getName(),question.getTitle(),question.getId());


  }else{
   //回复评论
   //查询当前二级评论对应的一级评论
   Comment comment1=commentMapper.selCommentByCommentId(comment.getParentId());
   if (comment1==null){
    throw  new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);
   }
   //查询一级评论对应的问题
   question = questionMapper.selQuesByQuesId(comment1.getParentId());
   if (StringUtils.isEmpty(question)){
    throw new CustomizeException(CustomizeErrorCode.TARGET_NOT_FOUND);
   }
   //创建通知
   createNotifiy(comment, comment1.getCommentator().longValue(),commentator.getName(),question.getTitle(),question.getId());
   //修改回复下评论数，使其加一
   comment1.setCommentCount(1l);
   upCommentCount(comment1);

  }


  return commentMapper.insertComment(comment);
 }
/*添加通知信息*/
 private void createNotifiy(Comment comment,Long receiver,String notifierName,String outerTitle,Long outerId) {
  if (comment.getCommentator().longValue()==receiver){
   return;
  }

  Notification notification = new Notification();
  //实现添加一条回复通知信息
  notification.setGmtCreate(System.currentTimeMillis());
  //设置回复问题还是评论
  notification.setType(comment.getType());
  //设置消息发送者
  notification.setNotifier(comment.getParentId());
  notification.setNotifierName(notifierName);
  //设置对应的问题id
  notification.setOuterid(outerId);
  notification.setOuterTitle(outerTitle);
  //设置消息接受者userId
  notification.setReceiver(receiver);
  //设置未读
  notification.setStatus(NotificationStatusEnum.UNREAD.getStatus());

  notificationMapper.insertNotification(notification);
 }

 @Override
 public List<CommentDto> selCommentByParentId(long parentId, CommentEnum type) {
    //查询当前问题下的评论列表（默认type=1）
    Integer type1 = type.getType();
  Comment comment1 = new Comment();
  comment1.setParentId(parentId);
  comment1.setType(type1);
  List<Comment> comments=commentMapper.selCommentByParentQuesId(comment1);
     if (comments==null||comments.size()==0){
      return new ArrayList<>();
     }
  List<CommentDto> commentDtos = new ArrayList<>();

  for (Comment comment : comments) {
   User1 user1 = user1Mapper.findByUser1Id(comment.getCommentator());
   CommentDto commentDto = new CommentDto();
   BeanUtils.copyProperties(comment,commentDto);
   commentDto.setUser1(user1);
   commentDtos.add(commentDto);
  }

  return commentDtos;
 }

 @Override
 public void upCommentCount(Comment comment) {
  commentMapper.upCommentCount(comment);
 }


 /*
 * 实现通知功能
 * */
}
