package furnitureDeals.furnituredeals.readM;

import furnitureDeals.furnituredeals.business.mediator.ConcreteMediator;
import furnitureDeals.furnituredeals.business.mediator.Mediator;
import furnitureDeals.furnituredeals.dao.FurnitureDAO;
import furnitureDeals.furnituredeals.dao.OrdersDAO;
import furnitureDeals.furnituredeals.dao.ShoppingCartDAO;
import furnitureDeals.furnituredeals.dao.UserDAO;
import furnitureDeals.furnituredeals.queries.shoppingCart.RemoveItem;
import furnitureDeals.furnituredeals.queries.shoppingCart.SendOrder;
import furnitureDeals.furnituredeals.queries.shoppingCart.ListItems;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.reflect.InvocationTargetException;

@Controller
@RequestMapping("cart")
public class ShoppingCartControllerGet {

    @Autowired
    private ShoppingCartDAO shoppingCartDao;

    @Autowired
    private FurnitureDAO furnitureDao;

    @Autowired
    private OrdersDAO ordersDao;

    @Autowired
    private UserDAO userDao;

    private Mediator  m = new ConcreteMediator();

    @RequestMapping(value = "list/{userId}", method = RequestMethod.GET)
    public String listItems(Model model, @PathVariable int userId) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        ListItems query = new ListItems(userId, model, shoppingCartDao);
        m.handle(query);
        return "cart/list";
    }

    @RequestMapping(value = "add/{userId}", method  = RequestMethod.GET)
    public String displayAddOrder(Model model, @PathVariable int userId) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        SendOrder query = new SendOrder(userId, model, shoppingCartDao);
        m.handle(query);
         return "cart/add";
    }

    @RequestMapping(value = "remove/{userId}", method  = RequestMethod.GET)
    public String displayRemoveItems(Model model, @PathVariable int userId) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        RemoveItem query = new RemoveItem(userId, model, shoppingCartDao);
        m.handle(query);
        return "cart/remove";
    }
}
