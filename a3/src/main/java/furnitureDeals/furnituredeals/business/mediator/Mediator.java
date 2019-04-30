package furnitureDeals.furnituredeals.business.mediator;

import org.springframework.ui.Model;

public interface Mediator {

    String notifyMediator(Object component, String event);
}
