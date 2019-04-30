package furnitureDeals.furnituredeals.readM;

import furnitureDeals.furnituredeals.business.mediator.ConcreteMediator;
import furnitureDeals.furnituredeals.business.mediator.Mediator;
import furnitureDeals.furnituredeals.model.Rights;
import furnitureDeals.furnituredeals.model.Role;
import furnitureDeals.furnituredeals.model.User;
import furnitureDeals.furnituredeals.model.forms.AddRoleRightForm;
import furnitureDeals.furnituredeals.dao.RightsDAO;
import furnitureDeals.furnituredeals.dao.RoleDAO;
import furnitureDeals.furnituredeals.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("role")
public class RoleControllerGet {

    @Autowired
    private RoleDAO roleDao;

    @Autowired
    private RightsDAO rightsDao;

    @Autowired
    private UserDAO userDao;

    private Mediator m = new ConcreteMediator();

    @RequestMapping(value = "list/{userId}", method = RequestMethod.GET)
    public String listRoles(Model model, @PathVariable int userId){

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

            return m.notifyMediator(this, "error");
        }

        model.addAttribute("title", "Roles");
        model.addAttribute("roles", roleDao.findAll());
        model.addAttribute("userId", userId);

        return m.notifyMediator(this, "listRoles");
    }

    @RequestMapping(value = "view/{userId}/{roleId}", method = RequestMethod.GET)
    public String viewRoleRights(Model model, @PathVariable int userId, @PathVariable int roleId){

        Role myRole = null;
        User myUser = null;
        Optional<User> optionalUser = userDao.findById(userId);
        if(optionalUser.isPresent()){

            myUser = optionalUser.get();
            myRole = myUser.getRole();
        }

        List<Rights> rights = rightsDao.findByMyRight("view roles");
        Rights requiredRight = rights.get(0);

        if(!myRole.getRights().contains(requiredRight)){

            model.addAttribute("title", "Invalid Request!");
            model.addAttribute("messages", "You do not have permission to perform that action!");
            model.addAttribute("userId", userId);

            return m.notifyMediator(this, "error");
        }

        Role role = null;
        Optional<Role> optionalRole = roleDao.findById(roleId);
        if(optionalRole.isPresent()){

            role = optionalRole.get();
        }

        model.addAttribute("title", role.getRole() + " rights");
        model.addAttribute("rights", role.getRights());
        model.addAttribute("userId", userId);
        model.addAttribute("roleId", roleId);

        return m.notifyMediator(this, "viewRoleRights");
    }

    @RequestMapping(value = "add-right/{userId}/{roleId}", method = RequestMethod.GET)
    public String displayAddRightForm(Model model, @PathVariable int userId, @PathVariable int roleId){

        Role myRole = null;
        User myUser = null;
        Optional<User> optionalUser = userDao.findById(userId);
        if(optionalUser.isPresent()){

            myUser = optionalUser.get();
            myRole = myUser.getRole();
        }

        List<Rights> rights = rightsDao.findByMyRight("add right to role");
        Rights requiredRight = rights.get(0);

        if(!myRole.getRights().contains(requiredRight)){

            model.addAttribute("title", "Invalid Request");
            model.addAttribute("messages", "You do not have permission to perform that action!");
            model.addAttribute("userId", userId);

            return m.notifyMediator(this, "error");
        }

        Role role = null;
        Optional<Role> optionalRole = roleDao.findById(roleId);
        if(optionalRole.isPresent()){

            role = optionalRole.get();
        }

        AddRoleRightForm form = new AddRoleRightForm(rightsDao.findAll(), role);

        model.addAttribute("title", "Add Rights");
        model.addAttribute("form", form);
        model.addAttribute("userId", userId);
        model.addAttribute("roleId", roleId);

        return m.notifyMediator(this, "addRight");
    }

    @RequestMapping(value = "remove-right/{userId}/{roleId}", method = RequestMethod.GET)
    public String displayRemoveRightForm(Model model, @PathVariable int userId, @PathVariable int roleId){

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

            return m.notifyMediator(this, "error");
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

        return m.notifyMediator(this, "removeRight");
    }
}
