package furnitureDeals.furnituredeals.queries.role;

import furnitureDeals.furnituredeals.dao.RightsDAO;
import furnitureDeals.furnituredeals.dao.RoleDAO;
import furnitureDeals.furnituredeals.model.Rights;
import furnitureDeals.furnituredeals.model.Role;
import furnitureDeals.furnituredeals.model.forms.AddRoleRightForm;
import furnitureDeals.furnituredeals.queries.QueryHandler;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;

import java.util.Optional;

public class ProcessRemoveRightHandler implements QueryHandler<ProcessRemoveRight> {

    @Override
    public void handle(ProcessRemoveRight request) {

        int userId = request.getUserId();
        Model model = request.getModel();
        Errors errors = request.getErrors();
        AddRoleRightForm form = request.getForm();
        RoleDAO roleDao = request.getRoleDao();
        RightsDAO rightsDao = request.getRightsDao();

        if(errors.hasErrors()){

            model.addAttribute("title", "Remove Rights");

            return ;
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
    }
}
