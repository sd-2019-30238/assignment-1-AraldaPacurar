package furnitureDeals.furnituredeals.business;

import furnitureDeals.furnituredeals.model.*;
import furnitureDeals.furnituredeals.model.builder.OrderBuilder;
import furnitureDeals.furnituredeals.dao.FurnitureDAO;
import furnitureDeals.furnituredeals.dao.OrdersDAO;
import furnitureDeals.furnituredeals.dao.RightsDAO;
import furnitureDeals.furnituredeals.dao.UserDAO;
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
@RequestMapping("order")
public class OrdersController {

    @Autowired
    private OrdersDAO ordersDao;

    @Autowired
    private UserDAO userDao;

    @Autowired
    private FurnitureDAO furnitureDao;

    @Autowired
    private RightsDAO rightsDao;

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

            return "message";
        }

        model.addAttribute("title", "Pending Orders");
        model.addAttribute("orders", ordersDao.findAll());
        model.addAttribute("userId", userId);

        return "order/list";
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

            return "message";
        }

        model.addAttribute("title", "Order Furniture");
        model.addAttribute("furnitures", furnitureDao.findAll());
        model.addAttribute("userId", userId);

        return "order/add";
    }

    @RequestMapping(value = "add/{userId}", method = RequestMethod.POST)
    public String processAddOrder(@RequestParam int[] furnitureIds, @PathVariable int userId, Model model){

        for(int furnitureId : furnitureIds){

            User user = null;
            Optional<User> optionalUser = userDao.findById(userId);
            if(optionalUser.isPresent()){

                user = optionalUser.get();
            }

            Furniture furniture = null;
            Optional<Furniture> optionalFurniture = furnitureDao.findById(furnitureId);
            if(optionalFurniture.isPresent()){

                furniture = optionalFurniture.get();
            }

            Orders order = new OrderBuilder()
                    .setUsername(user.getUsername())
                    .setFurnitureName(furniture.getName())
                    .setUser(user)
                    .setFurniture(furniture)
                    .build();

            ordersDao.save(order);
        }

        model.addAttribute("title", "Order Placed Successfully");
        model.addAttribute("messages", "Your order has been placed successfully! It will arrive shortly to your destination!");
        model.addAttribute("userId", userId);

        return "message";
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

            return "message";
        }

        model.addAttribute("title", "Process Orders");
        model.addAttribute("orders", ordersDao.findAll());
        model.addAttribute("userId", userId);

        return "order/remove";
    }

    @RequestMapping(value = "remove/{userId}", method = RequestMethod.POST)
    public String processRemoveOrder(@RequestParam int[] orderIds, @PathVariable int userId){

        for(int orderId : orderIds){

            ordersDao.deleteById(orderId);
        }

        return "redirect:/order/list/" + userId;
    }
}

