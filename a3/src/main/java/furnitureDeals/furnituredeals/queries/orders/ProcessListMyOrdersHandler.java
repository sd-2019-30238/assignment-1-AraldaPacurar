package furnitureDeals.furnituredeals.queries.orders;

import furnitureDeals.furnituredeals.dao.OrdersDAO;
import furnitureDeals.furnituredeals.model.Orders;
import furnitureDeals.furnituredeals.model.forms.FeedbackForm;
import furnitureDeals.furnituredeals.queries.QueryHandler;
import org.springframework.ui.Model;

import java.util.Optional;

public class ProcessListMyOrdersHandler implements QueryHandler<ProcessListMyOrders> {

    @Override
    public void handle(ProcessListMyOrders request) {

        int userId = request.getUserId();
        Model model = request.getModel();
        int orderId = request.getOrderId();
        FeedbackForm feedback = request.getFeedBack();
        OrdersDAO ordersDao = request.getOrdersDao();

        Optional<Orders> optionalOrder = ordersDao.findById(orderId);
        Orders order = null;
        if(optionalOrder.isPresent()){
            order = optionalOrder.get();
        }

        if(!order.getStatus().equals("Payed")){
            model.addAttribute("title", "Error");
            model.addAttribute("messages", "Feedback can be submitted only for payed items.");
            model.addAttribute("userId", userId);

            return ;
        }

        order.setFeedback(feedback.getFeedback());
        ordersDao.save(order);
    }
}
