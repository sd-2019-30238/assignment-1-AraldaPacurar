package furnitureDeals.furnituredeals.business.mediator;

import org.springframework.ui.Model;

import java.lang.reflect.InvocationTargetException;

public interface Mediator {

    String notifyMediator(Object component, String event);
    void handle(Object query) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException ;
}
