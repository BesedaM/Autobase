package by.epam.javatraining.beseda.webproject.service.entityservice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.epam.javatraining.beseda.webproject.dao.entitydao.UserDAO;
import by.epam.javatraining.beseda.webproject.dao.exception.DAOLayerException;
import by.epam.javatraining.beseda.webproject.dao.exception.DAOTechnicalException;
import by.epam.javatraining.beseda.webproject.dao.interfacedao.UserInterface;
import by.epam.javatraining.beseda.webproject.entity.exception.EntityIdException;
import by.epam.javatraining.beseda.webproject.entity.exception.EntityLogicException;
import by.epam.javatraining.beseda.webproject.entity.user.User;
import by.epam.javatraining.beseda.webproject.logic.RegisterLogic;
import by.epam.javatraining.beseda.webproject.service.PasswordHash;
import by.epam.javatraining.beseda.webproject.service.exception.ServiceLayerException;
import by.epam.javatraining.beseda.webproject.service.exception.ServiceLogicException;
import by.epam.javatraining.beseda.webproject.service.exception.ServiceTechnicalException;

@Service
public class UserService extends AbstractEntityService<User> {

	@Autowired
	private UserInterface entityDAO;
	
	public UserService() {
		super();
	}

	
	
//    @Override
//    public final List<User> getAll() throws ServiceLayerException {
//        List<User> list = null;
//        try {
//            list = entityDAO.getAll();
//        } catch (DAOLayerException e) {
//            throw new ServiceLayerException(e);
//        }
//        return list;
//    }
//	
//    @Override
//    public final User getEntityById(int id) throws ServiceLayerException {
//        User entity = null;
//        if (id > 0) {
//            try {
//                entity = entityDAO.getEntityById(id);
//            } catch (DAOLayerException e) {
//                throw new ServiceTechnicalException(e);
//            }
//        }
//        return entity;
//    }
//
//    @Override
//    public List<User> getEntitiesByIdList(int[] idArr) throws ServiceLayerException {
//        List<User> list = null;
//        try {
//            list = entityDAO.getEntitiesByIdList(idArr);
//        } catch (DAOLayerException e) {
//            throw new ServiceLayerException(e);
//        }
//        return list;
//    }
//
//    /**
//     * Adds entityservice to database and assigns to entityservice the value of id
//     *
//     * @param entity entityservice to add
//     * @throws ServiceLayerException
//     */
//    @Override
//    public void add(User entity) throws ServiceLayerException {
//        if (entity != null) {
//            try {
//                int id = entityDAO.add(entity);
//                entity.setId(id);
//            } catch (DAOLayerException | EntityIdException e) {
//                throw new ServiceLogicException(e);
//            }
//        }
//    }
//
//    @Override
//    public void update(User entity) throws ServiceLayerException {
//        if (entity != null) {
//            try {
//                entityDAO.update(entity);
//            } catch (DAOLayerException e) {
//                throw new ServiceLogicException(e);
//            }
//        }
//    }
//
//    @Override
//    public final void delete(int id) throws ServiceLogicException {
//        if (id > 0) {
//            try {
//                entityDAO.delete(id);
//            } catch (DAOTechnicalException e) {
//                throw new ServiceLogicException(e);
//            }
//        }
//    }
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
	public final boolean loginExists(String login) throws ServiceLayerException {
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
	public final User getUserByLogin(String login) throws ServiceLayerException {
		User user = null;
		if (login != null) {
			try {
				user = ((UserDAO) entityDAO).getUserByLogin(login);
			} catch (DAOLayerException e) {
				throw new ServiceLayerException(e);
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
	public final User getUserByLoginAndPassword(String login, String password) throws ServiceLayerException {
		User user = null;
		if (login != null && password != null) {
			byte[] pw = PasswordHash.getHash(password);
			try {
				user = ((UserDAO) entityDAO).getUserByLoginAndPassword(login, pw);
			} catch (DAOLayerException e) {
				throw new ServiceLayerException(e);
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
	public final void changePassword(int id, String newPassword) throws ServiceLayerException {
		if (id > 0 && newPassword != null) {
			byte[] pw = PasswordHash.getHash(newPassword);
			try {
				((UserDAO) entityDAO).updatePassword(id, pw);
			} catch (DAOLayerException e) {
				throw new ServiceTechnicalException(e);
			}
		}
	}

}