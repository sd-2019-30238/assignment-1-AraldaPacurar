package furnitureDeals.furnituredeals.queries;

import furnitureDeals.furnituredeals.dao.FurnitureDAO;

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
