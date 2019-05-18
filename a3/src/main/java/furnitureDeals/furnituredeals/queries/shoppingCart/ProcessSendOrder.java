package furnitureDeals.furnituredeals.queries.shoppingCart;

import furnitureDeals.furnituredeals.dao.OrdersDAO;
import furnitureDeals.furnituredeals.dao.ShoppingCartDAO;
import furnitureDeals.furnituredeals.dao.UserDAO;
import org.springframework.ui.Model;

public class ProcessSendOrder {

    private int userId;
    private Model model;
    private int[] shoppingCardIds;
    private ShoppingCartDAO shoppingCartDao;
    private OrdersDAO ordersDao;
    private UserDAO userDao;

    public ProcessSendOrder(int userId, Model model, int[] shoppingCardIds, ShoppingCartDAO shoppingCartDao, OrdersDAO ordersDao, UserDAO userDao) {
        this.userId = userId;
        this.model = model;
        this.shoppingCardIds = shoppingCardIds;
        this.shoppingCartDao = shoppingCartDao;
        this.ordersDao = ordersDao;
        this.userDao = userDao;
    }

    public int getUserId() {
        return userId;
    }

    public Model getModel() {
        return model;
    }

    public int[] getShoppingCardIds() {
        return shoppingCardIds;
    }

    public ShoppingCartDAO getShoppingCartDao() {
        return shoppingCartDao;
    }

    public OrdersDAO getOrdersDao() {
        return ordersDao;
    }

    public UserDAO getUserDao() {
        return userDao;
    }
}
