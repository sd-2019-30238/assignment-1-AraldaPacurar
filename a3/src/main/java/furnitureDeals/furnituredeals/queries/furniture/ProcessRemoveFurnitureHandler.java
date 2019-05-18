package furnitureDeals.furnituredeals.queries.furniture;

import furnitureDeals.furnituredeals.dao.FurnitureDAO;
import furnitureDeals.furnituredeals.queries.QueryHandler;

public class ProcessRemoveFurnitureHandler implements QueryHandler<ProcessRemoveFurniture> {

    @Override
    public void handle(ProcessRemoveFurniture request) {

        int[] furnitureIds = request.getFurnitureIds();
        FurnitureDAO furnitureDao = request.getFurnitureDao();

        for(int furnitureId : furnitureIds){

            furnitureDao.deleteById(furnitureId);
        }
    }
}
