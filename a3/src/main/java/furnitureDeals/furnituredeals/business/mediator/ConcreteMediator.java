package furnitureDeals.furnituredeals.business.mediator;

import furnitureDeals.furnituredeals.queries.*;
import furnitureDeals.furnituredeals.queries.furniture.*;
import furnitureDeals.furnituredeals.queries.orders.*;
import furnitureDeals.furnituredeals.queries.role.*;
import furnitureDeals.furnituredeals.queries.shoppingCart.*;
import furnitureDeals.furnituredeals.queries.user.*;
import furnitureDeals.furnituredeals.readM.*;
import furnitureDeals.furnituredeals.writeM.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.HashMap;

public class ConcreteMediator implements Mediator {

    HashMap<Type, Type> handlerMap = new HashMap<Type, Type>() {{
        put(ReadAllFurniture.class, ReadAllFurnitureHandler.class);
        put(AddFurniture.class, AddFurnitureHandler.class);
        put(RemoveFurniture.class, RemoveFurnitureHandler.class);
        put(AddDiscount.class, AddDiscountHandler.class);
        put(ProcessReadAllFurniture.class, ProcessReadAllFurnitureHandler.class);
        put(ProcessAddFurniture.class, ProcessAddFurnitureHandler.class);
        put(ProcessRemoveFurniture.class, ProcessRemoveFurnitureHandler.class);
        put(ProcessAddDiscount.class, ProcessAddDiscountHandler.class);
        put(ListOrders.class, ListOrdersHandler.class);
        put(ListMyOrders.class, ListMyOrdersHandler.class);
        put(AddOrder.class, AddOrderHandler.class);
        put(RemoveOrder.class, RemoveOrderHandler.class);
        put(ProcessListMyOrders.class, ProcessListMyOrdersHandler.class);
        put(ProcessAddOrder.class, ProcessAddOrderHandler.class);
        put(ProcessRemoveOrder.class, ProcessRemoveOrderHandler.class);
        put(ListRoles.class, ListRolesHandler.class);
        put(ViewRoleRights.class, ViewRoleRightsHandler.class);
        put(AddRight.class, AddRightHandler.class);
        put(RemoveRight.class, RemoveRightHandler.class);
        put(ProcessAddRight.class, ProcessAddRightHandler.class);
        put(ProcessRemoveRight.class, ProcessRemoveRightHandler.class);
        put(ListItems.class, ListItemsHandler.class);
        put(SendOrder.class, SendOrderHandler.class);
        put(RemoveItem.class, RemoveItemHandler.class);
        put(ProcessListItems.class, ProcessListItemsHandler.class);
        put(ProcessSendOrder.class, ProcessSendOrderHandler.class);
        put(ProcessRemoveItems.class, ProcessRemoveItemsHandler.class);
        put(DisplayAuthenticationForm.class, DisplayAuthenticationFormHandler.class);
        put(DisplayRegisterForm.class, DisplayRegisterFormHandler.class);
        put(ListUsers.class, ListUsersHandler.class);
        put(ListNotifications.class, ListNotificationsHandler.class);
        put(RemoveUser.class, RemoveUserHandler.class);
        put(HireEmployee.class, HireEmployeeHandler.class);
        put(ProcessAuthentication.class, ProcessAuthenticationHandler.class);
        put(ProcessRegistration.class, ProcessRegistrationHandler.class);
        put(ProcessRemoveUser.class, ProcessRemoveUserHandler.class);
        put(ProcessHireEmployee.class, ProcessHireEmployeeHandler.class);
    }};

    public void handle(Object request) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        Type requestType = request.getClass();
        Type handlerType = handlerMap.get(requestType);
        Class<?> handlerClass = Class.forName(handlerType.getTypeName());
        Constructor<?> handlerConstructor = handlerClass.getConstructor();
        QueryHandler<Object> handler = (QueryHandler<Object>) handlerConstructor.newInstance();
        handler.handle(request);
    }
}
