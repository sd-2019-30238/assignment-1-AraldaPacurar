package furnitureDeals.furnituredeals.queries;

import furnitureDeals.furnituredeals.dao.FurnitureDAO;
import furnitureDeals.furnituredeals.dao.RightsDAO;
import furnitureDeals.furnituredeals.dao.UserDAO;
import furnitureDeals.furnituredeals.model.Rights;
import furnitureDeals.furnituredeals.model.Role;
import furnitureDeals.furnituredeals.model.User;
import furnitureDeals.furnituredeals.model.forms.FilterForm;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;


public class ReadAllFurnitureHandler implements QueryHandler<ReadAllFurniture> {


    public void handle(ReadAllFurniture request){

        Model model = request.getModel();
        int userId = request.getUserId();
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

        List<Rights> rights = rightsDao.findByMyRight("view furniture");
        Rights requiredRight = rights.get(0);

        if(!myRole.getRights().contains(requiredRight)){

            model.addAttribute("title", "Invalid request!");
            model.addAttribute("messages", "You do not have privileges to perform this action!");
            model.addAttribute("userId", userId);

            return;
        }

        model.addAttribute("title", "Available Furniture");
        model.addAttribute("userId", userId);
        model.addAttribute("furnitures", furnitureDao.findAll());
        model.addAttribute(new FilterForm());

    }
}
