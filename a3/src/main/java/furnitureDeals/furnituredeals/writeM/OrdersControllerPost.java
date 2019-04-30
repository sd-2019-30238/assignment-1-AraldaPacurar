package furnitureDeals.furnituredeals.writeM;

import furnitureDeals.furnituredeals.business.mediator.ConcreteMediator;
import furnitureDeals.furnituredeals.business.mediator.Mediator;
import furnitureDeals.furnituredeals.dao.*;
import furnitureDeals.furnituredeals.model.*;
import furnitureDeals.furnituredeals.model.builder.NotificationBuilder;
import furnitureDeals.furnituredeals.model.builder.ShoppingCartBuilder;
import furnitureDeals.furnituredeals.model.forms.FeedbackForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

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
    public String processListMyOrders(@RequestParam int orderId, @PathVariable int userId, @ModelAttribute @Valid FeedbackForm feedback, Model model){

        Optional<Orders> optionalOrder = ordersDao.findById(orderId);
        Orders order = null;
        if(optionalOrder.isPresent()){
            order = optionalOrder.get();
        }

        if(!order.getStatus().equals("Payed")){
            model.addAttribute("title", "Error");
            model.addAttribute("messages", "Feedback can be submitted only for payed items.");
            model.addAttribute("userId", userId);

            return m.notifyMediator(this, "error");
        }

        order.setFeedback(feedback.getFeedback());
        ordersDao.save(order);

        return m.notifyMediator(this, "processListHistory") + userId;
    }

    @RequestMapping(value = "add/{userId}", method = RequestMethod.POST)
    public String processAddOrder(@RequestParam int[] furnitureIds, @PathVariable int userId, Model model){

        User user = null;

        for(int furnitureId : furnitureIds){

            Furniture furniture = null;
            Optional<Furniture> optionalFurniture = furnitureDao.findById(furnitureId);
            if(optionalFurniture.isPresent()){

                furniture = optionalFurniture.get();
            }

            ShoppingCart shoppingCart = new ShoppingCartBuilder()
                    .setFurniture(furniture)
                    .setUserId(userId)
                    .setPrice(furniture.getPrice())
                    .setDeals("")
                    .build();

            shoppingCartDao.save(shoppingCart);
        }

        model.addAttribute("title", "Items Added To Shopping Cart");
        model.addAttribute("messages", "Your items have been added successfully to the shopping cart!");
        model.addAttribute("userId", userId);

        return m.notifyMediator(this, "processAddOrder");
    }

    @RequestMapping(value = "remove/{userId}", method = RequestMethod.POST)
    public String processRemoveOrder(@RequestParam int[] orderIds, @PathVariable int userId){

        for(int orderId : orderIds){

            Optional<Orders> optionalOrder = ordersDao.findById(orderId);
            Orders order = null;
            if(optionalOrder.isPresent()){
                order = optionalOrder.get();
            }

            User user = null;
            Optional<User> optionalUser = userDao.findById(order.getUser().getId());
            if(optionalUser.isPresent()) {

                user = optionalUser.get();
            }

            String notificationMessage = "No notifications";

            if(order.getStatus().equals("Pending")){
                order.setStatus("Shipping");
                notificationMessage = "Order number " + order.getId() + " has been sent!";
            }
            else if(order.getStatus().equals("Shipping")){
                order.setStatus("Payed");
                notificationMessage = "Order number " + order.getId() + " has arrived!";
            }

            Notification notification = new NotificationBuilder()
                    .setUser(user)
                    .setNotification(notificationMessage)
                    .build();

            order.notifyObserver(user, notification);

            ordersDao.save(order);
            notificationDao.save(notification);
        }

        return m.notifyMediator(this, "processRemoveOrder") + userId;
    }
}

