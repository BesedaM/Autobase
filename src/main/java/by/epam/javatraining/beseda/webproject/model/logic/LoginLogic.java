package by.epam.javatraining.beseda.webproject.model.logic;

import by.epam.javatraining.beseda.webproject.model.dao.entitydao.UserDAO;
import by.epam.javatraining.beseda.webproject.model.entity.user.User;
import by.epam.javatraining.beseda.webproject.model.exception.daoexception.DAOTechnicalException;
import by.epam.javatraining.beseda.webproject.model.exception.serviceexception.ServiceTechnicalException;


public class LoginLogic {

    private static UserDAO userDAO = UserDAO.getDAO();

    public static boolean isValidUser(String login, String password) throws ServiceTechnicalException {
        User user = null;
        if (login != null && password != null) {
            byte[] pw = PasswordHash.getHash(password);
            try {
                user = userDAO.getUserByLoginAndPassword(login, pw);
            } catch (DAOTechnicalException e) {
                throw new ServiceTechnicalException(e);
            }
        }
        return user != null;
    }

}
