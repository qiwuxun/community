package ye.qiwu.community.dto;

import lombok.Data;
import ye.qiwu.community.model.User1;

@Data
public class CommentDto {
 private Long id;
 private Long parentId;
 private Integer type;
 private Integer commentator;
 private Long  gmtCreate;
 private Long  gmtModified;
 private Long likeCount;
 private String content;//评论内容
 private User1 user1;
 private Long commentCount;//评论数
}
