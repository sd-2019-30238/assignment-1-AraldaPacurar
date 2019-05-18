package furnitureDeals.furnituredeals.queries.user;

import furnitureDeals.furnituredeals.dao.UserDAO;
import furnitureDeals.furnituredeals.queries.QueryHandler;

public class ProcessRemoveUserHandler implements QueryHandler<ProcessRemoveUser> {

    @Override
    public void handle(ProcessRemoveUser request) {

        int[] employeeIds = request.getEmployeeIds();
        UserDAO userDao = request.getUserDao();

        for(int employeeId : employeeIds){

            userDao.deleteById(employeeId);
        }
    }
}
