package furnitureDeals.furnituredeals.queries.furniture;

import furnitureDeals.furnituredeals.dao.FurnitureTypeDAO;
import furnitureDeals.furnituredeals.dao.RightsDAO;
import furnitureDeals.furnituredeals.dao.UserDAO;
import furnitureDeals.furnituredeals.model.Furniture;
import furnitureDeals.furnituredeals.model.Rights;
import furnitureDeals.furnituredeals.model.Role;
import furnitureDeals.furnituredeals.model.User;
import furnitureDeals.furnituredeals.queries.QueryHandler;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

public class AddFurnitureHandler implements QueryHandler<AddFurniture> {

    @Override
    public void handle(AddFurniture request) {

        Model model = request.getModel();
        int userId = request.getUserId();
        UserDAO userDao = request.getUserDao();
        RightsDAO rightsDao = request.getRightsDao();
        FurnitureTypeDAO furnitureTypeDao = request.getFurnitureTypeDAO();

        Role myRole = null;
        User user = null;
        Optional<User> optionalUser = userDao.findById(userId);
        if(optionalUser.isPresent()){

            user = optionalUser.get();
            myRole = user.getRole();
        }

        List<Rights> rights = rightsDao.findByMyRight("add furniture");
        Rights requiredRight = rights.get(0);

        if(!myRole.getRights().contains(requiredRight)){


            model.addAttribute("title", "Invalid request!");
            model.addAttribute("messages", "You do not have privileges to perform this operation!");
            model.addAttribute("userId", userId);

            return;
        }

        model.addAttribute("title", "Add Furniture");
        model.addAttribute("userId", userId);
        model.addAttribute("furnitureTypes", furnitureTypeDao.findAll());
        model.addAttribute(new Furniture());
    }
}
