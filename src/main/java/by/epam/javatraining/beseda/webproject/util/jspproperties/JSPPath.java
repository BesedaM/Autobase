package by.epam.javatraining.beseda.webproject.util.jspproperties;

import java.util.HashSet;
import java.util.Set;

public class JSPPath {

    public static final String ERROR_PAGE;

    public static final String LOGIN_PAGE;
    public static final String ADMIN_MAIN_PAGE;
    public static final String CUSTOMER_MAIN_PAGE;
    public static final String DRIVER_MAIN_PAGE;

    public static final String CUSTOMER_REGISTER_PAGE;
    public static final String DRIVER_REGISTER_PAGE;

    public static Set<String> ALL_PATHES = new HashSet<>();

    static {
        ERROR_PAGE = "view/error.jsp";
        LOGIN_PAGE = "view/login.jsp";
        ADMIN_MAIN_PAGE = "view/admin/main.jsp";
        CUSTOMER_MAIN_PAGE = "view/customer/main.jsp";
        DRIVER_MAIN_PAGE = "view/driver/main.jsp";
        CUSTOMER_REGISTER_PAGE = "view/register/customer.jsp";
        DRIVER_REGISTER_PAGE = "view/register/driver.jsp";

        ALL_PATHES.add(ERROR_PAGE);
        ALL_PATHES.add(LOGIN_PAGE);
        ALL_PATHES.add(ADMIN_MAIN_PAGE);
        ALL_PATHES.add(CUSTOMER_MAIN_PAGE);
        ALL_PATHES.add(DRIVER_MAIN_PAGE);
        ALL_PATHES.add(CUSTOMER_REGISTER_PAGE);
        ALL_PATHES.add(DRIVER_REGISTER_PAGE);
    }

}
