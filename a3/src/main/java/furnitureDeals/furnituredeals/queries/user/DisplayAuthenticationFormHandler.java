package furnitureDeals.furnituredeals.queries.user;

import furnitureDeals.furnituredeals.model.User;
import furnitureDeals.furnituredeals.queries.QueryHandler;
import org.springframework.ui.Model;

public class DisplayAuthenticationFormHandler implements QueryHandler<DisplayAuthenticationForm> {

    @Override
    public void handle(DisplayAuthenticationForm request) {

        Model model = request.getModel();

        model.addAttribute("title", "Authentication");
        model.addAttribute(new User());
    }
}
