package furnitureDeals.furnituredeals.queries;

import furnitureDeals.furnituredeals.dao.FurnitureDAO;
import furnitureDeals.furnituredeals.dao.FurnitureTypeDAO;
import furnitureDeals.furnituredeals.model.Furniture;
import furnitureDeals.furnituredeals.model.FurnitureType;
import furnitureDeals.furnituredeals.model.forms.FilterForm;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

public class ProcessReadAllFurnitureHandler implements QueryHandler<ProcessReadAllFurniture> {

    @Override
    public void handle(ProcessReadAllFurniture request) {

        int userId = request.getUserId();
        Model model = request.getModel();
        FilterForm filter= request.getFilterForm();
        FurnitureDAO furnitureDao = request.getFurnitureDao();
        FurnitureTypeDAO furnitureTypeDao = request.getFurnitureTypeDao();

        List<Furniture> filteredFurniture = new ArrayList<>();
        filteredFurniture = furnitureDao.findByName(filter.getFilter());

        if(filteredFurniture.isEmpty()){

            List<FurnitureType> furnitureType = furnitureTypeDao.findByName(filter.getFilter());
            if(!furnitureType.isEmpty()){

                filteredFurniture = furnitureDao.findByFurnitureTypeId(furnitureType.get(0).getId());
            }

            if(filteredFurniture.isEmpty()){

                try {
                    filteredFurniture = furnitureDao.findByPrice(Integer.parseInt(filter.getFilter()));
                }
                catch (NumberFormatException e){

                    model.addAttribute("title", "Available Furniture");
                    model.addAttribute("userId", userId);
                    model.addAttribute("furnitures", filteredFurniture);
                    model.addAttribute(new FilterForm());

                    return;
                }
            }
        }

        model.addAttribute("title", "Available Furniture");
        model.addAttribute("userId", userId);
        model.addAttribute("furnitures", filteredFurniture);
        model.addAttribute(new FilterForm());
    }
}
