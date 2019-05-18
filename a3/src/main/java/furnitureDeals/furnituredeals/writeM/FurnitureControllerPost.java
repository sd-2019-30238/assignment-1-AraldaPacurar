package furnitureDeals.furnituredeals.writeM;

import furnitureDeals.furnituredeals.business.mediator.ConcreteMediator;
import furnitureDeals.furnituredeals.business.mediator.Mediator;
import furnitureDeals.furnituredeals.dao.*;
import furnitureDeals.furnituredeals.model.*;
import furnitureDeals.furnituredeals.model.forms.FilterForm;
import furnitureDeals.furnituredeals.queries.furniture.ProcessAddDiscount;
import furnitureDeals.furnituredeals.queries.furniture.ProcessAddFurniture;
import furnitureDeals.furnituredeals.queries.furniture.ProcessReadAllFurniture;
import furnitureDeals.furnituredeals.queries.furniture.ProcessRemoveFurniture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.InvocationTargetException;

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
        return "furniture/list";
    }

    @RequestMapping(value = "add/{userId}", method = RequestMethod.POST)
    public String processAddFurniture(@ModelAttribute @Valid Furniture newFurniture, Errors errors, @PathVariable int userId, Model model) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        ProcessAddFurniture query = new ProcessAddFurniture(userId, model, errors, newFurniture, furnitureDao);
        m.handle(query);
        return "redirect:/furniture/list/" + userId;
    }

    @RequestMapping(value = "remove/{userId}", method = RequestMethod.POST)
    public String processRemoveFurniture(@RequestParam int[] furnitureIds, @PathVariable int userId) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        ProcessRemoveFurniture query = new ProcessRemoveFurniture(furnitureIds, furnitureDao);
        m.handle(query);
        return "redirect:/furniture/list/" + userId;
    }

    @RequestMapping(value = "discount/{userId}", method = RequestMethod.POST)
    public String processAddDiscount(@RequestParam int[] furnitureIds, @RequestParam String action, @RequestParam String discount, @PathVariable int userId) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        ProcessAddDiscount query = new ProcessAddDiscount(furnitureIds, discount, action, furnitureDao);
        m.handle(query);
        return "redirect:/furniture/list/" + userId;
    }
}
