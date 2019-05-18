package furnitureDeals.furnituredeals.queries.role;

import furnitureDeals.furnituredeals.dao.RightsDAO;
import furnitureDeals.furnituredeals.dao.RoleDAO;
import furnitureDeals.furnituredeals.dao.UserDAO;
import org.springframework.ui.Model;

public class ListRoles {

    private int userId;
    private Model model;
    private UserDAO userDao;
    private RightsDAO rightsDao;
    private RoleDAO roleDao;

    public ListRoles(int userId, Model model, UserDAO userDao, RightsDAO rightsDao, RoleDAO roleDao) {
        this.userId = userId;
        this.model = model;
        this.userDao = userDao;
        this.rightsDao = rightsDao;
        this.roleDao = roleDao;
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

    public RoleDAO getRoleDao() {
        return roleDao;
    }
}
