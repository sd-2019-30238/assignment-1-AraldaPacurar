package furnitureDeals.furnituredeals.model.decorator;

import furnitureDeals.furnituredeals.model.Cart;

public class FreeLampCartDecorator extends CartDecorator{


    public FreeLampCartDecorator(Cart decoratedCart) {

        super(decoratedCart);
    }

    public void setDeals(String deals){

        decoratedCart.setDeals("Free shipping!");
        addFreeLampDeal();
    }

    private void addFreeLampDeal(){

        decoratedCart.addDeal("Gift: Free lamp!");
    }
}
