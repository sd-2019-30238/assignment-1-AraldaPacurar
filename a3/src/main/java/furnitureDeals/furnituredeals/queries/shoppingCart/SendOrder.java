package furnitureDeals.furnituredeals.queries.shoppingCart;

import furnitureDeals.furnituredeals.dao.ShoppingCartDAO;
import org.springframework.ui.Model;

public class SendOrder {

    private int userId;
    private Model model;
    private ShoppingCartDAO shoppingCartDao;

    public SendOrder(int userId, Model model, ShoppingCartDAO shoppingCartDao) {
        this.userId = userId;
        this.model = model;
        this.shoppingCartDao = shoppingCartDao;
    }

    public int getUserId() {
        return userId;
    }

    public Model getModel() {
        return model;
    }

    public ShoppingCartDAO getShoppingCartDao() {
        return shoppingCartDao;
    }
}
