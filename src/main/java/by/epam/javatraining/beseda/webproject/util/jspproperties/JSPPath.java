package by.epam.javatraining.beseda.webproject.util.jspproperties;

public class JSPPath {

    public static final String ERROR_PAGE;

    public static final String LOGIN_PAGE;
    public static final String ADMIN_MAIN_PAGE;
    public static final String CUSTOMER_MAIN_PAGE;
    public static final String DRIVER_MAIN_PAGE;

    public static final String CUSTOMER_REGISTER_PAGE;


    static {
        ERROR_PAGE = "/error.jsp";
        LOGIN_PAGE = "/login.jsp";
        ADMIN_MAIN_PAGE = "/admin/main.jsp";
        CUSTOMER_MAIN_PAGE = "/customer/main.jsp";
        DRIVER_MAIN_PAGE = "/driver/main.jsp";
        CUSTOMER_REGISTER_PAGE = "/customer/register.jsp";

    }

}
