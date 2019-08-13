package by.epam.javatraining.beseda.webproject.model.command.util.constant;

import java.util.HashSet;
import java.util.Set;

/**
 * Class containing jsp pages relative paths
 */
public class JSPPath {

    public static final String ERROR_PAGE;

    public static final String LOGIN_PAGE;
    public static final String ADMIN_MAIN_PAGE;
    public static final String CUSTOMER_MAIN_PAGE;
    public static final String CUSTOMER_PERSONAL_DATA_PAGE;
    public static final String DRIVER_MAIN_PAGE;

    public static final String CUSTOMER_REGISTER_PAGE;
    public static final String DRIVER_REGISTER_PAGE;

    public static Set<String> ALL_PATHS = new HashSet<>();

    static {
        ERROR_PAGE = "/view/error_page.jsp";
        LOGIN_PAGE = "/view/login.jsp";
        ADMIN_MAIN_PAGE = "/view/admin/welcome.jsp";
        CUSTOMER_MAIN_PAGE = "/view/customer/customer_main.jsp";
        CUSTOMER_PERSONAL_DATA_PAGE="/view/customer/personal_data.jsp";

        DRIVER_MAIN_PAGE = "/view/driver/driver_main.jsp";

        CUSTOMER_REGISTER_PAGE = "/view/register/customer.jsp";

        DRIVER_REGISTER_PAGE = "/view/register/driver.jsp";


        ALL_PATHS.add(ERROR_PAGE);
        ALL_PATHS.add(LOGIN_PAGE);
        ALL_PATHS.add(ADMIN_MAIN_PAGE);
        ALL_PATHS.add(CUSTOMER_MAIN_PAGE);
        ALL_PATHS.add(DRIVER_MAIN_PAGE);
        ALL_PATHS.add(CUSTOMER_REGISTER_PAGE);
        ALL_PATHS.add(DRIVER_REGISTER_PAGE);
    }

}
