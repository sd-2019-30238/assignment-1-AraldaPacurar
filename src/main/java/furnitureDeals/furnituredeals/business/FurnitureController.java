package furnitureDeals.furnituredeals.business;

import furnitureDeals.furnituredeals.dao.*;
import furnitureDeals.furnituredeals.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("furniture")
public class  FurnitureController {

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

            return "message";
        }

        model.addAttribute("title", "Available Furniture");
        model.addAttribute("userId", userId);
        model.addAttribute("furnitures", furnitureDao.findAll());

        return "furniture/list";
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

            return "message";
        }

        model.addAttribute("title", "Add Furniture");
        model.addAttribute("userId", userId);
        model.addAttribute("furnitureTypes", furnitureTypeDao.findAll());
        model.addAttribute(new Furniture());

        return "furniture/add";
    }

    @RequestMapping(value = "add/{userId}", method = RequestMethod.POST)
    public String processAddFurniture(@ModelAttribute @Valid Furniture newFurniture, Errors errors, @PathVariable int userId, Model model){

        if(errors.hasErrors()){

            model.addAttribute("title", "Add Furniture");
            return "furniture/add";
        }

        furnitureDao.save(newFurniture);

        return "redirect:/furniture/list/" + userId;
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

            return "message";
        }

        model.addAttribute("title", "Remove Furniture");
        model.addAttribute("userId", userId);
        model.addAttribute("furnitures", furnitureDao.findAll());

        return "furniture/remove";
    }

    @RequestMapping(value = "remove/{userId}", method = RequestMethod.POST)
    public String processRemoveFurniture(@RequestParam int[] furnitureIds, @PathVariable int userId){

        for(int furnitureId : furnitureIds){

            furnitureDao.deleteById(furnitureId);
        }

        return "redirect:/furniture/list/" + userId;
    }
}
