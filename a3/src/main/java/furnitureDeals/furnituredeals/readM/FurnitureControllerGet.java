package furnitureDeals.furnituredeals.readM;

import furnitureDeals.furnituredeals.business.mediator.ConcreteMediator;
import furnitureDeals.furnituredeals.business.mediator.Mediator;
import furnitureDeals.furnituredeals.dao.*;
import furnitureDeals.furnituredeals.model.*;
import furnitureDeals.furnituredeals.model.forms.FilterForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String listFurniture(Model model, @PathVariable int userId){

        Role myRole = null;
        User user = null;
        Optional<User> optionalUser = userDao.findById(userId);
        if(optionalUser.isPresent()){

            user = optionalUser.get();
            myRole = user.getRole();
        }

        List<Rights> rights = rightsDao.findByMyRight("view furniture");
        Rights requiredRight = rights.get(0);

        if(!myRole.getRights().contains(requiredRight)){

            model.addAttribute("title", "Invalid request!");
            model.addAttribute("messages", "You do not have privileges to perform this action!");
            model.addAttribute("userId", userId);

            return m.notifyMediator(this, "error");
        }

        model.addAttribute("title", "Available Furniture");
        model.addAttribute("userId", userId);
        model.addAttribute("furnitures", furnitureDao.findAll());
        model.addAttribute(new FilterForm());

        return m.notifyMediator(this, "listFurniture");
    }

    @RequestMapping(value = "add/{userId}", method = RequestMethod.GET)
    public String displayAddFurnitureForm(Model model, @PathVariable int userId){

        Role myRole = null;
        User user = null;
        Optional<User> optionalUser = userDao.findById(userId);
        if(optionalUser.isPresent()){

            user = optionalUser.get();
            myRole = user.getRole();
        }

        List<Rights> rights = rightsDao.findByMyRight("add furniture");
        Rights requiredRight = rights.get(0);

        if(!myRole.getRights().contains(requiredRight)){


            model.addAttribute("title", "Invalid request!");
            model.addAttribute("messages", "You do not have privileges to perform this operation!");
            model.addAttribute("userId", userId);

            return m.notifyMediator(this, "error");
        }

        model.addAttribute("title", "Add Furniture");
        model.addAttribute("userId", userId);
        model.addAttribute("furnitureTypes", furnitureTypeDao.findAll());
        model.addAttribute(new Furniture());

        return m.notifyMediator(this, "addFurniture");
    }

    @RequestMapping(value = "remove/{userId}", method = RequestMethod.GET)
    public String displayRemoveFurniture(Model model, @PathVariable int userId){

        Role myRole = null;
        User user = null;
        Optional<User> optionalUser = userDao.findById(userId);
        if(optionalUser.isPresent()){

            user = optionalUser.get();
            myRole = user.getRole();
        }

        List<Rights> rights = rightsDao.findByMyRight("remove furniture");
        Rights requiredRight = rights.get(0);

        if(!myRole.getRights().contains(requiredRight)){

            model.addAttribute("title", "Invalid request!");
            model.addAttribute("messages", "You do not have privileges to perform this action!");
            model.addAttribute("userId", userId);

            return m.notifyMediator(this, "error");
        }

        model.addAttribute("title", "Remove Furniture");
        model.addAttribute("userId", userId);
        model.addAttribute("furnitures", furnitureDao.findAll());

        return m.notifyMediator(this, "removeFurniture");
    }

    @RequestMapping(value = "discount/{userId}", method = RequestMethod.GET)
    public String addDiscount(Model model, @PathVariable int userId){

        Role myRole = null;
        User user = null;
        Optional<User> optionalUser = userDao.findById(userId);
        if(optionalUser.isPresent()){

            user = optionalUser.get();
            myRole = user.getRole();
        }

        List<Rights> rights = rightsDao.findByMyRight("manage discounts");
        Rights requiredRight = rights.get(0);

        if(!myRole.getRights().contains(requiredRight)){

            model.addAttribute("title", "Invalid request!");
            model.addAttribute("messages", "You do not have privileges to perform this action!");
            model.addAttribute("userId", userId);

            return m.notifyMediator(this, "error");
        }

        model.addAttribute("title", "Manage Discounts");
        model.addAttribute("furnitures", furnitureDao.findAll());
        model.addAttribute("userId", userId);

        return m.notifyMediator(this, "addDiscount");
    }
}
