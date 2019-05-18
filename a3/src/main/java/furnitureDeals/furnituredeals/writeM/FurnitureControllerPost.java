package furnitureDeals.furnituredeals.writeM;

import furnitureDeals.furnituredeals.business.factory.Discount;
import furnitureDeals.furnituredeals.business.factory.DiscountCreator;
import furnitureDeals.furnituredeals.business.mediator.ConcreteMediator;
import furnitureDeals.furnituredeals.business.mediator.Mediator;
import furnitureDeals.furnituredeals.dao.*;
import furnitureDeals.furnituredeals.model.*;
import furnitureDeals.furnituredeals.model.forms.FilterForm;
import furnitureDeals.furnituredeals.queries.ProcessAddDiscount;
import furnitureDeals.furnituredeals.queries.ProcessAddFurniture;
import furnitureDeals.furnituredeals.queries.ProcessReadAllFurniture;
import furnitureDeals.furnituredeals.queries.ProcessRemoveFurniture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.InvocationTargetException;
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
    public String processListFurniture(Model model, @PathVariable int userId, @ModelAttribute @Valid FilterForm filter) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        ProcessReadAllFurniture query = new ProcessReadAllFurniture(userId, model, filter, furnitureDao, furnitureTypeDao);
        m.handle(query);

        /*
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
        */

        return "furniture/list";
    }

    @RequestMapping(value = "add/{userId}", method = RequestMethod.POST)
    public String processAddFurniture(@ModelAttribute @Valid Furniture newFurniture, Errors errors, @PathVariable int userId, Model model) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        ProcessAddFurniture query = new ProcessAddFurniture(userId, model, errors, newFurniture, furnitureDao);
        m.handle(query);

        /*
        if(errors.hasErrors()){

            model.addAttribute("title", "Add Furniture");
            return m.notifyMediator(this, "addFurniture");
        }

        newFurniture.setOriginalPrice(newFurniture.getPrice());

        furnitureDao.save(newFurniture);
        */

        return "redirect:/furniture/list/" + userId;
    }

    @RequestMapping(value = "remove/{userId}", method = RequestMethod.POST)
    public String processRemoveFurniture(@RequestParam int[] furnitureIds, @PathVariable int userId) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        ProcessRemoveFurniture query = new ProcessRemoveFurniture(furnitureIds, furnitureDao);
        m.handle(query);

        /*
        for(int furnitureId : furnitureIds){

            furnitureDao.deleteById(furnitureId);
        }
        */

        return "redirect:/furniture/list/" + userId;
    }

    @RequestMapping(value = "discount/{userId}", method = RequestMethod.POST)
    public String processAddDiscount(@RequestParam int[] furnitureIds, @RequestParam String action, @RequestParam String discount, @PathVariable int userId) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        ProcessAddDiscount query = new ProcessAddDiscount(furnitureIds, discount, action, furnitureDao);
        m.handle(query);

        /*
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
        */

        return "redirect:/furniture/list/" + userId;
    }
}
