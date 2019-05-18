package furnitureDeals.furnituredeals.queries.orders;

import furnitureDeals.furnituredeals.dao.OrdersDAO;
import furnitureDeals.furnituredeals.dao.RightsDAO;
import furnitureDeals.furnituredeals.dao.UserDAO;
import furnitureDeals.furnituredeals.model.Orders;
import furnitureDeals.furnituredeals.model.Rights;
import furnitureDeals.furnituredeals.model.Role;
import furnitureDeals.furnituredeals.model.User;
import furnitureDeals.furnituredeals.queries.QueryHandler;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RemoveOrderHandler implements QueryHandler<RemoveOrder> {

    @Override
    public void handle(RemoveOrder request) {

        int userId = request.getUserId();
        Model model = request.getModel();
        UserDAO userDao = request.getUserDao();
        RightsDAO rightsDao = request.getRightsDao();
        OrdersDAO ordersDao = request.getOrdersDao();

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

            return ;
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
    }
}
