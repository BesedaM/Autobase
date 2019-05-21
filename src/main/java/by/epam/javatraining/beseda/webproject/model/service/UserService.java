package by.epam.javatraining.beseda.webproject.model.service;

import by.epam.javatraining.beseda.webproject.model.dao.entitydao.CustomerDAO;
import by.epam.javatraining.beseda.webproject.model.dao.entitydao.DriverDAO;
import by.epam.javatraining.beseda.webproject.model.dao.entitydao.UserDAO;
import by.epam.javatraining.beseda.webproject.model.entity.user.User;
import by.epam.javatraining.beseda.webproject.model.exception.daoexception.DAOLayerException;
import by.epam.javatraining.beseda.webproject.model.exception.daoexception.DAOTechnicalException;
import by.epam.javatraining.beseda.webproject.model.exception.entityexception.EntityLogicException;
import by.epam.javatraining.beseda.webproject.model.exception.serviceexception.ServiceLayerException;
import by.epam.javatraining.beseda.webproject.model.exception.serviceexception.ServiceLogicException;
import by.epam.javatraining.beseda.webproject.model.exception.serviceexception.ServiceTechnicalException;
import by.epam.javatraining.beseda.webproject.util.PasswordHash;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static by.epam.javatraining.beseda.webproject.model.service.ServiceConstants.*;

public class UserService extends AbstractService<User>{

    public UserService() {
        super();
        entityDAO = UserDAO.getDAO();
    }

    public boolean loginExists(String login) throws ServiceTechnicalException {
        return getUserByLogin(login) != null;
    }

    public boolean illegalPassword(String password) {
        boolean illegalPassword = false;
        if (password != null) {
            if (password.length() < PASSWORD_LENGTH
                    || password.matches(IS_WHITESPACE_CHARACTER)
                    || (!password.matches(IS_DIGIT))
                    || (!password.matches(IS_ALPHABETIC_CHARACTER))) {
                illegalPassword = true;
            }
        }
        return illegalPassword;
    }

    public List<User> getUsersByRole(String role) throws ServiceLayerException {
        List<User> newList = new ArrayList<>();
        if (role != null) {
            List<User> all = getAll();
            for (int i = 0; i < all.size(); i++) {
                User user = all.get(i);
                if (user.getRole().equals(role)) {
                    newList.add(user);
                }
            }
        }
        return newList;
    }


    public User getUserByLogin(String login) throws ServiceTechnicalException {
        User user = null;
        if (login != null) {
            try {
                user = ((UserDAO)entityDAO).getUserByLogin(login);
            } catch (DAOTechnicalException e) {
                throw new ServiceTechnicalException(e);
            }
        }
        return user;
    }

    /**
     * Creates entity with the given data without id
     */
    public User createEntity(String login, String password, String user_role) throws ServiceLayerException {
        User user = new User();
        if (login != null && password != null && user_role != null) {
            if (!loginExists(login) && !illegalPassword(password)) {
                byte[] pw = PasswordHash.getHash(password);
                try {
                    user.setLogin(login);
                    user.setPassword(pw);
                    user.setRole(user_role);
                } catch (EntityLogicException e) {
                    throw new ServiceLogicException(e);
                }
            }
        }
        return user;
    }

    public boolean changePassword(String login, String newPassword) throws ServiceLayerException {
        boolean succeed = false;
        if (newPassword != null && !illegalPassword(newPassword)) {
            byte[] pw = PasswordHash.getHash(newPassword);
            try {
                succeed = ((UserDAO)entityDAO).updatePassword(login, pw);
            } catch (DAOLayerException e) {
                throw new ServiceTechnicalException(e);
            }
        }
        return succeed;
    }

}
