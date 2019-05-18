package furnitureDeals.furnituredeals.queries.user;

import furnitureDeals.furnituredeals.dao.UserDAO;

public class ProcessRemoveUser {

    private int[] employeeIds;
    private UserDAO userDao;

    public ProcessRemoveUser(int[] employeeIds, UserDAO userDao) {
        this.employeeIds = employeeIds;
        this.userDao = userDao;
    }

    public int[] getEmployeeIds() {
        return employeeIds;
    }

    public UserDAO getUserDao() {
        return userDao;
    }
}
