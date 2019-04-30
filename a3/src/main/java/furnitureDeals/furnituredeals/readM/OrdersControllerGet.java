package furnitureDeals.furnituredeals.readM;

import furnitureDeals.furnituredeals.business.mediator.ConcreteMediator;
import furnitureDeals.furnituredeals.business.mediator.Mediator;
import furnitureDeals.furnituredeals.dao.*;
import furnitureDeals.furnituredeals.model.*;
import furnitureDeals.furnituredeals.model.forms.FeedbackForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public String listOrders(Model model, @PathVariable int userId){

        Role myRole = null;
        User user = null;
        Optional<User> optionalUser = userDao.findById(userId);
        if(optionalUser.isPresent()){

            user = optionalUser.get();
            myRole = user.getRole();
        }

        List<Rights> rights = rightsDao.findByMyRight("view orders");
        Rights requiredRight = rights.get(0);

        if(!myRole.getRights().contains(requiredRight)){

            model.addAttribute("title", "Invalid request!");
            model.addAttribute("messages", "You do not have privileges to perform this action!");
            model.addAttribute("userId", userId);

            return m.notifyMediator(this, "error");
        }

        model.addAttribute("title", "Pending Orders");
        model.addAttribute("orders", ordersDao.findAll());
        model.addAttribute("userId", userId);

        return m.notifyMediator(this, "listOrder");
    }

    @RequestMapping(value = "history/{userId}", method = RequestMethod.GET)
    public String listMyOrders(Model model, @PathVariable int userId){

        model.addAttribute("title", "Order History");
        model.addAttribute("orders", ordersDao.findByUserId(userId));
        model.addAttribute("userId", userId);
        model.addAttribute(new FeedbackForm());

        return m.notifyMediator(this, "listHistory");
    }

    @RequestMapping(value = "add/{userId}", method  = RequestMethod.GET)
    public String displayAddOrder(Model model, @PathVariable int userId){

        Role myRole = null;
        User user = null;
        Optional<User> optionalUser = userDao.findById(userId);
        if(optionalUser.isPresent()){

            user = optionalUser.get();
            myRole = user.getRole();
        }

        List<Rights> rights = rightsDao.findByMyRight("order");
        Rights requiredRight = rights.get(0);

        if(!myRole.getRights().contains(requiredRight)){

            model.addAttribute("title", "Invalid request!");
            model.addAttribute("messages", "You do not have privileges to perform this action!");
            model.addAttribute("userId", userId);

            return m.notifyMediator(this, "listOrder");
        }

        model.addAttribute("title", "Order Furniture");
        model.addAttribute("furnitures", furnitureDao.findAll());
        model.addAttribute("userId", userId);

        return m.notifyMediator(this, "addOrder");
    }

    @RequestMapping(value = "remove/{userId}", method = RequestMethod.GET)
    public String displayRemoveOrder(Model model, @PathVariable int userId){

        Role myRole = null;
        User user = null;
        Optional<User> optionalUser = userDao.findById(userId);
        if(optionalUser.isPresent()){

            user = optionalUser.get();
            myRole = user.getRole();
        }

        List<Rights> rights = rightsDao.findByMyRight("send order");
        Rights requiredRight = rights.get(0);

        if(!myRole.getRights().contains(requiredRight)){

            model.addAttribute("title", "Invalid request!");
            model.addAttribute("messages", "You do not have privileges to perform this action!");
            model.addAttribute("userId", userId);

            return m.notifyMediator(this, "error");
        }

        List<Orders> allORders = new ArrayList<>();
        List<Orders> ordersToDisplay = new ArrayList<>();

        for(Orders order : ordersDao.findAll()){
            if(!order.getStatus().equals("Payed")){
                ordersToDisplay.add(order);
            }
        }

        model.addAttribute("title", "Process Orders");
        model.addAttribute("orders", ordersToDisplay);
        model.addAttribute("userId", userId);

        return m.notifyMediator(this, "removeOrder");
    }
}
