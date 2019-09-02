package by.epam.javatraining.beseda.webproject.controller.command.util.constant;

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

    public static final String CARS_IN_ROUTES_PAGE;
    public static final String CAR_LIST_PAGE;
    public static final String CLIENT_LIST_PAGE;
    public static final String DRIVER_LIST_PAGE;

    public static final String ADD_NEW_ROUTE_PAGE;
    public static final String CHANGE_ROUTE_PAGE;


    static {
        ERROR_PAGE = "/view/error_page.jsp";
        LOGIN_PAGE = "/view/login.jsp";
        ADMIN_MAIN_PAGE = "/view/admin/admin_main.jsp";
        CUSTOMER_MAIN_PAGE = "/view/customer/customer_main.jsp";
        CUSTOMER_PERSONAL_DATA_PAGE = "/view/customer/personal_data.jsp";

        DRIVER_MAIN_PAGE = "/view/driver/driver_main.jsp";

        CUSTOMER_REGISTER_PAGE = "/view/register/customer.jsp";

        DRIVER_REGISTER_PAGE = "/view/register/driver.jsp";

        NEW_REQUESTS_PAGE = "/view/admin/requests/new_requests.jsp";
        CURRENT_REQUESTS_PAGE = "/view/admin/requests/current_requests.jsp";
        FULFILLED_REQUESTS_PAGE = "/view/admin/requests/fulfilled_requests.jsp";
        REJECTED_REQUESTS_PAGE = "/view/admin/requests/rejected_requests.jsp";

        CARS_IN_ROUTES_PAGE = "/view/admin/cars_in_routes.jsp";
        CAR_LIST_PAGE = "/view/admin/car_list.jsp";
        CLIENT_LIST_PAGE = "/view/admin/client_list.jsp";
        DRIVER_LIST_PAGE = "/view/admin/driver_list.jsp";

        ADD_NEW_ROUTE_PAGE = "/view/admin/requests/add_route.jsp";
        CHANGE_ROUTE_PAGE = "/view/admin/requests/change_route.jsp";
    }

}
