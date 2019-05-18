package furnitureDeals.furnituredeals.queries.user;

import furnitureDeals.furnituredeals.model.User;
import furnitureDeals.furnituredeals.queries.QueryHandler;
import org.springframework.ui.Model;

public class DisplayRegisterFormHandler implements QueryHandler<DisplayRegisterForm> {

    @Override
    public void handle(DisplayRegisterForm request) {

        Model model = request.getModel();

        model.addAttribute("title", "Registration");
        model.addAttribute(new User());
    }
}
