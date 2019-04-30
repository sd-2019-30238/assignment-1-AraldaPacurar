package furnitureDeals.furnituredeals.readM;

import furnitureDeals.furnituredeals.business.mediator.ConcreteMediator;
import furnitureDeals.furnituredeals.business.mediator.Mediator;
import furnitureDeals.furnituredeals.dao.NotificationDAO;
import furnitureDeals.furnituredeals.model.Rights;
import furnitureDeals.furnituredeals.model.Role;
import furnitureDeals.furnituredeals.model.User;
import furnitureDeals.furnituredeals.dao.RightsDAO;
import furnitureDeals.furnituredeals.dao.RoleDAO;
import furnitureDeals.furnituredeals.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("user")
public class UserControllerGet {

    @Autowired
    private UserDAO userDao;

    @Autowired
    private RoleDAO roleDao;

    @Autowired
    private RightsDAO rightsDao;

    @Autowired
    private NotificationDAO notificationDao;

    private Mediator m = new ConcreteMediator();

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String displayAuthenticationForm(Model model){

        model.addAttribute("title", "Authentication");
        model.addAttribute(new User());

        return "user/authentication";
    }

    @RequestMapping(value = "register", method = RequestMethod.GET)
    public String displayRegisterForm(Model model){

        model.addAttribute("title", "Registration");
        model.addAttribute(new User());

        return m.notifyMediator(this, "userRegistration");
    }

    @RequestMapping(value = "/list/{userId}", method = RequestMethod.GET)
    public String listUsers(Model model, @PathVariable int userId){

        Role myRole = null;
        User user = null;
        Optional<User> optionalUser = userDao.findById(userId);
        if(optionalUser.isPresent()){

            user = optionalUser.get();
            myRole = user.getRole();
        }

        List<Rights> rights = rightsDao.findByMyRight("view users");
        Rights requiredRight = rights.get(0);

        if(!myRole.getRights().contains(requiredRight)){

            model.addAttribute("title", "Invalid request!");
            model.addAttribute("messages", "You do not have privileges to perform this action!");
            model.addAttribute("userId", userId);

            return m.notifyMediator(this, "error");
        }

        model.addAttribute("title", "Registered Users");
        model.addAttribute("users", userDao.findAll());
        model.addAttribute("userId", userId);

        return m.notifyMediator(this, "listUsers");
    }

    @RequestMapping(value = "/listNotifications/{userId}", method = RequestMethod.GET)
    public String listNotifications(Model model, @PathVariable int userId){

        model.addAttribute("title", "View Notifications");
        model.addAttribute("notifications", notificationDao.findByUserId(userId));
        model.addAttribute("userId", userId);

        return m.notifyMediator(this, "listNotifications");
    }

    @RequestMapping(value = "remove/{userId}", method = RequestMethod.GET)
    public String displayRemoveUser(Model model, @PathVariable int userId){

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

            return m.notifyMediator(this, "error");
        }

        List<Role> roles = roleDao.findByRole("employee");

        model.addAttribute("title", "Fire Employee");
        model.addAttribute("employees", userDao.findByRoleId(roles.get(0).getId()));
        model.addAttribute("userId", userId);

        return m.notifyMediator(this, "removeUser");
    }

    @RequestMapping(value = "hire/{userId}", method = RequestMethod.GET)
    public String displayHireEmployeeForm(Model model, @PathVariable int userId){

        Role myRole = null;
        User user = null;
        Optional<User> optionalUser = userDao.findById(userId);
        if(optionalUser.isPresent()){

            user = optionalUser.get();
            myRole = user.getRole();
        }

        List<Rights> rights = rightsDao.findByMyRight("hire");
        Rights requiredRight = rights.get(0);

        if(!myRole.getRights().contains(requiredRight)){

            model.addAttribute("title", "Invalid request!");
            model.addAttribute("messages", "You do not have privileges to perform this action!");
            model.addAttribute("userId", userId);

            return m.notifyMediator(this, "error");
        }

        model.addAttribute("title", "Hire Employee");
        model.addAttribute(new User());
        model.addAttribute("userId", userId);

        return m.notifyMediator(this, "hireUser");
    }
}
