package by.epam.javatraining.beseda.webproject.service.dependenceservice;

import by.epam.javatraining.beseda.webproject.dao.dependencedao.DependenceDAO;
import by.epam.javatraining.beseda.webproject.dao.exception.DAOTechnicalException;
import by.epam.javatraining.beseda.webproject.dao.dependencedao.DAODependenceFactory;
import by.epam.javatraining.beseda.webproject.entity.EntityBase;
import by.epam.javatraining.beseda.webproject.service.exception.ServiceTechnicalException;

public abstract class DependenceService<M extends EntityBase, K extends EntityBase> {

	protected static DAODependenceFactory daoDependenceFactory = DAODependenceFactory.getFactory();
	protected DependenceDAO<M, K> dependenceDAO;

	protected DependenceService() {
	}

	public void addDependence(M entity01, K entity02) throws ServiceTechnicalException {
		if (entity01 != null && entity02 != null) {
			try {
				dependenceDAO.setDependence(entity01, entity02);
			} catch (DAOTechnicalException e) {
				throw new ServiceTechnicalException(e);
			}
		}
	}

	public void addDependence(int entity01Id, int entity02Id) throws ServiceTechnicalException {
		if (entity01Id > 0 && entity02Id > 0) {
			try {
				dependenceDAO.setDependence(entity01Id, entity02Id);
			} catch (DAOTechnicalException e) {
				throw new ServiceTechnicalException(e);
			}
		}
	}
}