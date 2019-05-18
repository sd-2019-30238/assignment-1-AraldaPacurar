package furnitureDeals.furnituredeals.queries;

import furnitureDeals.furnituredeals.dao.FurnitureDAO;
import furnitureDeals.furnituredeals.model.Furniture;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;

public class ProcessAddFurnitureHandler implements QueryHandler<ProcessAddFurniture> {

    @Override
    public void handle(ProcessAddFurniture request) {

        int userId = request.getUserId();
        Model model = request.getModel();
        Errors errors = request.getErrors();
        Furniture newFurniture = request.getNewFurniture();
        FurnitureDAO furnitureDao = request.getFurnitureDao();

        if(errors.hasErrors()){

            model.addAttribute("title", "Add Furniture");
            return;
        }

        newFurniture.setOriginalPrice(newFurniture.getPrice());

        furnitureDao.save(newFurniture);
    }
}
