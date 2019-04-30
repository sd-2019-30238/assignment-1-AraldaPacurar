package furnitureDeals.furnituredeals.writeM;

import furnitureDeals.furnituredeals.business.mediator.ConcreteMediator;
import furnitureDeals.furnituredeals.business.mediator.Mediator;
import furnitureDeals.furnituredeals.model.Rights;
import furnitureDeals.furnituredeals.model.Role;
import furnitureDeals.furnituredeals.model.forms.AddRoleRightForm;
import furnitureDeals.furnituredeals.dao.RightsDAO;
import furnitureDeals.furnituredeals.dao.RoleDAO;
import furnitureDeals.furnituredeals.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("role")
public class RoleControllerPost {

    @Autowired
    private RoleDAO roleDao;

    @Autowired
    private RightsDAO rightsDao;

    @Autowired
    private UserDAO userDao;

    private Mediator m = new ConcreteMediator();

    @RequestMapping(value = "add-right/{userId}/{roleId}", method = RequestMethod.POST)
    public String processAddRightForm(Model model, @ModelAttribute @Valid AddRoleRightForm form, Errors errors, @PathVariable int userId){

        if(errors.hasErrors()){

            model.addAttribute("title", "Add Rights");

            return m.notifyMediator(this, "addRight");
        }

        Rights newRight = null;
        Optional<Rights> optionalRights = rightsDao.findById(form.getRightId());
        if(optionalRights.isPresent()){

            newRight = optionalRights.get();
        }

        Role myRole = null;
        Optional<Role> optionalRole = roleDao.findById(form.getRoleId());
        if(optionalRole.isPresent()){

            myRole = optionalRole.get();
        }

        if(myRole.getRights().contains(newRight)){

            model.addAttribute("title", "Add Right");
            model.addAttribute("messages", "This role already has that right!");
            model.addAttribute("userId", userId);

            return m.notifyMediator(this, "error");
        }

        myRole.addRight(newRight);
        roleDao.save(myRole);

        return m.notifyMediator(this, "processAddRight") + userId + "/" + myRole.getId();
    }

    @RequestMapping(value = "remove-right/{userId}/{roleId}", method = RequestMethod.POST)
    public String processRemoveRightForm(Model model, @ModelAttribute @Valid AddRoleRightForm form, Errors errors, @PathVariable int userId){

        if(errors.hasErrors()){

            model.addAttribute("title", "Remove Rights");

            return m.notifyMediator(this, "removeRight");
        }

        Rights rightToRemove = null;
        Optional<Rights> optionalRights = rightsDao.findById(form.getRightId());
        if(optionalRights.isPresent()){

            rightToRemove = optionalRights.get();
        }

        Role myRole = null;
        Optional<Role> optionalRole = roleDao.findById(form.getRoleId());
        if(optionalRole.isPresent()){

            myRole = optionalRole.get();
        }

        myRole.removeRight(rightToRemove);
        roleDao.save(myRole);

        return m.notifyMediator(this, "processRemoveRight") + userId + "/" + myRole.getId();
    }
}
