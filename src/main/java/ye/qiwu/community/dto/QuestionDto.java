package ye.qiwu.community.dto;

import lombok.Data;
import ye.qiwu.community.model.User1;

@Data
public class QuestionDto {
 private Long id;
 private  String title;//标题
 private String describes;//问题补充
 private Long gmtCreate;//创建时间
 private  Long gmtModified;//更新时间
 private Integer creator;//提问人id
 private Integer commentCount;//评论数
 private  Integer viewCount;//阅读数
 private Integer likeCount;//点赞数
 private  String tag;//标签
 private User1 user1;
/* private Integer pageSize;//每页显示的条数
 private Integer start;//当前页的起始页*/
}
