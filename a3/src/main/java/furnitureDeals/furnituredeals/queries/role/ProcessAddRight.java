package furnitureDeals.furnituredeals.queries.role;

import furnitureDeals.furnituredeals.dao.RightsDAO;
import furnitureDeals.furnituredeals.dao.RoleDAO;
import furnitureDeals.furnituredeals.model.forms.AddRoleRightForm;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;

public class ProcessAddRight {

    private int userId;
    private Model model;
    private Errors errors;
    private AddRoleRightForm form;
    private RoleDAO roleDao;
    private RightsDAO rightsDao;

    public ProcessAddRight(int userId, Model model, Errors errors, AddRoleRightForm form, RoleDAO roleDao, RightsDAO rightsDao) {
        this.userId = userId;
        this.model = model;
        this.errors = errors;
        this.form = form;
        this.roleDao = roleDao;
        this.rightsDao = rightsDao;
    }

    public int getUserId() {
        return userId;
    }

    public Model getModel() {
        return model;
    }

    public Errors getErrors() {
        return errors;
    }

    public AddRoleRightForm getForm() {
        return form;
    }

    public RoleDAO getRoleDao() {
        return roleDao;
    }

    public RightsDAO getRightsDao() {
        return rightsDao;
    }
}
