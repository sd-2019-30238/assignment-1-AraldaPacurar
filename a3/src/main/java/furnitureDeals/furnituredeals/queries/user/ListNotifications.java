package furnitureDeals.furnituredeals.queries.user;

import furnitureDeals.furnituredeals.dao.NotificationDAO;
import org.springframework.ui.Model;

public class ListNotifications {

    private int userId;
    private Model model;
    private NotificationDAO notificationDao;

    public ListNotifications(int userId, Model model, NotificationDAO notificationDao) {
        this.userId = userId;
        this.model = model;
        this.notificationDao = notificationDao;
    }

    public int getUserId() {
        return userId;
    }

    public Model getModel() {
        return model;
    }

    public NotificationDAO getNotificationDao() {
        return notificationDao;
    }
}
