package by.epam.javatraining.beseda.webproject.controller.util.constant;

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

    public static final String NEW_REQUESTS_PAGE;
    public static final String CURRENT_REQUESTS_PAGE;
    public static final String FULFILLED_REQUESTS_PAGE;
    public static final String REJECTED_REQUESTS_PAGE;

    public static final String CAR_LIST_PAGE;
    public static final String CLIENT_LIST_PAGE;
    public static final String DRIVER_LIST_PAGE;

    public static final String ADD_NEW_ROUTE_PAGE;
    public static final String CHANGE_ROUTE_PAGE;


    static {
        ERROR_PAGE = "/error_page.jsp";
        LOGIN_PAGE = "login";
        ADMIN_MAIN_PAGE = "/admin/admin_main";
        CUSTOMER_MAIN_PAGE = "/customer/customer_main";
        CUSTOMER_PERSONAL_DATA_PAGE = "customer/personal_data";

        DRIVER_MAIN_PAGE = "/driver/driver_main";

        CUSTOMER_REGISTER_PAGE = "register/customer";

        DRIVER_REGISTER_PAGE = "register/driver";

        NEW_REQUESTS_PAGE = "admin/requests/new_requests";
        CURRENT_REQUESTS_PAGE = "admin/requests/current_requests";
        FULFILLED_REQUESTS_PAGE = "admin/requests/fulfilled_requests";
        REJECTED_REQUESTS_PAGE = "admin/requests/rejected_requests";

        CAR_LIST_PAGE = "admin/car_list";
        CLIENT_LIST_PAGE = "admin/client_list";
        DRIVER_LIST_PAGE = "admin/driver_list";

        ADD_NEW_ROUTE_PAGE = "admin/requests/add_route";
        CHANGE_ROUTE_PAGE = "admin/requests/change_route";
    }

}
