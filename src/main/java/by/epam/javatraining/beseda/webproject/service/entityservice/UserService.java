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

	/**
	 * Creates entity with the given data WITHOUT id
	 * 
	 * @param login
	 * @param password
	 * @param user_role
	 * @return
	 * @throws ServiceLayerException
	 */
	public User createEntity(String login, String password, String user_role) throws ServiceLayerException {
		User user = new User();
		if (login != null && password != null && user_role != null && !loginExists(login)
				&& RegisterLogic.legalPassword(password)) {
			byte[] pw = PasswordHash.getHash(password);
			try {
				user.setLogin(login);
				user.setPassword(pw);
				user.setRole(user_role);
			} catch (EntityLogicException e) {
				throw new ServiceLogicException(e);
			}
		}
		return user;
	}

	/**
	 * Checks if the specified login exists.
	 * 
	 * @param login login to check
	 * @return true if login exists
	 * @throws ServiceTechnicalException
	 */
	public final boolean loginExists(String login) throws ServiceTechnicalException {
		return getUserByLogin(login) != null;
	}

	/**
	 * Returns the list of users by the concrete role.
	 * 
	 * @param role user role
	 * @return list of users
	 * @throws ServiceLayerException
	 */
	public final List<User> getUsersByRole(String role) throws ServiceLayerException {
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

	/**
	 * Returns the user by login.
	 * 
	 * @param login user login
	 * @return user
	 * @throws ServiceTechnicalException
	 */
	public final User getUserByLogin(String login) throws ServiceTechnicalException {
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

	/**
	 * Returns user by login and password.
	 * 
	 * @param login    user login
	 * @param password user password
	 * @return user
	 * @throws ServiceTechnicalException
	 */
	public final User getUserByLoginAndPassword(String login, String password) throws ServiceTechnicalException {
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
	 * Changes password for given user.
	 * 
	 * @param id          user id
	 * @param newPassword new password for user
	 * @return true if method succeeds
	 * @throws ServiceLayerException
	 */
	public final boolean changePassword(int id, String newPassword) throws ServiceLayerException {
		boolean succeed = false;
		if (id > 0 && newPassword != null) {
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