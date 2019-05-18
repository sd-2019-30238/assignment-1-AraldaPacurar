package furnitureDeals.furnituredeals.writeM;

import furnitureDeals.furnituredeals.business.mediator.ConcreteMediator;
import furnitureDeals.furnituredeals.business.mediator.Mediator;
import furnitureDeals.furnituredeals.dao.FurnitureDAO;
import furnitureDeals.furnituredeals.dao.OrdersDAO;
import furnitureDeals.furnituredeals.dao.ShoppingCartDAO;
import furnitureDeals.furnituredeals.dao.UserDAO;
import furnitureDeals.furnituredeals.queries.shoppingCart.ProcessListItems;
import furnitureDeals.furnituredeals.queries.shoppingCart.ProcessRemoveItems;
import furnitureDeals.furnituredeals.queries.shoppingCart.ProcessSendOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.reflect.InvocationTargetException;

@Controller
@RequestMapping("cart")
public class ShoppingCartControllerPost {

    @Autowired
    private ShoppingCartDAO shoppingCartDao;

    @Autowired
    private FurnitureDAO furnitureDao;

    @Autowired
    private OrdersDAO ordersDao;

    @Autowired
    private UserDAO userDao;

    private Mediator m = new ConcreteMediator();

    @RequestMapping(value = "list/{userId}", method = RequestMethod.POST)
    public String processListItems(Model model, @RequestParam String deal, @PathVariable int userId) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        ProcessListItems query = new ProcessListItems(userId, model, deal, shoppingCartDao);
        m.handle(query);
        return "cart/list";
    }

    @RequestMapping(value = "add/{userId}", method = RequestMethod.POST)
    public String processAddOrder(@RequestParam int[] shoppingCartIds, @PathVariable int userId, Model model) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        ProcessSendOrder query = new ProcessSendOrder(userId, model, shoppingCartIds, shoppingCartDao, ordersDao, userDao);
        m.handle(query);
        return "message";
    }

    @RequestMapping(value = "remove/{userId}", method = RequestMethod.POST)
    public String processRemoveItems(@RequestParam int[] shoppingCartIds, @PathVariable int userId, Model model) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        ProcessRemoveItems query = new ProcessRemoveItems(userId, model, shoppingCartIds, shoppingCartDao);
        m.handle(query);
        return "message";
    }
}
