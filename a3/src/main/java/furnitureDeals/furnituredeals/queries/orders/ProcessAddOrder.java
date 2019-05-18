package furnitureDeals.furnituredeals.queries.orders;

import furnitureDeals.furnituredeals.dao.FurnitureDAO;
import furnitureDeals.furnituredeals.dao.ShoppingCartDAO;
import org.springframework.ui.Model;

public class ProcessAddOrder {

    private int userId;
    private Model model;
    private int[] furnitureIds;
    private FurnitureDAO furnitureDao;
    private ShoppingCartDAO shoppingCartDao;

    public ProcessAddOrder(int userId, Model model, int[] furnitureIds, FurnitureDAO furnitureDao, ShoppingCartDAO shoppingCartDao) {
        this.userId = userId;
        this.model = model;
        this.furnitureIds = furnitureIds;
        this.furnitureDao = furnitureDao;
        this.shoppingCartDao = shoppingCartDao;
    }

    public int getUserId() {
        return userId;
    }

    public Model getModel() {
        return model;
    }

    public int[] getFurnitureIds() {
        return furnitureIds;
    }

    public FurnitureDAO getFurnitureDao() {
        return furnitureDao;
    }

    public ShoppingCartDAO getShoppingCartDao() {
        return shoppingCartDao;
    }
}
