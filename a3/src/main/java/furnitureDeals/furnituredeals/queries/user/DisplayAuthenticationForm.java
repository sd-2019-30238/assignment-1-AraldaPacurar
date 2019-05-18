package furnitureDeals.furnituredeals.queries.user;

import org.springframework.ui.Model;

public class DisplayAuthenticationForm {

    private Model model;

    public DisplayAuthenticationForm(Model model) {
        this.model = model;
    }

    public Model getModel() {
        return model;
    }
}
