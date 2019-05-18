package furnitureDeals.furnituredeals.queries;

import furnitureDeals.furnituredeals.business.factory.Discount;
import furnitureDeals.furnituredeals.business.factory.DiscountCreator;
import furnitureDeals.furnituredeals.dao.FurnitureDAO;
import furnitureDeals.furnituredeals.model.Furniture;

import java.util.Optional;

public class ProcessAddDiscountHandler implements QueryHandler<ProcessAddDiscount> {

    @Override
    public void handle(ProcessAddDiscount request) {

        int[] furnitureIds = request.getFurnitureIds();
        String discount = request.getDiscount();
        String action = request.getAction();
        FurnitureDAO furnitureDao = request.getFurnitureDao();

        for(int furnitureId: furnitureIds){

            Optional<Furniture> optionalFurniture = furnitureDao.findById(furnitureId);
            Furniture furniture = null;
            if(optionalFurniture.isPresent()){
                furniture = optionalFurniture.get();
            }

            Discount myDiscount = new DiscountCreator().createDiscount(discount);

            if(action.equals("Add Discount")) {

                furniture.setPrice(myDiscount.addDiscount(furniture.getPrice()));
                furniture.setDescription(myDiscount.toString());
            }
            else if(action.equals("Remove Discount")){

                furniture.setPrice(myDiscount.removeDiscount(furniture.getPrice(), furniture.getOriginalPrice()));
                furniture.setDescription("No discount");
            }

            furnitureDao.save(furniture);
        }
    }
}
