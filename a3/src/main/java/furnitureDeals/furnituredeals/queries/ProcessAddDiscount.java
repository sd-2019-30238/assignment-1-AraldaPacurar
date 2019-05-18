package furnitureDeals.furnituredeals.queries;

import furnitureDeals.furnituredeals.dao.FurnitureDAO;

public class ProcessAddDiscount {

    private int[] furnitureIds;
    private String discount;
    private String action;
    private FurnitureDAO furnitureDao;

    public ProcessAddDiscount(int[] furnitureIds, String discount, String action, FurnitureDAO furnitureDao) {
        this.furnitureIds = furnitureIds;
        this.discount = discount;
        this.action = action;
        this.furnitureDao = furnitureDao;
    }

    public int[] getFurnitureIds() {
        return furnitureIds;
    }

    public String getDiscount() {
        return discount;
    }

    public String getAction() {
        return action;
    }

    public FurnitureDAO getFurnitureDao() {
        return furnitureDao;
    }
}
