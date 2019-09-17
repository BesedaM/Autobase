package by.epam.javatraining.beseda.webproject.controller.command;

import by.epam.javatraining.beseda.webproject.controller.command.implementation.get.*;
import by.epam.javatraining.beseda.webproject.controller.command.implementation.gotopage.*;
import by.epam.javatraining.beseda.webproject.controller.command.implementation.post.*;
import by.epam.javatraining.beseda.webproject.controller.command.implementation.post.admin.*;

/**
 * Class containing all the application commands
 * 
 * @author Maryia_Biaseda
 *
 */
public enum ActionCommandEnum {
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    CUSTOMER_REGISTER(new CustomerRegisterCommand()),

    CHANGE_LOCALE(new LocaleChangerCommand()),

    LOGIN_PAGE(new GoToLogin()),
    CUSTOMER_REGISTER_PAGE(new GoToCustomerRegister()),
    PERSONAL_CUSTOMER_DATA_PAGE(new GoToPersonalCustomerData()),
    CUSTOMER_MAIN_PAGE(new GoToCustomerMain()),

    CHANGE_ROUTE_STATUS(new RouteStatusChanger()),
    CHANGE_CAR_STATE(new CarStateChanger()),
    CHANGE_ROUTE_STATUS_CAR_STATE(new RouteStatusCarStateChanger()),
    CHANGE_CUSTOMER_PASSWORD(new CustomerPasswordChanger()),
    CHANGE_CUSTOMER_DATA(new CustomerDataChanger()),
    ADD_REQUEST(new AddRequestCommand()),
    UPDATE_CUSTOMER_REQUEST_LIST(new UpdateCustomerRequestsCommand()),

    NEW_REQUESTS_REDIRECT(new AdminGetNewRequests()),
    CHANGE_REQUEST_STATUS(new RequestStatusChanger()),
    CREATE_ROUTE_REDIRECT(new GoToAddNewRoutePage()),
    CARS_IN_ROUTES_RELOAD(new ReloadCarsInRoutes()),
    ADD_CHANGE_TASK(new ProcessTaskData()),
    ADD_NEW_ROUTE(new AddNewRoute()),
    CURRENT_REQUESTS_GET(new AdminGetCurrentRequests()),
    CURRENT_REQUESTS_REDIRECT(new GoToCurrentRequests()),
    CHANGE_CURRENT_ROUTE(new ChangeRoute()),
    CHANGE_CARS_LIST(new ChangeCarListFlag()),
    CHANGE_CARS(new ChangeCars()),
    DELETE_TASK(new DeleteTask()),
    CHANGE_TASK(new ChangeTaskFlag()),
    ADD_TASK(new AddTaskFlag()),
    FULFILLED_REQUESTS_REDIRECT(new AdminGetFulfilledRequests()),
    REJECTED_REQUESTS_REDIRECT(new AdminGetRejectedRequests()),
//    CAR_LIST_REDIRECT(new AdminGetCarListData()),
//    DRIVER_LIST_REDIRECT(new AdminGetDriversData()),
//    CLIENT_LIST_REDIRECT(new AdminGetClientsData()),
    ADMIN_MAIN_REDIRECT(new GoToAdminMain()),

    DELETE_REQUEST(new RequestDeleteCommand());


    ActionCommand command;

    ActionCommandEnum(ActionCommand command) {
        this.command = command;
    }

    public ActionCommand getCommand() {
        return command;
    }
}