package furnitureDeals.furnituredeals.model.decorator;

import furnitureDeals.furnituredeals.model.Cart;

public class CartDecorator implements Cart {

    protected Cart decoratedCart;

    public CartDecorator(Cart decoratedCart){

        this.decoratedCart = decoratedCart;
    }

    @Override
    public void setDeals(String deal) {

        decoratedCart.setDeals(deal);
    }

    @Override
    public void addDeal(String deal) {
        decoratedCart.addDeal(deal);
    }

    @Override
    public String getDeals() {

        return decoratedCart.getDeals();
    }
}
