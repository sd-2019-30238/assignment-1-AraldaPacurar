package furnitureDeals.furnituredeals.queries.role;

import furnitureDeals.furnituredeals.dao.RightsDAO;
import furnitureDeals.furnituredeals.dao.RoleDAO;
import furnitureDeals.furnituredeals.dao.UserDAO;
import furnitureDeals.furnituredeals.model.Rights;
import furnitureDeals.furnituredeals.model.Role;
import furnitureDeals.furnituredeals.model.User;
import furnitureDeals.furnituredeals.queries.QueryHandler;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

public class ListRolesHandler implements QueryHandler<ListRoles> {

    @Override
    public void handle(ListRoles request) {

        int userId = request.getUserId();
        Model model = request.getModel();
        UserDAO userDao = request.getUserDao();
        RightsDAO rightsDao = request.getRightsDao();
        RoleDAO roleDao = request.getRoleDao();

        Role role = null;
        User user = null;
        Optional<User> optionalUser = userDao.findById(userId);
        if(optionalUser.isPresent()){

            user = optionalUser.get();
            role = user.getRole();
        }

        List<Rights> rights = rightsDao.findByMyRight("view roles");
        Rights requiredRight = rights.get(0);

        if(!role.getRights().contains(requiredRight)){

            model.addAttribute("title", "Invalid request!");
            model.addAttribute("messages", "You do not have permission to perform that action!");
            model.addAttribute("userId", userId);

            return ;
        }

        model.addAttribute("title", "Roles");
        model.addAttribute("roles", roleDao.findAll());
        model.addAttribute("userId", userId);
    }
}
