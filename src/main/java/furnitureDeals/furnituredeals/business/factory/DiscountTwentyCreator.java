package furnitureDeals.furnituredeals.business.factory;

public class DiscountTwentyCreator extends DiscountCreator {

    public Discount createDiscount() {
        return new DiscountTwenty();
    }
}
