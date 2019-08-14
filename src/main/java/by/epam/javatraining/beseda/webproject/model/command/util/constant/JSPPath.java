package by.epam.javatraining.beseda.webproject.model.command.util.constant;

/**
 * Class containing jsp pages relative paths
 */
public enum JSPPath {

    ERROR_PAGE("/view/error_page.jsp"),
    LOGIN_PAGE("/view/login.jsp"),
    ADMIN_MAIN_PAGE("/view/admin/welcome.jsp"),
    CUSTOMER_MAIN_PAGE("/view/customer/customer_main.jsp"),
    CUSTOMER_PERSONAL_DATA_PAGE("/view/customer/personal_data.jsp"),
    DRIVER_MAIN_PAGE("/view/driver/driver_main.jsp"),
    CUSTOMER_REGISTER_PAGE("/view/register/customer.jsp"),
    DRIVER_REGISTER_PAGE("/view/register/driver.jsp");

    String path;

    JSPPath(String path) {
        this.path = path;
    }

    public String getPath(){
        return path;
    }
}
