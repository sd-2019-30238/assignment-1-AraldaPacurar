package furnitureDeals.furnituredeals.model.builder;

import furnitureDeals.furnituredeals.model.Notification;
import furnitureDeals.furnituredeals.model.User;

public class NotificationBuilder {

    private Notification notification;

    public NotificationBuilder(){

        notification = new Notification();
    }

    public NotificationBuilder setUser(User user){

        notification.setUser(user);
        return this;
    }

    public NotificationBuilder setNotification(String notificationMessage){

        notification.setNotification(notificationMessage);
        return this;
    }

    public Notification build(){

        return notification;
    }
}
