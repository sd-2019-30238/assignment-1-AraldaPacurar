package furnitureDeals.furnituredeals.queries.orders;

import furnitureDeals.furnituredeals.dao.NotificationDAO;
import furnitureDeals.furnituredeals.dao.OrdersDAO;
import furnitureDeals.furnituredeals.dao.UserDAO;
import furnitureDeals.furnituredeals.model.Notification;
import furnitureDeals.furnituredeals.model.Orders;
import furnitureDeals.furnituredeals.model.User;
import furnitureDeals.furnituredeals.model.builder.NotificationBuilder;
import furnitureDeals.furnituredeals.model.builder.OrderBuilder;
import furnitureDeals.furnituredeals.queries.QueryHandler;

import java.util.Optional;

public class ProcessRemoveOrderHandler implements QueryHandler<ProcessRemoveOrder> {

    @Override
    public void handle(ProcessRemoveOrder request) {

        int userId = request.getUserId();
        int[] orderIds = request.getOrderIds();
        OrdersDAO ordersDao = request.getOrdersDao();
        UserDAO userDao = request.getUserDao();
        NotificationDAO notificationDao = request.getNotificationDao();

        for(int orderId : orderIds){

            Optional<Orders> optionalOrder = ordersDao.findById(orderId);
            Orders order = null;
            if(optionalOrder.isPresent()){
                order = optionalOrder.get();
            }

            User user = null;
            Optional<User> optionalUser = userDao.findById(order.getUser().getId());
            if(optionalUser.isPresent()) {

                user = optionalUser.get();
            }

            String notificationMessage = "No notifications";

            if(order.getStatus().equals("Pending")){
                order.setStatus("Shipping");
                notificationMessage = "Order number " + order.getId() + " has been sent!";
            }
            else if(order.getStatus().equals("Shipping")){
                order.setStatus("Payed");
                notificationMessage = "Order number " + order.getId() + " has arrived!";
            }

            Notification notification = new NotificationBuilder()
                    .setUser(user)
                    .setNotification(notificationMessage)
                    .build();

            order.notifyObserver(user, notification);

            ordersDao.save(order);
            notificationDao.save(notification);
        }
    }
}
