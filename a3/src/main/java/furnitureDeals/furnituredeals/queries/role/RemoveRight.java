package furnitureDeals.furnituredeals.queries.role;

import furnitureDeals.furnituredeals.dao.RightsDAO;
import furnitureDeals.furnituredeals.dao.RoleDAO;
import furnitureDeals.furnituredeals.dao.UserDAO;
import org.springframework.ui.Model;

public class RemoveRight {

    private int userId;
    private Model model;
    private int roleId;
    private UserDAO userDao;
    private RoleDAO roleDao;
    private RightsDAO rightsDao;

    public RemoveRight(int userId, Model model, int roleId, UserDAO userDao, RoleDAO roleDao, RightsDAO rightsDao) {
        this.userId = userId;
        this.model = model;
        this.roleId = roleId;
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.rightsDao = rightsDao;
    }

    public int getUserId() {
        return userId;
    }

    public Model getModel() {
        return model;
    }

    public int getRoleId() {
        return roleId;
    }

    public UserDAO getUserDao() {
        return userDao;
    }

    public RoleDAO getRoleDao() {
        return roleDao;
    }

    public RightsDAO getRightsDao() {
        return rightsDao;
    }
}
