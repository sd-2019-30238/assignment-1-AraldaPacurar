package furnitureDeals.furnituredeals.queries;

import furnitureDeals.furnituredeals.dao.FurnitureDAO;

public class ProcessRemoveFurniture {

    private int[] furnitureIds;
    private FurnitureDAO furnitureDao;

    public ProcessRemoveFurniture(int[] furnitureIds, FurnitureDAO furnitureDao) {
        this.furnitureIds = furnitureIds;
        this.furnitureDao = furnitureDao;
    }

    public int[] getFurnitureIds() {
        return furnitureIds;
    }

    public FurnitureDAO getFurnitureDao() {
        return furnitureDao;
    }
}
