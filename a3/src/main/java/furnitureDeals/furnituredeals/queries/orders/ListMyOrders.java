package furnitureDeals.furnituredeals.queries.orders;

import furnitureDeals.furnituredeals.dao.OrdersDAO;
import org.springframework.ui.Model;

public class ListMyOrders {

    private int userId;
    private Model model;
    private OrdersDAO ordersDao;

    public ListMyOrders(int userId, Model model, OrdersDAO ordersDao) {
        this.userId = userId;
        this.model = model;
        this.ordersDao = ordersDao;
    }

    public int getUserId() {
        return userId;
    }

    public Model getModel() {
        return model;
    }

    public OrdersDAO getOrdersDao() {
        return ordersDao;
    }
}
