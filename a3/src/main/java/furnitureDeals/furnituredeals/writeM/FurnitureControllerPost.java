package furnitureDeals.furnituredeals.writeM;

import furnitureDeals.furnituredeals.business.factory.Discount;
import furnitureDeals.furnituredeals.business.factory.DiscountCreator;
import furnitureDeals.furnituredeals.business.mediator.ConcreteMediator;
import furnitureDeals.furnituredeals.business.mediator.Mediator;
import furnitureDeals.furnituredeals.dao.*;
import furnitureDeals.furnituredeals.model.*;
import furnitureDeals.furnituredeals.model.forms.FilterForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("furniture")
public class FurnitureControllerPost {

    @Autowired
    private FurnitureDAO furnitureDao;

    @Autowired
    private RoleDAO roleDao;

    @Autowired
    private UserDAO userDao;

    @Autowired
    private RightsDAO rightsDao;

    @Autowired
    private FurnitureTypeDAO furnitureTypeDao;

    private Mediator m = new ConcreteMediator();

    @RequestMapping(value = "list/{userId}", method = RequestMethod.POST)
    public String processListFurniture(Model model, @PathVariable int userId, @ModelAttribute @Valid FilterForm filter){

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

                    return m.notifyMediator(this, "listFurniture");
                }
            }
        }

        model.addAttribute("title", "Available Furniture");
        model.addAttribute("userId", userId);
        model.addAttribute("furnitures", filteredFurniture);
        model.addAttribute(new FilterForm());

        return m.notifyMediator(this, "listFurniture");
    }

    @RequestMapping(value = "add/{userId}", method = RequestMethod.POST)
    public String processAddFurniture(@ModelAttribute @Valid Furniture newFurniture, Errors errors, @PathVariable int userId, Model model){

        if(errors.hasErrors()){

            model.addAttribute("title", "Add Furniture");
            return m.notifyMediator(this, "addFurniture");
        }

        newFurniture.setOriginalPrice(newFurniture.getPrice());

        furnitureDao.save(newFurniture);

        return m.notifyMediator(this, "processAddFurniture") + userId;
    }

    @RequestMapping(value = "remove/{userId}", method = RequestMethod.POST)
    public String processRemoveFurniture(@RequestParam int[] furnitureIds, @PathVariable int userId){

        for(int furnitureId : furnitureIds){

            furnitureDao.deleteById(furnitureId);
        }

        return m.notifyMediator(this, "processRemoveFurniture") + userId;
    }

    @RequestMapping(value = "discount/{userId}", method = RequestMethod.POST)
    public String processAddDiscount(@RequestParam int[] furnitureIds, @RequestParam String action, @RequestParam String discount, @PathVariable int userId){

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

        return m.notifyMediator(this, "processAddDiscount") + userId;
    }
}
