package furnitureDeals.furnituredeals.model.decorator;

import furnitureDeals.furnituredeals.model.Cart;

public class FreeRugCartDecorator extends CartDecorator {

    public FreeRugCartDecorator(Cart decoratedCart) {

        super(decoratedCart);
    }

    public void setDeals(String deals){

        decoratedCart.setDeals("Free shipping!");
        addFreeRugDeal();
    }

    private void addFreeRugDeal(){

        decoratedCart.addDeal("Gift: Free rug!");
    }
}
