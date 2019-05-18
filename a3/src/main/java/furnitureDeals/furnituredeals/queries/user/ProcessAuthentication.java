package furnitureDeals.furnituredeals.queries.user;

import furnitureDeals.furnituredeals.dao.UserDAO;
import furnitureDeals.furnituredeals.model.User;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;

public class ProcessAuthentication {

    private Model model;
    private Errors errors;
    private User user;
    private UserDAO userDao;

    public ProcessAuthentication(Model model, Errors errors, User user, UserDAO userDao) {
        this.model = model;
        this.errors = errors;
        this.user = user;
        this.userDao = userDao;
    }

    public Model getModel() {
        return model;
    }

    public Errors getErrors() {
        return errors;
    }

    public User getUser() {
        return user;
    }

    public UserDAO getUserDao() {
        return userDao;
    }
}
