package furnitureDeals.furnituredeals.model.builder;

import furnitureDeals.furnituredeals.model.Furniture;
import furnitureDeals.furnituredeals.model.Orders;
import furnitureDeals.furnituredeals.model.User;

public class OrderBuilder {

    private Orders order;

    public OrderBuilder(){

        order = new Orders();
    }

    public OrderBuilder setUsername(String username){

        order.setUsername(username);
        return this;
    }

    public OrderBuilder setFurnitureName(String furnitureName){

        order.setFurnitureName(furnitureName);
        return this;
    }

    public OrderBuilder setUser(User user){

        order.setUser(user);
        return this;
    }

    public OrderBuilder setFurniture(Furniture furniture){

        order.setFurniture(furniture);
        return this;
    }

    public OrderBuilder setStatus(String status){
        order.setStatus(status);
        return this;
    }

    public OrderBuilder setFeedback(String feedback){
        order.setFeedback(feedback);
        return this;
    }

    public Orders build(){

        return order;
    }
}
