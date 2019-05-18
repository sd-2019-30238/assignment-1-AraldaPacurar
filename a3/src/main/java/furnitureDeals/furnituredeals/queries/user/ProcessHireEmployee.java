package furnitureDeals.furnituredeals.queries.user;

import furnitureDeals.furnituredeals.dao.RoleDAO;
import furnitureDeals.furnituredeals.dao.UserDAO;
import furnitureDeals.furnituredeals.model.User;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;

public class ProcessHireEmployee {

    private int userId;
    private Model model;
    private Errors errors;
    private User user;
    private UserDAO userDao;
    private RoleDAO roleDao;

    public ProcessHireEmployee(int userId, Model model, Errors errors, User user, UserDAO userDao, RoleDAO roleDao) {
        this.userId = userId;
        this.model = model;
        this.errors = errors;
        this.user = user;
        this.userDao = userDao;
        this.roleDao = roleDao;
    }

    public int getUserId() {
        return userId;
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

    public RoleDAO getRoleDao() {
        return roleDao;
    }
}
