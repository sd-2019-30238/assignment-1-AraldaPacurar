package furnitureDeals.furnituredeals.queries.user;

import org.springframework.ui.Model;

public class DisplayRegisterForm {

    private Model model;

    public DisplayRegisterForm(Model model) {
        this.model = model;
    }

    public Model getModel() {
        return model;
    }
}
