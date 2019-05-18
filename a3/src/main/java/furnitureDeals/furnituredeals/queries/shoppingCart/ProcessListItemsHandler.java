package furnitureDeals.furnituredeals.queries.shoppingCart;

import furnitureDeals.furnituredeals.dao.ShoppingCartDAO;
import furnitureDeals.furnituredeals.model.Cart;
import furnitureDeals.furnituredeals.model.ShoppingCart;
import furnitureDeals.furnituredeals.model.decorator.FreeLampCartDecorator;
import furnitureDeals.furnituredeals.model.decorator.FreeRugCartDecorator;
import furnitureDeals.furnituredeals.queries.QueryHandler;
import org.springframework.ui.Model;

import java.util.List;

public class ProcessListItemsHandler implements QueryHandler<ProcessListItems> {

    @Override
    public void handle(ProcessListItems request) {

        int userId = request.getUserId();
        Model model = request.getModel();
        String deal = request.getDeal();
        ShoppingCartDAO shoppingCartDao = request.getShoppingCartDao();

        List<ShoppingCart> shoppingCarts = shoppingCartDao.findByUserId(userId);
        Cart cart = null;
        String deals = "No deals";

        for(ShoppingCart shoppingCart : shoppingCarts){

            if(deal.equals("rug")){
                cart = new FreeRugCartDecorator(shoppingCart);
            }
            else{
                cart = new FreeLampCartDecorator(shoppingCart);
            }

            cart.setDeals("");
            deals = cart.getDeals();

            shoppingCart.setDeals(cart.getDeals());

            shoppingCartDao.save(shoppingCart);
        }

        model.addAttribute("title", "Items");
        model.addAttribute("userId", userId);
        model.addAttribute("shoppingCart", shoppingCarts);
        model.addAttribute("deals", deals);
    }
}
