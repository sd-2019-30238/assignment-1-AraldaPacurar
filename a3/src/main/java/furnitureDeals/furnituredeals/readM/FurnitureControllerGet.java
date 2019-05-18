package furnitureDeals.furnituredeals.readM;

import furnitureDeals.furnituredeals.business.mediator.ConcreteMediator;
import furnitureDeals.furnituredeals.business.mediator.Mediator;
import furnitureDeals.furnituredeals.dao.*;
import furnitureDeals.furnituredeals.model.*;
import furnitureDeals.furnituredeals.model.forms.FilterForm;
import furnitureDeals.furnituredeals.queries.AddDiscount;
import furnitureDeals.furnituredeals.queries.AddFurniture;
import furnitureDeals.furnituredeals.queries.ReadAllFurniture;
import furnitureDeals.furnituredeals.queries.RemoveFurniture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("furniture")
public class FurnitureControllerGet {

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

    @RequestMapping(value = "list/{userId}", method = RequestMethod.GET)
    public String listFurniture(Model model, @PathVariable int userId) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        ReadAllFurniture query = new ReadAllFurniture(userId, model, userDao, rightsDao, furnitureDao);
        m.handle(query);
        return "furniture/list";
    }

    @RequestMapping(value = "add/{userId}", method = RequestMethod.GET)
    public String displayAddFurnitureForm(Model model, @PathVariable int userId) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        AddFurniture query = new AddFurniture(userId, model, userDao, rightsDao, furnitureTypeDao);
        m.handle(query);
        return "furniture/add";
    }

    @RequestMapping(value = "remove/{userId}", method = RequestMethod.GET)
    public String displayRemoveFurniture(Model model, @PathVariable int userId) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        RemoveFurniture query = new RemoveFurniture(userId, model, userDao, rightsDao, furnitureDao);
        m.handle(query);
        return "furniture/remove";
    }

    @RequestMapping(value = "discount/{userId}", method = RequestMethod.GET)
    public String addDiscount(Model model, @PathVariable int userId) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        AddDiscount query = new AddDiscount(userId, model, userDao, rightsDao, furnitureDao);
        m.handle(query);
        return "furniture/discount";
    }
}
