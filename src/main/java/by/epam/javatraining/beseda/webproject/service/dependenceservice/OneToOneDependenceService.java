package by.epam.javatraining.beseda.webproject.service.dependenceservice;

import by.epam.javatraining.beseda.webproject.dao.dependencedao.OneToOneDependenceDAO;
import by.epam.javatraining.beseda.webproject.dao.exception.DAOLayerException;
import by.epam.javatraining.beseda.webproject.entity.EntityBase;
import by.epam.javatraining.beseda.webproject.service.exception.ServiceLayerException;

public abstract class OneToOneDependenceService<M extends EntityBase, K extends EntityBase>
		extends ToOneDependenceService<M, K> {

	protected OneToOneDependenceService() {
		super();
	}

	public int getEntityIdByDependence(K entity) throws ServiceLayerException {
		int entityId01 = -1;
		if (entity != null) {
			try {
				entityId01 = ((OneToOneDependenceDAO<M, K>) dependenceDAO).getEntityIdByDependence(entity);
			} catch (DAOLayerException e) {
				throw new ServiceLayerException(e);
			}
		}
		return entityId01;
	}

}