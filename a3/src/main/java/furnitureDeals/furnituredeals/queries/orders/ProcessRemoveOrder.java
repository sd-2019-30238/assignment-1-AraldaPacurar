package furnitureDeals.furnituredeals.queries.orders;

import furnitureDeals.furnituredeals.dao.NotificationDAO;
import furnitureDeals.furnituredeals.dao.OrdersDAO;
import furnitureDeals.furnituredeals.dao.UserDAO;

public class ProcessRemoveOrder {

    private int userId;
    private int[] orderIds;
    private UserDAO userDao;
    private OrdersDAO ordersDao;
    private NotificationDAO notificationDao;

    public ProcessRemoveOrder(int userId, int[] orderIds, UserDAO userDao, OrdersDAO ordersDao, NotificationDAO notificationDao) {
        this.userId = userId;
        this.orderIds = orderIds;
        this.userDao = userDao;
        this.ordersDao = ordersDao;
        this.notificationDao = notificationDao;
    }

    public int getUserId() {
        return userId;
    }

    public int[] getOrderIds() {
        return orderIds;
    }

    public UserDAO getUserDao() {
        return userDao;
    }

    public OrdersDAO getOrdersDao() {
        return ordersDao;
    }

    public NotificationDAO getNotificationDao() {
        return notificationDao;
    }
}
