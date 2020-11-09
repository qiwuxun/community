package ye.qiwu.community.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import ye.qiwu.community.model.Notification;

import java.util.List;

@Component
@Mapper
public interface NotificationMapper {
 void insertNotification(Notification notification);

 List<Notification> selNotifiByUser1IdList(Notification notification);

 Long selUnReadCount(Notification notification);

 Notification selNotificationById(Long id);

 void upNotiStatusById(Notification notification);
}
