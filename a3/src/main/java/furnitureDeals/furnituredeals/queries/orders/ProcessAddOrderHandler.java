package furnitureDeals.furnituredeals.queries.orders;

import furnitureDeals.furnituredeals.dao.FurnitureDAO;
import furnitureDeals.furnituredeals.dao.ShoppingCartDAO;
import furnitureDeals.furnituredeals.model.Furniture;
import furnitureDeals.furnituredeals.model.ShoppingCart;
import furnitureDeals.furnituredeals.model.User;
import furnitureDeals.furnituredeals.model.builder.ShoppingCartBuilder;
import furnitureDeals.furnituredeals.queries.QueryHandler;
import org.springframework.ui.Model;

import java.util.Optional;

public class ProcessAddOrderHandler implements QueryHandler<ProcessAddOrder> {

    @Override
    public void handle(ProcessAddOrder request) {

        int userId = request.getUserId();
        Model model = request.getModel();
        int[] furnitureIds = request.getFurnitureIds();
        FurnitureDAO furnitureDao = request.getFurnitureDao();
        ShoppingCartDAO shoppingCartDao = request.getShoppingCartDao();

        User user = null;

        for(int furnitureId : furnitureIds){

            Furniture furniture = null;
            Optional<Furniture> optionalFurniture = furnitureDao.findById(furnitureId);
            if(optionalFurniture.isPresent()){

                furniture = optionalFurniture.get();
            }

            ShoppingCart shoppingCart = new ShoppingCartBuilder()
                    .setFurniture(furniture)
                    .setUserId(userId)
                    .setPrice(furniture.getPrice())
                    .setDeals("")
                    .build();

            shoppingCartDao.save(shoppingCart);
        }

        model.addAttribute("title", "Items Added To Shopping Cart");
        model.addAttribute("messages", "Your items have been added successfully to the shopping cart!");
        model.addAttribute("userId", userId);
    }
}
