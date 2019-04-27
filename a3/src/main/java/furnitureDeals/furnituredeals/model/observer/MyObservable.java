package furnitureDeals.furnituredeals.model.observer;

import furnitureDeals.furnituredeals.model.Notification;
import furnitureDeals.furnituredeals.model.User;

import java.util.ArrayList;
import java.util.List;

public class MyObservable {

    public List<MyObserver> observers = new ArrayList<MyObserver>();

    public void registerObserver(MyObserver o){

        observers.add(o);
    }

    public void unregisterObserver(MyObserver o){

        observers.remove(o);
    }

    public void notifyObserver(User user, Notification notification){

        user.update(notification);
    }
}
