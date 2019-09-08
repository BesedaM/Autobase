package by.epam.javatraining.beseda.webproject.service.entityservice;

import by.epam.javatraining.beseda.webproject.dao.entitydao.UserDAO;
import by.epam.javatraining.beseda.webproject.entity.user.User;
import by.epam.javatraining.beseda.webproject.dao.exception.DAOLayerException;
import by.epam.javatraining.beseda.webproject.dao.exception.DAOTechnicalException;
import by.epam.javatraining.beseda.webproject.entity.exception.EntityLogicException;
import by.epam.javatraining.beseda.webproject.logic.RegisterLogic;
import by.epam.javatraining.beseda.webproject.service.exception.ServiceLayerException;
import by.epam.javatraining.beseda.webproject.service.exception.ServiceLogicException;
import by.epam.javatraining.beseda.webproject.service.exception.ServiceTechnicalException;
import by.epam.javatraining.beseda.webproject.service.PasswordHash;

import java.util.ArrayList;
import java.util.List;

public class UserService extends AbstractEntityService<User> {

    UserService() {
        super();
        entityDAO = mySQLDAOEntityFactory.getUserDAO();
    }


    public boolean loginExists(String login) throws ServiceTechnicalException {
        return getUserByLogin(login) != null;
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
     * Creates entityservice with the given data WITHOUT id
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

    public boolean changePassword(int id, String newPassword) throws ServiceLayerException {
        boolean succeed = false;
        if (id>0 && newPassword != null) {
            byte[] pw = PasswordHash.getHash(newPassword);
            try {
                succeed = ((UserDAO) entityDAO).updatePassword(id, pw);
            } catch (DAOLayerException e) {
                throw new ServiceTechnicalException(e);
            }
        }
        return succeed;
    }

}
