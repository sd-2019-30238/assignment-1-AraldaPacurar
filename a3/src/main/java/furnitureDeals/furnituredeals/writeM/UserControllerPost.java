package furnitureDeals.furnituredeals.writeM;

import furnitureDeals.furnituredeals.business.mediator.ConcreteMediator;
import furnitureDeals.furnituredeals.business.mediator.Mediator;
import furnitureDeals.furnituredeals.business.service.PasswordEncoder;
import furnitureDeals.furnituredeals.business.service.UserValidator;
import furnitureDeals.furnituredeals.dao.NotificationDAO;
import furnitureDeals.furnituredeals.model.Role;
import furnitureDeals.furnituredeals.model.User;
import furnitureDeals.furnituredeals.dao.RightsDAO;
import furnitureDeals.furnituredeals.dao.RoleDAO;
import furnitureDeals.furnituredeals.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
    public String processAuthentication(@ModelAttribute User newUser, Errors errors, Model model){

        if(errors.hasErrors()){

            model.addAttribute("title", "Authentication");
            return m.notifyMediator(this, "userAuthentication");
        }

        List<User> user = userDao.findByUsername(newUser.getUsername());
        if(user.isEmpty()){

            model.addAttribute("title", "Login Error!");
            model.addAttribute("messages", "Invalid username or password!");

            return m.notifyMediator(this, "userMessage");
        }

        User myUser = user.get(0);
        String foundUserEncodedPassword = myUser.getPassword();
        PasswordEncoder passwordEncoder = new PasswordEncoder(newUser.getPassword());

        if (!foundUserEncodedPassword.equals(passwordEncoder.getEncodedPassword())) {

            model.addAttribute("title", "Login Error!");
            model.addAttribute("messages", "Invalid username or password!");

            return m.notifyMediator(this, "userMessage");
        }

        model.addAttribute("title", "Login Successful!");
        model.addAttribute("rights", myUser.getRole().getRights());
        model.addAttribute("userId", myUser.getId());

        return m.notifyMediator(this, "processUserAuthentication");
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public String processRegistrationForm(@ModelAttribute @Valid User newUser, Errors errors, Model model){

        if(errors.hasErrors()){

            model.addAttribute("title", "Registration");
            return m.notifyMediator(this, "userRegistration");
        }

        UserValidator userValidator = new UserValidator(newUser);
        if(!userValidator.validate()){

            model.addAttribute("title", "Registration Error!");
            model.addAttribute("messages", userValidator.getErrors());

            return m.notifyMediator(this, "userMessage");
        }

        if(!userDao.findByUsername(newUser.getUsername()).isEmpty()){

            model.addAttribute("title","Registration Error!");
            model.addAttribute("messages", "Username is already taken!");

            return m.notifyMediator(this, "userMessage");
        }

        PasswordEncoder passwordEncoder = new PasswordEncoder(newUser.getPassword());
        newUser.setPassword(passwordEncoder.getEncodedPassword());

        List<Role> roles = roleDao.findByRole("customer");
        newUser.setRole(roles.get(0));

        userDao.save(newUser);

        model.addAttribute("title", "Registration Completed!");

        return m.notifyMediator(this, "userMessage");
    }

    @RequestMapping(value = "remove/{userId}", method = RequestMethod.POST)
    public String processRemoveUser(@RequestParam int[] employeeIds, @PathVariable int userId){

        for(int employeeId : employeeIds){

            userDao.deleteById(employeeId);
        }

        return m.notifyMediator(this, "processRemoveUser") + userId;
    }

    @RequestMapping(value = "hire/{userId}", method = RequestMethod.POST)
    public String processHireEmployeeForm(@ModelAttribute @Valid User newUser, Errors errors, Model model, @PathVariable int userId){

        if(errors.hasErrors()){

            model.addAttribute("title", "Hire Employee");
            model.addAttribute("userId", userId);
            return m.notifyMediator(this, "hireUser");
        }

        UserValidator userValidator = new UserValidator(newUser);
        if(!userValidator.validate()){

            model.addAttribute("title", "Registration Error!");
            model.addAttribute("messages", userValidator.getErrors());
            model.addAttribute("userId", userId);

            return m.notifyMediator(this, "error");
        }

        if(!userDao.findByUsername(newUser.getUsername()).isEmpty()){

            model.addAttribute("title","Registration Error!");
            model.addAttribute("messages", "Username is already taken!");
            model.addAttribute("userId", userId);

            return m.notifyMediator(this, "error");
        }

        PasswordEncoder passwordEncoder = new PasswordEncoder(newUser.getPassword());
        newUser.setPassword(passwordEncoder.getEncodedPassword());

        List<Role> roles = roleDao.findByRole("employee");
        newUser.setRole(roles.get(0));

        userDao.save(newUser);

        model.addAttribute("title", "Employee Hired!");
        model.addAttribute("userId", userId);

        return m.notifyMediator(this, "userMessage");
    }

}
