package furnitureDeals.furnituredeals.business.mediator;

import furnitureDeals.furnituredeals.queries.*;
import furnitureDeals.furnituredeals.queries.furniture.*;
import furnitureDeals.furnituredeals.queries.orders.*;
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
    }};

    public void handle(Object request) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        Type requestType = request.getClass();
        Type handlerType = handlerMap.get(requestType);
        Class<?> handlerClass = Class.forName(handlerType.getTypeName());
        Constructor<?> handlerConstructor = handlerClass.getConstructor();
        QueryHandler<Object> handler = (QueryHandler<Object>) handlerConstructor.newInstance();
        handler.handle(request);
    }

    public String notifyMediator(Object component, String event){



        if(event.equals("error")){
            return reactOnError();
        }

        if(component.getClass().equals(new RoleControllerGet().getClass()) || component.getClass().equals(new RoleControllerPost().getClass())){
            return reactOnRoleController(event);
        }
        else if(component.getClass().equals(new ShoppingCartControllerGet().getClass()) || component.getClass().equals(new ShoppingCartControllerPost().getClass())){
            return reactOnShoppingCartController(event);
        }
        else if(component.getClass().equals(new UserControllerGet().getClass()) || component.getClass().equals(new UserControllerPost().getClass())){
            return reactOnUserController(event);
        }
        return "";
    }

    private String reactOnError(){

        return "message";
    }

    private String reactOnRoleController(String event){

        if(event.equals("listRoles")){
            return "role/list";
        }
        else if(event.equals("viewRoleRights")){
            return "role/view";
        }
        else if(event.equals("addRight")){
            return "role/add-right";
        }
        else if(event.equals("removeRight")){
            return "role/remove-right";
        }
        else if(event.equals("processAddRight")){
            return "redirect:/role/view/";
        }
        else if(event.equals("processRemoveRight")){
            return "redirect:/role/view/";
        }
        return "";
    }

    private String reactOnShoppingCartController(String event){

        if(event.equals("listCart")){
            return "cart/list";
        }
        else if(event.equals("addToCart")){
            return "cart/add";
        }
        else if(event.equals("removeFromCart")){
            return "cart/remove";
        }
        else if(event.equals("processAddOrder")){
            return "message";
        }
        else if(event.equals("processRemoveFromCart")){
            return "message";
        }
        return "";
    }

    private String reactOnUserController(String event){

        if(event.equals("userAuthentication")){
            return "user/authentication";
        }
        else if(event.equals("userRegistration")){
            return "user/registration";
        }
        else if(event.equals("listUsers")){
            return "user/list";
        }
        else if(event.equals("listNotifications")){
            return "user/listNotifications";
        }
        else if(event.equals("removeUser")){
            return "user/remove";
        }
        else if(event.equals("hireUser")){
            return "user/hire";
        }
        else if(event.equals("userMessage")){
            return "user/message";
        }
        else if(event.equals("processUserAuthentication")){
            return "user/menu";
        }
        else if(event.equals("processRegistration")){
            return "user/registration";
        }
        else if(event.equals("processRemoveUser")){
            return "redirect:/user/list/";
        }
        return "";
    }
}
