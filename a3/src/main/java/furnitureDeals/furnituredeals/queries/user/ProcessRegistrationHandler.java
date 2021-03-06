package furnitureDeals.furnituredeals.queries.user;

import furnitureDeals.furnituredeals.business.service.PasswordEncoder;
import furnitureDeals.furnituredeals.business.service.UserValidator;
import furnitureDeals.furnituredeals.dao.RoleDAO;
import furnitureDeals.furnituredeals.dao.UserDAO;
import furnitureDeals.furnituredeals.model.Role;
import furnitureDeals.furnituredeals.model.User;
import furnitureDeals.furnituredeals.queries.QueryHandler;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;

import java.util.List;

public class ProcessRegistrationHandler implements QueryHandler<ProcessRegistration> {

    @Override
    public void handle(ProcessRegistration request) {

        Model model = request.getModel();
        Errors errors = request.getErrors();
        User newUser = request.getUser();
        UserDAO userDao = request.getUserDao();
        RoleDAO roleDao = request.getRoleDao();

        if(errors.hasErrors()){

            model.addAttribute("title", "Registration");
            return ;
        }

        UserValidator userValidator = new UserValidator(newUser);
        if(!userValidator.validate()){

            model.addAttribute("title", "Registration Error!");
            model.addAttribute("messages", userValidator.getErrors());

            return ;
        }

        if(!userDao.findByUsername(newUser.getUsername()).isEmpty()){

            model.addAttribute("title","Registration Error!");
            model.addAttribute("messages", "Username is already taken!");

            return ;
        }

        PasswordEncoder passwordEncoder = new PasswordEncoder(newUser.getPassword());
        newUser.setPassword(passwordEncoder.getEncodedPassword());

        List<Role> roles = roleDao.findByRole("customer");
        newUser.setRole(roles.get(0));

        userDao.save(newUser);

        model.addAttribute("title", "Registration Completed!");
    }
}
