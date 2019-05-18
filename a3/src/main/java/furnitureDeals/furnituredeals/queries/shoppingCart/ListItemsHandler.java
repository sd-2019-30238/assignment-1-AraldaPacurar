package furnitureDeals.furnituredeals.queries.shoppingCart;

import furnitureDeals.furnituredeals.dao.ShoppingCartDAO;
import furnitureDeals.furnituredeals.model.ShoppingCart;
import furnitureDeals.furnituredeals.queries.QueryHandler;
import org.springframework.ui.Model;

import java.util.List;

public class ListItemsHandler implements QueryHandler<ListItems> {

    @Override
    public void handle(ListItems request) {

        int userId = request.getUserId();
        Model model = request.getModel();
        ShoppingCartDAO shoppingCartDao = request.getShoppingCartDao();

        List<ShoppingCart> shoppingCarts = shoppingCartDao.findByUserId(userId);
        String deals = "No deals";

        if(shoppingCarts.size() > 0){
            deals = shoppingCarts.get(0).getDeals();
        }

        model.addAttribute("title", "Items");
        model.addAttribute("userId", userId);
        model.addAttribute("shoppingCart", shoppingCarts);
        model.addAttribute("deals", deals);
    }
}
