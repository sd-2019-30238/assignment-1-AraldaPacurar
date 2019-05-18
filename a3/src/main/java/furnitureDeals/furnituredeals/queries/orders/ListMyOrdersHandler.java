package furnitureDeals.furnituredeals.queries.orders;

import furnitureDeals.furnituredeals.dao.OrdersDAO;
import furnitureDeals.furnituredeals.model.forms.FeedbackForm;
import furnitureDeals.furnituredeals.queries.QueryHandler;
import org.springframework.ui.Model;

public class ListMyOrdersHandler implements QueryHandler<ListMyOrders> {

    @Override
    public void handle(ListMyOrders request) {

        int userId = request.getUserId();
        Model model = request.getModel();
        OrdersDAO ordersDao = request.getOrdersDao();

        model.addAttribute("title", "Order History");
        model.addAttribute("orders", ordersDao.findByUserId(userId));
        model.addAttribute("userId", userId);
        model.addAttribute(new FeedbackForm());
    }
}
