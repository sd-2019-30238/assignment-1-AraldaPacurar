package furnitureDeals.furnituredeals.queries.user;

import furnitureDeals.furnituredeals.business.service.PasswordEncoder;
import furnitureDeals.furnituredeals.dao.UserDAO;
import furnitureDeals.furnituredeals.model.User;
import furnitureDeals.furnituredeals.queries.QueryHandler;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;

import java.util.List;

public class ProcessAuthenticationHandler implements QueryHandler<ProcessAuthentication> {

    @Override
    public void handle(ProcessAuthentication request) {

        Model model = request.getModel();
        Errors errors = request.getErrors();
        User newUser = request.getUser();
        UserDAO userDao = request.getUserDao();

        if(errors.hasErrors()){

            model.addAttribute("title", "Authentication");
            return ;
        }

        List<User> user = userDao.findByUsername(newUser.getUsername());
        if(user.isEmpty()){

            model.addAttribute("title", "Login Error!");
            model.addAttribute("messages", "Invalid username or password!");

            return ;
        }

        User myUser = user.get(0);
        String foundUserEncodedPassword = myUser.getPassword();
        PasswordEncoder passwordEncoder = new PasswordEncoder(newUser.getPassword());

        if (!foundUserEncodedPassword.equals(passwordEncoder.getEncodedPassword())) {

            model.addAttribute("title", "Login Error!");
            model.addAttribute("messages", "Invalid username or password!");

            return ;
        }

        model.addAttribute("title", "Login Successful!");
        model.addAttribute("rights", myUser.getRole().getRights());
        model.addAttribute("userId", myUser.getId());
    }
}
