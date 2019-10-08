package by.epam.javatraining.beseda.webproject.dao.interfacedao;

import by.epam.javatraining.beseda.webproject.dao.exception.DAOLayerException;
import by.epam.javatraining.beseda.webproject.dao.exception.DAOTechnicalException;
import by.epam.javatraining.beseda.webproject.entity.user.User;

public interface UserInterface extends EntityDAO<User> {

    User getUserByLoginAndPassword(String login, byte[] password) throws DAOLayerException;

    User getUserByLogin(String login) throws DAOLayerException;

    boolean updatePassword(int id, byte[] password) throws DAOTechnicalException;
}
