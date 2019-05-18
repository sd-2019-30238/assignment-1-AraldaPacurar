package furnitureDeals.furnituredeals.readM;

import furnitureDeals.furnituredeals.business.mediator.ConcreteMediator;
import furnitureDeals.furnituredeals.business.mediator.Mediator;
import furnitureDeals.furnituredeals.dao.NotificationDAO;
import furnitureDeals.furnituredeals.dao.RightsDAO;
import furnitureDeals.furnituredeals.dao.RoleDAO;
import furnitureDeals.furnituredeals.dao.UserDAO;
import furnitureDeals.furnituredeals.queries.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;

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
    public String displayAuthenticationForm(Model model) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        DisplayAuthenticationForm query = new DisplayAuthenticationForm(model);
        m.handle(query);
        return "user/authentication";
    }

    @RequestMapping(value = "register", method = RequestMethod.GET)
    public String displayRegisterForm(Model model) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        DisplayRegisterForm query = new DisplayRegisterForm(model);
        m.handle(query);
        return "user/registration";
    }

    @RequestMapping(value = "/list/{userId}", method = RequestMethod.GET)
    public String listUsers(Model model, @PathVariable int userId) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        ListUsers query = new ListUsers(userId, model, userDao, rightsDao);
        m.handle(query);
        return "user/list";
    }

    @RequestMapping(value = "/listNotifications/{userId}", method = RequestMethod.GET)
    public String listNotifications(Model model, @PathVariable int userId) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        ListNotifications query = new ListNotifications(userId, model, notificationDao);
        m.handle(query);
        return "user/listNotifications";
    }

    @RequestMapping(value = "remove/{userId}", method = RequestMethod.GET)
    public String displayRemoveUser(Model model, @PathVariable int userId) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        RemoveUser query = new RemoveUser(userId, model, userDao, rightsDao, roleDao);
        m.handle(query);
        return "user/remove";
    }

    @RequestMapping(value = "hire/{userId}", method = RequestMethod.GET)
    public String displayHireEmployeeForm(Model model, @PathVariable int userId) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        HireEmployee query = new HireEmployee(userId, model, userDao, rightsDao);
        m.handle(query);
        return "user/hire";
    }
}
