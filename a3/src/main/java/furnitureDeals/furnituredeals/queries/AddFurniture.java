package furnitureDeals.furnituredeals.queries;

import furnitureDeals.furnituredeals.dao.FurnitureTypeDAO;
import furnitureDeals.furnituredeals.dao.RightsDAO;
import furnitureDeals.furnituredeals.dao.UserDAO;
import org.springframework.ui.Model;

public class AddFurniture {

    private int userId;
    private Model model;
    private UserDAO userDao;
    private RightsDAO rightsDao;
    private FurnitureTypeDAO furnitureTypeDAO;

    public AddFurniture(int userId, Model model, UserDAO userDao, RightsDAO rightsDao, FurnitureTypeDAO furnitureTypeDAO) {
        this.userId = userId;
        this.model = model;
        this.userDao = userDao;
        this.rightsDao = rightsDao;
        this.furnitureTypeDAO = furnitureTypeDAO;
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

    public FurnitureTypeDAO getFurnitureTypeDAO() {
        return furnitureTypeDAO;
    }
}
