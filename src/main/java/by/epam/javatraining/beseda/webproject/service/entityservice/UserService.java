package by.epam.javatraining.beseda.webproject.service.entityservice;

import java.util.ArrayList;
import java.util.List;

import by.epam.javatraining.beseda.webproject.dao.interfacedao.EntityDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import by.epam.javatraining.beseda.webproject.dao.entitydao.UserDAO;
import by.epam.javatraining.beseda.webproject.dao.interfacedao.UserInterface;
import by.epam.javatraining.beseda.webproject.entity.user.User;
import by.epam.javatraining.beseda.webproject.logic.RegisterLogic;
import by.epam.javatraining.beseda.webproject.service.PasswordHash;
import by.epam.javatraining.beseda.webproject.service.exception.ServiceLayerException;
import by.epam.javatraining.beseda.webproject.service.exception.ServiceTechnicalException;

@Service
public class UserService extends AbstractEntityService<User> {

	@Autowired
	private PasswordHash passwordEncoder;

	public UserService() {
		super();
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
			byte[] pw = passwordEncoder.getHash(password);
			user.setLogin(login);
			user.setPassword(pw);
			user.setRole(user_role);
		}
		return user;
	}

	@Qualifier("userDAO")
	@Autowired
	@Override
	public void setEntityDAO(EntityDAO<User> entityDAO) {
		this.entityDAO=entityDAO;
	}

	/**
	 * Checks if the specified login exists.
	 * 
	 * @param login login to check
	 * @return true if login exists
	 * @throws ServiceTechnicalException
	 */
	public final boolean loginExists(String login) throws ServiceLayerException {
		return getUserByLogin(login) != null;
	}

	/**
	 * Returns the list of users by the concrete role.
	 * 
	 * @param role user role
	 * @return list of users
	 */
	public final List<User> getUsersByRole(String role){
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
	 */
	public final User getUserByLogin(String login) {
		User user = null;
		if (login != null) {
			user = ((UserDAO) entityDAO).getUserByLogin(login);
		}
		return user;
	}

	/**
	 * Returns user by login and password.
	 * 
	 * @param login    user login
	 * @param password user password
	 * @return user
	 */
	public final User getUserByLoginAndPassword(String login, String password){
		User user = null;
		if (login != null && password != null) {
			byte[] pw = passwordEncoder.getHash(password);
			user = ((UserDAO) entityDAO).getUserByLoginAndPassword(login, pw);
		}
		return user;
	}

	/**
	 * Changes password for given user.
	 * 
	 * @param id          user id
	 * @param newPassword new password for user
	 */
	public final void changePassword(int id, String newPassword){
		if (id > 0 && newPassword != null) {
			byte[] pw = passwordEncoder.getHash(newPassword);
			((UserDAO) entityDAO).updatePassword(id, pw);
		}
	}

}