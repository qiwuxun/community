package ye.qiwu.community.model;

import lombok.Data;

@Data
public class Comment {
 private Long id;
 private Long parentId;
 private Integer type;
 private Integer commentator;
 private Long  gmtCreate;
 private Long  gmtModified;
 private Long likeCount;
 private String content;//评论内容
 private Long commentCount;//评论数

}
