package ye.qiwu.community.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ye.qiwu.community.dao.NotificationMapper;
import ye.qiwu.community.dao.User1Mapper;
import ye.qiwu.community.dto.NotificationDTO;
import ye.qiwu.community.enums.NotificationStatusEnum;
import ye.qiwu.community.enums.NotificationTypeEnum;
import ye.qiwu.community.exception.CustomizeErrorCode;
import ye.qiwu.community.exception.CustomizeException;
import ye.qiwu.community.model.Notification;
import ye.qiwu.community.model.User1;

import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationServiceImp implements NotificationService {
 @Autowired
 private NotificationMapper notificationMapper;
 @Override
 public List<NotificationDTO> selNotifiList(Integer user1Id) {
  Notification notification = new Notification();
  notification.setReceiver(user1Id.longValue());
 List<Notification> notifications= notificationMapper.selNotifiByUser1IdList(notification);
   //该用户有没有最新回复通知
 if (notifications==null||notifications.size()==0){
    return new ArrayList<>();
   }
  ArrayList<NotificationDTO> notifDTOList = new ArrayList<>();
  for (Notification notification1 : notifications) {
   NotificationDTO notifiDTO = new NotificationDTO();
   notifiDTO.setTypeName(NotificationTypeEnum.nameOfType(notification1.getType()));
   BeanUtils.copyProperties(notification1,notifiDTO);
   notifDTOList.add(notifiDTO);
  }
  return notifDTOList;
 }

 @Override
 public Long selUnReadCount(Integer user1Id) {
  Notification notification = new Notification();
  notification.setReceiver(user1Id.longValue());
  return notificationMapper.selUnReadCount(notification);
 }

 //查询当前点击通知信息，并设置为已读
 @Override
 public NotificationDTO selNotificationById(User1 user1, Long id) {
    Notification notification=notificationMapper.selNotificationById(id);
     if (StringUtils.isEmpty(notification)){
      throw new CustomizeException(CustomizeErrorCode.NOTIFICATION_NOT_FOUND);
     }
     if (!notification.getReceiver().equals(user1.getId().longValue())){
      throw new CustomizeException(CustomizeErrorCode.READ_NOTIFICATION_FAIL);
     }
     //设置状态为已读
     notification.setStatus(NotificationStatusEnum.READ.getStatus());
     notificationMapper.upNotiStatusById(notification);
     NotificationDTO notifiDTO = new NotificationDTO();
     notifiDTO.setTypeName(NotificationTypeEnum.nameOfType(notification.getType()));
     BeanUtils.copyProperties(notification,notifiDTO);

    return notifiDTO;
 }
}
