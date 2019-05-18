package furnitureDeals.furnituredeals.writeM;

import furnitureDeals.furnituredeals.business.mediator.ConcreteMediator;
import furnitureDeals.furnituredeals.business.mediator.Mediator;
import furnitureDeals.furnituredeals.dao.NotificationDAO;
import furnitureDeals.furnituredeals.model.User;
import furnitureDeals.furnituredeals.dao.RightsDAO;
import furnitureDeals.furnituredeals.dao.RoleDAO;
import furnitureDeals.furnituredeals.dao.UserDAO;
import furnitureDeals.furnituredeals.queries.user.ProcessAuthentication;
import furnitureDeals.furnituredeals.queries.user.ProcessHireEmployee;
import furnitureDeals.furnituredeals.queries.user.ProcessRegistration;
import furnitureDeals.furnituredeals.queries.user.ProcessRemoveUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.InvocationTargetException;

@Controller
@RequestMapping("user")
public class UserControllerPost {

    @Autowired
    private UserDAO userDao;

    @Autowired
    private RoleDAO roleDao;

    @Autowired
    private RightsDAO rightsDao;

    @Autowired
    private NotificationDAO notificationDao;

    private Mediator m = new ConcreteMediator();

    @RequestMapping(value="", method = RequestMethod.POST)
    public String processAuthentication(@ModelAttribute User newUser, Errors errors, Model model) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        ProcessAuthentication query = new ProcessAuthentication(model, errors, newUser, userDao);
        m.handle(query);
        return "user/menu";
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public String processRegistrationForm(@ModelAttribute @Valid User newUser, Errors errors, Model model) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        ProcessRegistration query = new ProcessRegistration(model, errors, newUser, userDao, roleDao);
        m.handle(query);
        return "user/message";
    }

    @RequestMapping(value = "remove/{userId}", method = RequestMethod.POST)
    public String processRemoveUser(@RequestParam int[] employeeIds, @PathVariable int userId) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        ProcessRemoveUser query = new ProcessRemoveUser(employeeIds, userDao);
        m.handle(query);
        return "redirect:/user/list/" + userId;
    }

    @RequestMapping(value = "hire/{userId}", method = RequestMethod.POST)
    public String processHireEmployeeForm(@ModelAttribute @Valid User newUser, Errors errors, Model model, @PathVariable int userId) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        ProcessHireEmployee query = new ProcessHireEmployee(userId, model, errors, newUser, userDao, roleDao);
        m.handle(query);
        return "user/message";
    }

}
