package ye.qiwu.community.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import ye.qiwu.community.enums.CommentEnum;
import ye.qiwu.community.model.Comment;
import ye.qiwu.community.model.Question;

import java.util.List;

@Component
@Mapper
public interface CommentMapper {
 int insertComment(Comment comment);

 Comment selCommentByCommentId(Long id);
 //查询当前问题下的评论列表（默认type=1），直接回复问题的评论列表
 List<Comment> selCommentByParentQuesId(Comment comment);

 //修改评论数
 void upCommentCount(Comment comment);
}
