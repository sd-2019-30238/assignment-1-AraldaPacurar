package furnitureDeals.furnituredeals.queries.orders;

import furnitureDeals.furnituredeals.dao.OrdersDAO;
import furnitureDeals.furnituredeals.dao.RightsDAO;
import furnitureDeals.furnituredeals.dao.UserDAO;
import org.springframework.ui.Model;

public class ListOrders {

    private int userId;
    private Model model;
    private UserDAO userDao;
    private RightsDAO rightsDao;
    private OrdersDAO ordersDao;

    public ListOrders(int userId, Model model, UserDAO userDao, RightsDAO rightsDao, OrdersDAO ordersDao) {
        this.userId = userId;
        this.model = model;
        this.userDao = userDao;
        this.rightsDao = rightsDao;
        this.ordersDao = ordersDao;
    }

    public int getUserId() {
        return userId;
    }

    public Model getModel() {
        return model;
    }

    public UserDAO getUserDao() {
        return userDao;
    }

    public RightsDAO getRightsDao() {
        return rightsDao;
    }

    public OrdersDAO getOrdersDao() {
        return ordersDao;
    }
}
