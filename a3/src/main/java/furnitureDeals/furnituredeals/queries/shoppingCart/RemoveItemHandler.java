package furnitureDeals.furnituredeals.queries.shoppingCart;

import furnitureDeals.furnituredeals.dao.ShoppingCartDAO;
import furnitureDeals.furnituredeals.queries.QueryHandler;
import org.springframework.ui.Model;

public class RemoveItemHandler implements QueryHandler<RemoveItem> {

    @Override
    public void handle(RemoveItem request) {

        int userId = request.getUserId();
        Model model = request.getModel();

        ShoppingCartDAO shoppingCartDao = request.getShoppingCartDao();
        model.addAttribute("title", "Shopping Cart");
        model.addAttribute("userId", userId);
        model.addAttribute("shoppingCart", shoppingCartDao.findByUserId(userId));
    }
}
