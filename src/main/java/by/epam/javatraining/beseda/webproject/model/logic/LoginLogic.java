package by.epam.javatraining.beseda.webproject.model.logic;

import by.epam.javatraining.beseda.webproject.model.dao.entitydao.UserDAO;
import by.epam.javatraining.beseda.webproject.model.dao.factory.DAOEntityFactory;
import by.epam.javatraining.beseda.webproject.model.entity.user.User;
import by.epam.javatraining.beseda.webproject.model.dao.exception.DAOTechnicalException;
import by.epam.javatraining.beseda.webproject.model.service.exception.ServiceTechnicalException;


public class LoginLogic {

    private static UserDAO userDAO = DAOEntityFactory.getFactory().getUserDAO();

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
