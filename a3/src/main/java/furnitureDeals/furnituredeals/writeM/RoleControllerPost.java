package furnitureDeals.furnituredeals.writeM;

import furnitureDeals.furnituredeals.business.mediator.ConcreteMediator;
import furnitureDeals.furnituredeals.business.mediator.Mediator;
import furnitureDeals.furnituredeals.model.Role;
import furnitureDeals.furnituredeals.model.forms.AddRoleRightForm;
import furnitureDeals.furnituredeals.dao.RightsDAO;
import furnitureDeals.furnituredeals.dao.RoleDAO;
import furnitureDeals.furnituredeals.dao.UserDAO;
import furnitureDeals.furnituredeals.queries.role.ProcessAddRight;
import furnitureDeals.furnituredeals.queries.role.ProcessRemoveRight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.lang.reflect.InvocationTargetException;
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
    public String processAddRightForm(Model model, @ModelAttribute @Valid AddRoleRightForm form, Errors errors, @PathVariable int userId) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        ProcessAddRight query = new ProcessAddRight(userId, model, errors, form, roleDao, rightsDao);
        m.handle(query);

        Role myRole = null;
        Optional<Role> optionalRole = roleDao.findById(form.getRoleId());
        if(optionalRole.isPresent()){

            myRole = optionalRole.get();
        }

        return "redirect:/role/view/" + userId + "/" + myRole.getId();
    }

    @RequestMapping(value = "remove-right/{userId}/{roleId}", method = RequestMethod.POST)
    public String processRemoveRightForm(Model model, @ModelAttribute @Valid AddRoleRightForm form, Errors errors, @PathVariable int userId) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        ProcessRemoveRight query = new ProcessRemoveRight(userId, model, errors, form, roleDao, rightsDao);
        m.handle(query);

        Role myRole = null;
        Optional<Role> optionalRole = roleDao.findById(form.getRoleId());
        if(optionalRole.isPresent()){

            myRole = optionalRole.get();
        }

        return "redirect:/role/view/" + userId + "/" + myRole.getId();
    }
}
