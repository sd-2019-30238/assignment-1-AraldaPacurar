package furnitureDeals.furnituredeals.queries.shoppingCart;

import furnitureDeals.furnituredeals.dao.OrdersDAO;
import furnitureDeals.furnituredeals.dao.ShoppingCartDAO;
import furnitureDeals.furnituredeals.dao.UserDAO;
import org.springframework.ui.Model;

public class ProcessRemoveItems {

    private int userId;
    private Model model;
    private int[] shoppingCardIds;
    private ShoppingCartDAO shoppingCartDao;


    public ProcessRemoveItems(int userId, Model model, int[] shoppingCardIds, ShoppingCartDAO shoppingCartDao) {
        this.userId = userId;
        this.model = model;
        this.shoppingCardIds = shoppingCardIds;
        this.shoppingCartDao = shoppingCartDao;
    }

    public int getUserId() {
        return userId;
    }

    public Model getModel() {
        return model;
    }

    public int[] getShoppingCardIds() {
        return shoppingCardIds;
    }

    public ShoppingCartDAO getShoppingCartDao() {
        return shoppingCartDao;
    }
}
