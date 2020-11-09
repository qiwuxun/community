package ye.qiwu.community.service;

import ye.qiwu.community.dto.CommentDto;
import ye.qiwu.community.enums.CommentEnum;
import ye.qiwu.community.model.Comment;
import ye.qiwu.community.model.User1;

import java.util.List;

public interface CommentService {
 int insertComment(Comment comment, User1 user1);

 List<CommentDto> selCommentByParentId(long parentId, CommentEnum type);

 void upCommentCount(Comment comment);
 //Comment selCommentByCommentId(Long id);
}
