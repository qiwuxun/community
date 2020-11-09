package ye.qiwu.community.service;

import ye.qiwu.community.dto.NotificationDTO;
import ye.qiwu.community.model.User1;

import java.util.List;

public interface NotificationService {
 List<NotificationDTO> selNotifiList(Integer id);

 Long selUnReadCount(Integer id);

 NotificationDTO selNotificationById(User1 user1, Long id);

}
