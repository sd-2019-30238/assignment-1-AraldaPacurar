package furnitureDeals.furnituredeals.queries.user;

import furnitureDeals.furnituredeals.dao.RightsDAO;
import furnitureDeals.furnituredeals.dao.UserDAO;
import org.springframework.ui.Model;

public class HireEmployee {

    private int userId;
    private Model model;
    private UserDAO userDao;
    private RightsDAO rightsDao;

    public HireEmployee(int userId, Model model, UserDAO userDao, RightsDAO rightsDao) {
        this.userId = userId;
        this.model = model;
        this.userDao = userDao;
        this.rightsDao = rightsDao;
    }

    public int getUserId() {
        return userId;
    }

    public Model getModel() {
        return model;
    }

    public UserDAO getUserDao() {
        return userDao;
    }

    public RightsDAO getRightsDao() {
        return rightsDao;
    }
}
