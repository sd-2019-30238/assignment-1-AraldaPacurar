package furnitureDeals.furnituredeals.queries.role;

import furnitureDeals.furnituredeals.dao.RightsDAO;
import furnitureDeals.furnituredeals.dao.RoleDAO;
import furnitureDeals.furnituredeals.dao.UserDAO;
import furnitureDeals.furnituredeals.model.Rights;
import furnitureDeals.furnituredeals.model.Role;
import furnitureDeals.furnituredeals.model.User;
import furnitureDeals.furnituredeals.model.forms.AddRoleRightForm;
import furnitureDeals.furnituredeals.queries.QueryHandler;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

public class RemoveRightHandler implements QueryHandler<RemoveRight> {

    @Override
    public void handle(RemoveRight request) {

        int userId = request.getUserId();
        Model model = request.getModel();
        int roleId = request.getRoleId();
        UserDAO userDao = request.getUserDao();
        RoleDAO roleDao = request.getRoleDao();
        RightsDAO rightsDao = request.getRightsDao();

        Role myRole = null;
        User myUser = null;
        Optional<User> optionalUser = userDao.findById(userId);
        if(optionalUser.isPresent()){

            myUser = optionalUser.get();
            myRole = myUser.getRole();
        }

        List<Rights> rights = rightsDao.findByMyRight("remove right from role");
        Rights requiredRight = rights.get(0);

        if(!myRole.getRights().contains(requiredRight)){

            model.addAttribute("title", "Invalid Request");
            model.addAttribute("messages", "You do not have permission to perform that action!");
            model.addAttribute("userId", userId);

            return ;
        }

        Role role = null;
        Optional<Role> optionalRole = roleDao.findById(roleId);
        if(optionalRole.isPresent()){

            role = optionalRole.get();
        }

        AddRoleRightForm form = new AddRoleRightForm(role.getRights(), role);

        model.addAttribute("title", "Remove Rights");
        model.addAttribute("form", form);
        model.addAttribute("userId", userId);
        model.addAttribute("roleId", roleId);
    }
}
