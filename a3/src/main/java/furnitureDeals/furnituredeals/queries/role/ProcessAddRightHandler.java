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

public class ProcessAddRightHandler implements QueryHandler<ProcessAddRight> {

    @Override
    public void handle(ProcessAddRight request) {

        int userId = request.getUserId();
        Model model = request.getModel();
        Errors errors = request.getErrors();
        AddRoleRightForm form = request.getForm();
        RoleDAO roleDao = request.getRoleDao();
        RightsDAO rightsDao = request.getRightsDao();

        if(errors.hasErrors()){

            model.addAttribute("title", "Add Rights");

            return ;
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

            return ;
        }

        myRole.addRight(newRight);
        roleDao.save(myRole);
    }
}
