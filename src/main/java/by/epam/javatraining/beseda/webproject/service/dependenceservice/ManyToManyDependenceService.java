package by.epam.javatraining.beseda.webproject.service.dependenceservice;

import by.epam.javatraining.beseda.webproject.dao.dependencedao.ManyToManyDependenceDAO;
import by.epam.javatraining.beseda.webproject.dao.exception.DAOLayerException;
import by.epam.javatraining.beseda.webproject.dao.exception.DAOTechnicalException;
import by.epam.javatraining.beseda.webproject.entity.EntityBase;
import by.epam.javatraining.beseda.webproject.service.exception.ServiceLayerException;
import by.epam.javatraining.beseda.webproject.service.exception.ServiceTechnicalException;

public abstract class ManyToManyDependenceService<M extends EntityBase, K extends EntityBase>
		extends DependenceService<M, K> {

	protected ManyToManyDependenceService() {
		super();
	}

	public void deleteDependence(M entity01, K entity02) throws ServiceTechnicalException {
		if (entity01 != null && entity02 != null) {
			try {
				((ManyToManyDependenceDAO<M, K>) dependenceDAO).deleteDependence(entity01, entity02);
			} catch (DAOTechnicalException e) {
				throw new ServiceTechnicalException();
			}
		}
	}

	public void deleteDependence(int entity01Id, int entity02Id) throws ServiceTechnicalException {
		if (entity01Id > 0 && entity02Id > 0) {
			try {
				((ManyToManyDependenceDAO<M, K>) dependenceDAO).deleteDependence(entity01Id, entity02Id);
			} catch (DAOTechnicalException e) {
				throw new ServiceTechnicalException();
			}
		}
	}

	public int[] getEntities01Id(K entity) throws ServiceLayerException {
		int[] entityId01 = null;
		if (entity != null) {
			try {
				entityId01 = ((ManyToManyDependenceDAO<M, K>) dependenceDAO).getDependenceId(entity);
			} catch (DAOLayerException e) {
				throw new ServiceLayerException(e);
			}
		}
		return entityId01;
	}

	public int[] getEntities02Id(M entity) throws ServiceLayerException {
		int[] entityId02 = null;
		if (entity != null) {
			try {
				entityId02 = ((ManyToManyDependenceDAO<M, K>) dependenceDAO).getDependenceId(entity);
			} catch (DAOLayerException e) {
				throw new ServiceLayerException(e);
			}
		}
		return entityId02;
	}
}