package ye.qiwu.community.model;

import lombok.Data;

@Data
public class Notification {
 private Long id;
 private Long notifier;//通知人
 private  String notifierName;
 private Long receiver;//接受人
 private Long outerid;
 private String outerTitle;
 private Integer  type;
 private Long  gmtCreate;
 private Integer status;


}
