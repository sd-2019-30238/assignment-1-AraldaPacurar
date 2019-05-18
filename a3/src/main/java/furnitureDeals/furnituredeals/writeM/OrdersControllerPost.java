package furnitureDeals.furnituredeals.writeM;

import furnitureDeals.furnituredeals.business.mediator.ConcreteMediator;
import furnitureDeals.furnituredeals.business.mediator.Mediator;
import furnitureDeals.furnituredeals.dao.*;
import furnitureDeals.furnituredeals.model.forms.FeedbackForm;
import furnitureDeals.furnituredeals.queries.orders.ProcessAddOrder;
import furnitureDeals.furnituredeals.queries.orders.ProcessListMyOrders;
import furnitureDeals.furnituredeals.queries.orders.ProcessRemoveOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.InvocationTargetException;

@Controller
@RequestMapping("order")
public class OrdersControllerPost {

    @Autowired
    private OrdersDAO ordersDao;

    @Autowired
    private UserDAO userDao;

    @Autowired
    private FurnitureDAO furnitureDao;

    @Autowired
    private RightsDAO rightsDao;

    @Autowired
    private NotificationDAO notificationDao;

    @Autowired
    private ShoppingCartDAO shoppingCartDao;

    private Mediator m = new ConcreteMediator();

    @RequestMapping(value = "history/{userId}", method = RequestMethod.POST)
    public String processListMyOrders(@RequestParam int orderId, @PathVariable int userId, @ModelAttribute @Valid FeedbackForm feedback, Model model) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        ProcessListMyOrders query = new ProcessListMyOrders(userId, model, orderId, feedback, ordersDao);
        m.handle(query);
        return "redirect:/order/history/" + userId;
    }

    @RequestMapping(value = "add/{userId}", method = RequestMethod.POST)
    public String processAddOrder(@RequestParam int[] furnitureIds, @PathVariable int userId, Model model) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        ProcessAddOrder query = new ProcessAddOrder(userId, model, furnitureIds, furnitureDao, shoppingCartDao);
        m.handle(query);
        return "message";
    }

    @RequestMapping(value = "remove/{userId}", method = RequestMethod.POST)
    public String processRemoveOrder(@RequestParam int[] orderIds, @PathVariable int userId) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        ProcessRemoveOrder query = new ProcessRemoveOrder(userId, orderIds, userDao, ordersDao, notificationDao);
        m.handle(query);
        return "redirect:/order/list/" + userId;
    }
}

