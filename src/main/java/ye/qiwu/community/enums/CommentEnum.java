package ye.qiwu.community.enums;

public enum CommentEnum {
 QUESTION(1),
 COMMENT(2);
 private Integer type;

 public static boolean isExist(Integer type) {
  for (CommentEnum value : CommentEnum.values()) {
   if (value.getType()==type){
    return true;
   }
  }
  return false;
 }

 public Integer getType() {
  return type;
 }

 CommentEnum(Integer type){
  this.type=type;
 }
}
