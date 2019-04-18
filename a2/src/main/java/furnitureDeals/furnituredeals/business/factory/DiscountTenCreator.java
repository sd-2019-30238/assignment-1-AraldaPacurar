package furnitureDeals.furnituredeals.business.factory;

public class DiscountTenCreator extends DiscountCreator {

    public Discount createDiscount() {

        return new DiscountTen();
    }
}
