package furnitureDeals.furnituredeals.queries.user;

import furnitureDeals.furnituredeals.dao.NotificationDAO;
import furnitureDeals.furnituredeals.queries.QueryHandler;
import org.springframework.ui.Model;

public class ListNotificationsHandler implements QueryHandler<ListNotifications> {

    @Override
    public void handle(ListNotifications request) {

        int userId = request.getUserId();
        Model model = request.getModel();
        NotificationDAO notificationDao = request.getNotificationDao();

        model.addAttribute("title", "View Notifications");
        model.addAttribute("notifications", notificationDao.findByUserId(userId));
        model.addAttribute("userId", userId);
    }
}
