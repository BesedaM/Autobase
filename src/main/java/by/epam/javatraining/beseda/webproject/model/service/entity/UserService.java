package by.epam.javatraining.beseda.webproject.model.service.entity;

import by.epam.javatraining.beseda.webproject.model.dao.entitydao.UserDAO;
import by.epam.javatraining.beseda.webproject.model.entity.user.User;
import by.epam.javatraining.beseda.webproject.model.dao.exception.DAOLayerException;
import by.epam.javatraining.beseda.webproject.model.dao.exception.DAOTechnicalException;
import by.epam.javatraining.beseda.webproject.model.exception.entityexception.EntityLogicException;
import by.epam.javatraining.beseda.webproject.model.logic.RegisterLogic;
import by.epam.javatraining.beseda.webproject.model.service.exception.ServiceLayerException;
import by.epam.javatraining.beseda.webproject.model.service.exception.ServiceLogicException;
import by.epam.javatraining.beseda.webproject.model.service.exception.ServiceTechnicalException;
import by.epam.javatraining.beseda.webproject.model.logic.PasswordHash;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static by.epam.javatraining.beseda.webproject.model.service.ServiceConstants.*;

public class UserService extends AbstractEntityService<User> {

    public UserService() {
        super();
        entityDAO = daoEntityFactory.getUserDAO();
    }


    public boolean loginExists(String login) throws ServiceTechnicalException {
        return getUserByLogin(login) != null;
    }

    public boolean legalPassword(String password) {
        Pattern pattern = Pattern.compile(PASSWORD_VALIDATE);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
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
                user = ((UserDAO) entityDAO).getUserByLogin(login);
            } catch (DAOTechnicalException e) {
                throw new ServiceTechnicalException(e);
            }
        }
        return user;
    }

    public User getUserByLoginAndPassword(String login, String password) throws ServiceTechnicalException {
        User user = null;
        if (login != null && password != null) {
            byte[] pw = PasswordHash.getHash(password);
            try {
                user = ((UserDAO) entityDAO).getUserByLoginAndPassword(login, pw);
            } catch (DAOTechnicalException e) {
                throw new ServiceTechnicalException(e);
            }
        }
        return user;
    }

    /**
     * Creates entity with the given data WITHOUT id
     */
    public User createEntity(String login, String password, String user_role) throws ServiceLayerException {
        User user = new User();


        if (login != null && password != null && user_role != null) {

            if (!loginExists(login) && RegisterLogic.legalPassword(password)) {
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
        if (login != null && newPassword != null && legalPassword(newPassword)) {
            byte[] pw = PasswordHash.getHash(newPassword);
            try {
                succeed = ((UserDAO) entityDAO).updatePassword(login, pw);
            } catch (DAOLayerException e) {
                throw new ServiceTechnicalException(e);
            }
        }
        return succeed;
    }

}
