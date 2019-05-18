package furnitureDeals.furnituredeals.queries.orders;

import furnitureDeals.furnituredeals.dao.OrdersDAO;
import furnitureDeals.furnituredeals.model.forms.FeedbackForm;
import org.springframework.ui.Model;

public class ProcessListMyOrders {

    private int userId;
    private Model model;
    private int orderId;
    private FeedbackForm feedBack;
    private OrdersDAO ordersDao;

    public ProcessListMyOrders(int userId, Model model, int orderId, FeedbackForm feedBack, OrdersDAO ordersDao) {
        this.userId = userId;
        this.model = model;
        this.orderId = orderId;
        this.feedBack = feedBack;
        this.ordersDao = ordersDao;
    }

    public int getUserId() {
        return userId;
    }

    public Model getModel() {
        return model;
    }

    public int getOrderId() {
        return orderId;
    }

    public FeedbackForm getFeedBack() {
        return feedBack;
    }

    public OrdersDAO getOrdersDao() {
        return ordersDao;
    }
}
