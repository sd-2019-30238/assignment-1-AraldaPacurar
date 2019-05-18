package furnitureDeals.furnituredeals.readM;

import furnitureDeals.furnituredeals.business.mediator.ConcreteMediator;
import furnitureDeals.furnituredeals.business.mediator.Mediator;
import furnitureDeals.furnituredeals.dao.RightsDAO;
import furnitureDeals.furnituredeals.dao.RoleDAO;
import furnitureDeals.furnituredeals.dao.UserDAO;
import furnitureDeals.furnituredeals.queries.role.AddRight;
import furnitureDeals.furnituredeals.queries.role.ListRoles;
import furnitureDeals.furnituredeals.queries.role.RemoveRight;
import furnitureDeals.furnituredeals.queries.role.ViewRoleRights;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.reflect.InvocationTargetException;

@Controller
@RequestMapping("role")
public class RoleControllerGet {

    @Autowired
    private RoleDAO roleDao;

    @Autowired
    private RightsDAO rightsDao;

    @Autowired
    private UserDAO userDao;

    private Mediator m = new ConcreteMediator();

    @RequestMapping(value = "list/{userId}", method = RequestMethod.GET)
    public String listRoles(Model model, @PathVariable int userId) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        ListRoles query = new ListRoles(userId, model, userDao, rightsDao, roleDao);
        m.handle(query);
        return "role/list";
    }

    @RequestMapping(value = "view/{userId}/{roleId}", method = RequestMethod.GET)
    public String viewRoleRights(Model model, @PathVariable int userId, @PathVariable int roleId) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        ViewRoleRights query = new ViewRoleRights(userId, model, roleId, userDao, roleDao, rightsDao);
        m.handle(query);
        return "role/view";
    }

    @RequestMapping(value = "add-right/{userId}/{roleId}", method = RequestMethod.GET)
    public String displayAddRightForm(Model model, @PathVariable int userId, @PathVariable int roleId) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        AddRight query = new AddRight(userId, model, roleId, userDao, roleDao, rightsDao);
        m.handle(query);
        return "role/add-right";
    }

    @RequestMapping(value = "remove-right/{userId}/{roleId}", method = RequestMethod.GET)
    public String displayRemoveRightForm(Model model, @PathVariable int userId, @PathVariable int roleId) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        RemoveRight query = new RemoveRight(userId, model, roleId, userDao, roleDao, rightsDao);
        m.handle(query);
        return "role/remove-right";
    }
}
