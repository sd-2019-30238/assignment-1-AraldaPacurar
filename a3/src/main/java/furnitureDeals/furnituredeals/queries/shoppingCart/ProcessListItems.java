package furnitureDeals.furnituredeals.queries.shoppingCart;

import furnitureDeals.furnituredeals.dao.ShoppingCartDAO;
import org.springframework.ui.Model;

public class ProcessListItems {

    private int userId;
    private Model model;
    private String deal;
    private ShoppingCartDAO shoppingCartDao;

    public ProcessListItems(int userId, Model model, String deal, ShoppingCartDAO shoppingCartDao) {
        this.userId = userId;
        this.model = model;
        this.deal = deal;
        this.shoppingCartDao = shoppingCartDao;
    }

    public int getUserId() {
        return userId;
    }

    public Model getModel() {
        return model;
    }

    public String getDeal() {
        return deal;
    }

    public ShoppingCartDAO getShoppingCartDao() {
        return shoppingCartDao;
    }
}
