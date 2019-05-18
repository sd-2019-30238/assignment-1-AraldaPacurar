package furnitureDeals.furnituredeals.queries.orders;

import furnitureDeals.furnituredeals.dao.FurnitureDAO;
import furnitureDeals.furnituredeals.dao.RightsDAO;
import furnitureDeals.furnituredeals.dao.UserDAO;
import furnitureDeals.furnituredeals.model.Rights;
import furnitureDeals.furnituredeals.model.Role;
import furnitureDeals.furnituredeals.model.User;
import furnitureDeals.furnituredeals.queries.QueryHandler;
import org.springframework.ui.Model;
import org.w3c.dom.UserDataHandler;

import java.util.List;
import java.util.Optional;

public class AddOrderHandler implements QueryHandler<AddOrder> {

    @Override
    public void handle(AddOrder request) {

        int userId = request.getUserId();
        Model model = request.getModel();
        UserDAO userDao = request.getUserDao();
        RightsDAO rightsDao = request.getRightsDao();
        FurnitureDAO furnitureDao = request.getFurnitureDao();

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

            return ;
        }

        model.addAttribute("title", "Order Furniture");
        model.addAttribute("furnitures", furnitureDao.findAll());
        model.addAttribute("userId", userId);
    }
}
