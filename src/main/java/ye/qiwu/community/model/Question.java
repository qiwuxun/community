package ye.qiwu.community.model;

import lombok.Data;

/*@Data*/
public class Question {
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
/* private Integer pageSize;//每页显示的条数
 private Integer start;//当前页的起始页*/

 @Override
 public String toString() {
  return "Question{" +
   "id=" + id +
   ", title='" + title + '\'' +
   ", describes='" + describes + '\'' +
   ", gmtCreate=" + gmtCreate +
   ", gmtModified=" + gmtModified +
   ", creator=" + creator +
   ", commentCount=" + commentCount +
   ", viewCount=" + viewCount +
   ", likeCount=" + likeCount +
   ", tag='" + tag + '\'' +
   '}';
 }

 public Long getId() {
  return id;
 }

 public void setId(Long id) {
  this.id = id;
 }

 public String getTitle() {
  return title;
 }

 public void setTitle(String title) {
  this.title = title;
 }

 public String getDescribes() {
  return describes;
 }

 public void setDescribes(String describes) {
  this.describes = describes;
 }

 public Long getGmtCreate() {
  return gmtCreate;
 }

 public void setGmtCreate(Long gmtCreate) {
  this.gmtCreate = gmtCreate;
 }

 public Long getGmtModified() {
  return gmtModified;
 }

 public void setGmtModified(Long gmtModified) {
  this.gmtModified = gmtModified;
 }

 public Integer getCommentCount() {
  return commentCount;
 }

 public void setCommentCount(Integer commentCount) {
  this.commentCount = commentCount;
 }

 public Integer getViewCount() {
  return viewCount;
 }

 public void setViewCount(Integer viewCount) {
  this.viewCount = viewCount;
 }

 public Integer getLikeCount() {
  return likeCount;
 }

 public void setLikeCount(Integer likeCount) {
  this.likeCount = likeCount;
 }

 public Integer getCreator() {
  return creator;
 }

 public void setCreator(Integer creator) {
  this.creator = creator;
 }





 public String getTag() {
  return tag;
 }

 public void setTag(String tag) {
  this.tag = tag;
 }
}
