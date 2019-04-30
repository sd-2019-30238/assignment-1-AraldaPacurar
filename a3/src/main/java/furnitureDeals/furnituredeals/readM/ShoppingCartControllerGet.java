package furnitureDeals.furnituredeals.readM;

import furnitureDeals.furnituredeals.business.mediator.ConcreteMediator;
import furnitureDeals.furnituredeals.business.mediator.Mediator;
import furnitureDeals.furnituredeals.dao.FurnitureDAO;
import furnitureDeals.furnituredeals.dao.OrdersDAO;
import furnitureDeals.furnituredeals.dao.ShoppingCartDAO;
import furnitureDeals.furnituredeals.dao.UserDAO;
import furnitureDeals.furnituredeals.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

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
    public String listItems(Model model, @PathVariable int userId){

        List<ShoppingCart> shoppingCarts = shoppingCartDao.findByUserId(userId);
        String deals = "No deals";

        if(shoppingCarts.size() > 0){
            deals = shoppingCarts.get(0).getDeals();
        }

        model.addAttribute("title", "Items");
        model.addAttribute("userId", userId);
        model.addAttribute("shoppingCart", shoppingCarts);
        model.addAttribute("deals", deals);

        return m.notifyMediator(this, "listCart");
    }

    @RequestMapping(value = "add/{userId}", method  = RequestMethod.GET)
    public String displayAddOrder(Model model, @PathVariable int userId){

        model.addAttribute("title", "Shopping Cart");
        model.addAttribute("userId", userId);
        model.addAttribute("shoppingCart", shoppingCartDao.findByUserId(userId));

        return m.notifyMediator(this, "addToCart");
    }

    @RequestMapping(value = "remove/{userId}", method  = RequestMethod.GET)
    public String displayRemoveItems(Model model, @PathVariable int userId){

        model.addAttribute("title", "Shopping Cart");
        model.addAttribute("userId", userId);
        model.addAttribute("shoppingCart", shoppingCartDao.findByUserId(userId));

        return m.notifyMediator(this, "removeFromCart");
    }
}
