package furnitureDeals.furnituredeals.queries.shoppingCart;

import furnitureDeals.furnituredeals.dao.ShoppingCartDAO;
import furnitureDeals.furnituredeals.model.ShoppingCart;
import furnitureDeals.furnituredeals.queries.QueryHandler;
import org.springframework.ui.Model;

import java.util.Optional;

public class ProcessRemoveItemsHandler implements QueryHandler<ProcessRemoveItems> {

    @Override
    public void handle(ProcessRemoveItems request) {

        int userId = request.getUserId();
        Model model = request.getModel();
        int[] shoppingCartIds = request.getShoppingCardIds();
        ShoppingCartDAO shoppingCartDao = request.getShoppingCartDao();

        for(int shoppingCartId : shoppingCartIds){

            ShoppingCart shoppingCart = null;
            Optional<ShoppingCart> optionalShoppingCart = shoppingCartDao.findById(shoppingCartId);
            if(optionalShoppingCart.isPresent()){

                shoppingCart = optionalShoppingCart.get();
            }

            shoppingCartDao.delete(shoppingCart);
        }

        model.addAttribute("title", "Items successfully removed");
        model.addAttribute("messages", "The selected items have been successfully removed!");
        model.addAttribute("userId", userId);
    }
}
