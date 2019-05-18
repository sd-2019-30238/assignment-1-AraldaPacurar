package furnitureDeals.furnituredeals.queries.shoppingCart;

import furnitureDeals.furnituredeals.dao.OrdersDAO;
import furnitureDeals.furnituredeals.dao.ShoppingCartDAO;
import furnitureDeals.furnituredeals.dao.UserDAO;
import furnitureDeals.furnituredeals.model.Furniture;
import furnitureDeals.furnituredeals.model.Orders;
import furnitureDeals.furnituredeals.model.ShoppingCart;
import furnitureDeals.furnituredeals.model.User;
import furnitureDeals.furnituredeals.model.builder.OrderBuilder;
import furnitureDeals.furnituredeals.queries.QueryHandler;
import org.springframework.ui.Model;

import java.util.Optional;

public class ProcessSendOrderHandler implements QueryHandler<ProcessSendOrder> {

    @Override
    public void handle(ProcessSendOrder request) {

        int userId = request.getUserId();
        Model model = request.getModel();
        int[] shoppingCartIds = request.getShoppingCardIds();
        ShoppingCartDAO shoppingCartDao = request.getShoppingCartDao();
        OrdersDAO ordersDao = request.getOrdersDao();
        UserDAO userDao = request.getUserDao();

        User user = null;

        Optional<User> optionalUser = userDao.findById(userId);
        if(optionalUser.isPresent()){

            user = optionalUser.get();
        }

        for(int shoppingCartId : shoppingCartIds){

            Furniture furniture = null;
            ShoppingCart shoppingCart = null;
            Optional<ShoppingCart> optionalShoppingCart = shoppingCartDao.findById(shoppingCartId);
            if(optionalShoppingCart.isPresent()){

                shoppingCart = optionalShoppingCart.get();
                furniture = shoppingCart.getFurniture();
            }

            Orders order = new OrderBuilder()
                    .setUsername(user.getUsername())
                    .setFurnitureName(furniture.getName())
                    .setUser(user)
                    .setFurniture(furniture)
                    .setStatus("Pending")
                    .setFeedback("No Feedback")
                    .build();

            order.registerObserver(user);
            ordersDao.save(order);
            shoppingCartDao.delete(shoppingCart);
        }

        model.addAttribute("title", "Order placed successfully!");
        model.addAttribute("messages", "Your order has been placed successfully!");
        model.addAttribute("userId", userId);
    }
}
