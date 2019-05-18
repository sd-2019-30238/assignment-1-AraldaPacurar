package furnitureDeals.furnituredeals.readM;

import furnitureDeals.furnituredeals.business.mediator.ConcreteMediator;
import furnitureDeals.furnituredeals.business.mediator.Mediator;
import furnitureDeals.furnituredeals.dao.*;
import furnitureDeals.furnituredeals.queries.orders.AddOrder;
import furnitureDeals.furnituredeals.queries.orders.ListMyOrders;
import furnitureDeals.furnituredeals.queries.orders.ListOrders;
import furnitureDeals.furnituredeals.queries.orders.RemoveOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;

@Controller
@RequestMapping("order")
public class OrdersControllerGet {

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

    @RequestMapping(value = "list/{userId}", method =  RequestMethod.GET)
    public String listOrders(Model model, @PathVariable int userId) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        ListOrders query = new ListOrders(userId, model, userDao, rightsDao, ordersDao);
        m.handle(query);
        return "order/list";
    }

    @RequestMapping(value = "history/{userId}", method = RequestMethod.GET)
    public String listMyOrders(Model model, @PathVariable int userId) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        ListMyOrders query = new ListMyOrders(userId, model, ordersDao);
        m.handle(query);
        return "order/history";
    }

    @RequestMapping(value = "add/{userId}", method  = RequestMethod.GET)
    public String displayAddOrder(Model model, @PathVariable int userId) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        AddOrder query = new AddOrder(userId, model, userDao, rightsDao, furnitureDao);
        m.handle(query);
        return "order/add";
    }

    @RequestMapping(value = "remove/{userId}", method = RequestMethod.GET)
    public String displayRemoveOrder(Model model, @PathVariable int userId) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        RemoveOrder query = new RemoveOrder(userId, model, userDao, rightsDao, ordersDao);
        m.handle(query);
        return "order/remove";
    }
}
