package furnitureDeals.furnituredeals.queries.furniture;

import furnitureDeals.furnituredeals.dao.FurnitureDAO;
import furnitureDeals.furnituredeals.dao.RightsDAO;
import furnitureDeals.furnituredeals.dao.UserDAO;
import org.springframework.ui.Model;

public class ReadAllFurniture {

    private int userId;
    private Model model;
    private UserDAO userDao;
    private RightsDAO rightsDao;
    private FurnitureDAO furnitureDao;

    public ReadAllFurniture(int userId, Model model, UserDAO userDao, RightsDAO rightsDao, FurnitureDAO furnitureDao){
        this.userId = userId;
        this.model = model;
        this.userDao = userDao;
        this.rightsDao = rightsDao;
        this.furnitureDao = furnitureDao;
    }

    public int getUserId(){
        return userId;
    }

    public Model getModel(){
        return model;
    }

    public UserDAO getUserDao() {
        return userDao;
    }

    public RightsDAO getRightsDao() {
        return rightsDao;
    }

    public FurnitureDAO getFurnitureDao() {
        return furnitureDao;
    }
}
