package furnitureDeals.furnituredeals.queries.user;

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

public class RemoveUserHandler implements QueryHandler<RemoveUser> {

    @Override
    public void handle(RemoveUser request) {

        int userId = request.getUserId();
        Model model = request.getModel();
        UserDAO userDao = request.getUserDao();
        RightsDAO rightsDao = request.getRightsDao();
        RoleDAO roleDao = request.getRoleDao();

        Role myRole = null;
        User user = null;
        Optional<User> optionalUser = userDao.findById(userId);
        if(optionalUser.isPresent()){

            user = optionalUser.get();
            myRole = user.getRole();
        }

        List<Rights> rights = rightsDao.findByMyRight("fire");
        Rights requiredRight = rights.get(0);

        if(!myRole.getRights().contains(requiredRight)){

            model.addAttribute("title", "Invalid request!");
            model.addAttribute("messages", "You do not have privileges to perform this action!");
            model.addAttribute("userId", userId);

            return ;
        }

        List<Role> roles = roleDao.findByRole("employee");

        model.addAttribute("title", "Fire Employee");
        model.addAttribute("employees", userDao.findByRoleId(roles.get(0).getId()));
        model.addAttribute("userId", userId);
    }
}
