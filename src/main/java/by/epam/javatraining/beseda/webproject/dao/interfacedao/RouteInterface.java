package by.epam.javatraining.beseda.webproject.dao.interfacedao;

import by.epam.javatraining.beseda.webproject.dao.exception.DAOTechnicalException;
import by.epam.javatraining.beseda.webproject.entity.route.Route;

public interface RouteInterface extends EntityDAO<Route> {

    void updateRouteStatus(int id, String status) throws DAOTechnicalException;
}
