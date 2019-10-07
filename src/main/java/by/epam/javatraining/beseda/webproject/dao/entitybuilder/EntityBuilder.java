package by.epam.javatraining.beseda.webproject.dao.entitybuilder;

import java.sql.ResultSet;
import java.sql.SQLException;

import by.epam.javatraining.beseda.webproject.dao.exception.CarTypeNotPresentException;
import by.epam.javatraining.beseda.webproject.entity.EntityBase;
import by.epam.javatraining.beseda.webproject.entity.exception.EntityLogicException;

public interface EntityBuilder<T extends EntityBase> {
	
	T buildEntity(ResultSet result) throws SQLException, EntityLogicException, CarTypeNotPresentException;
}
