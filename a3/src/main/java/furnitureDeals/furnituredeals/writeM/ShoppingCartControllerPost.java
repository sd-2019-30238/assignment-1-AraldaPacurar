package furnitureDeals.furnituredeals.writeM;

import furnitureDeals.furnituredeals.business.mediator.ConcreteMediator;
import furnitureDeals.furnituredeals.business.mediator.Mediator;
import furnitureDeals.furnituredeals.dao.FurnitureDAO;
import furnitureDeals.furnituredeals.dao.OrdersDAO;
import furnitureDeals.furnituredeals.dao.ShoppingCartDAO;
import furnitureDeals.furnituredeals.dao.UserDAO;
import furnitureDeals.furnituredeals.model.*;
import furnitureDeals.furnituredeals.model.builder.OrderBuilder;
import furnitureDeals.furnituredeals.model.decorator.FreeLampCartDecorator;
import furnitureDeals.furnituredeals.model.decorator.FreeRugCartDecorator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

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
    public String processListItems(Model model, @RequestParam String deal, @PathVariable int userId){

        List<ShoppingCart> shoppingCarts = shoppingCartDao.findByUserId(userId);
        Cart cart = null;
        String deals = "No deals";

        for(ShoppingCart shoppingCart : shoppingCarts){

            if(deal.equals("rug")){
                cart = new FreeRugCartDecorator(shoppingCart);
            }
            else{
                cart = new FreeLampCartDecorator(shoppingCart);
            }

            cart.setDeals("");
            deals = cart.getDeals();

            shoppingCart.setDeals(cart.getDeals());

            shoppingCartDao.save(shoppingCart);
        }

        model.addAttribute("title", "Items");
        model.addAttribute("userId", userId);
        model.addAttribute("shoppingCart", shoppingCarts);
        model.addAttribute("deals", deals);

        return m.notifyMediator(this, "listCart");
    }

    @RequestMapping(value = "add/{userId}", method = RequestMethod.POST)
    public String processAddOrder(@RequestParam int[] shoppingCartIds, @PathVariable int userId, Model model){

        User user = null;

        Optional<User> optionalUser = userDao.findById(userId);
        if(optionalUser.isPresent()){

            user = optionalUser.get();
        }

        for(int shoppingCartId : shoppingCartIds){

            Furniture furniture = null;
            ShoppingCart shoppingCart = null;
            Optional<ShoppingCart> optionalShoppingCart = shoppingCartDao.findById(shoppingCartId);
            if(optionalShoppingCart.isPresent()){

                shoppingCart = optionalShoppingCart.get();
                furniture = shoppingCart.getFurniture();
            }

            Orders order = new OrderBuilder()
                    .setUsername(user.getUsername())
                    .setFurnitureName(furniture.getName())
                    .setUser(user)
                    .setFurniture(furniture)
                    .setStatus("Pending")
                    .setFeedback("No Feedback")
                    .build();

            order.registerObserver(user);
            ordersDao.save(order);
            shoppingCartDao.delete(shoppingCart);
        }

        model.addAttribute("title", "Order placed successfully!");
        model.addAttribute("messages", "Your order has been placed successfully!");
        model.addAttribute("userId", userId);

        return m.notifyMediator(this, "processAddOrder");
    }

    @RequestMapping(value = "remove/{userId}", method = RequestMethod.POST)
    public String processRemoveItems(@RequestParam int[] shoppingCartIds, @PathVariable int userId, Model model){

        for(int shoppingCartId : shoppingCartIds){

            ShoppingCart shoppingCart = null;
            Optional<ShoppingCart> optionalShoppingCart = shoppingCartDao.findById(shoppingCartId);
            if(optionalShoppingCart.isPresent()){

                shoppingCart = optionalShoppingCart.get();
            }

            shoppingCartDao.delete(shoppingCart);
        }

        model.addAttribute("title", "Items successfully removed");
        model.addAttribute("messages", "The selected items have been successfully removed!");
        model.addAttribute("userId", userId);

        return m.notifyMediator(this, "processRemoveFromCart");
    }
}