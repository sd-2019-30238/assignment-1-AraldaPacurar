package furnitureDeals.furnituredeals.model.builder;

import furnitureDeals.furnituredeals.model.Furniture;
import furnitureDeals.furnituredeals.model.ShoppingCart;

public class ShoppingCartBuilder {

    private ShoppingCart shoppingCart;

    public ShoppingCartBuilder(){

        shoppingCart = new ShoppingCart();
    }

    public ShoppingCartBuilder setFurniture(Furniture furniture){

        shoppingCart.setFurniture(furniture);
        return this;
    }

    public ShoppingCartBuilder setUserId(int userId){

        shoppingCart.setUserId(userId);
        return this;
    }

    public ShoppingCartBuilder setPrice(int price){

        shoppingCart.setPrice(price);
        return this;
    }

    public ShoppingCartBuilder setDeals(String deal){

        shoppingCart.setDeals(deal);
        return this;
    }

    public ShoppingCart build(){

        return shoppingCart;
    }

}
