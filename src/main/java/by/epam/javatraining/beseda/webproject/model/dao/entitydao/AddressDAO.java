package by.epam.javatraining.beseda.webproject.model.dao.entitydao;

import by.epam.javatraining.beseda.webproject.model.entity.route.Address;
import by.epam.javatraining.beseda.webproject.model.dao.exception.NotEnoughArgumentsException;
import by.epam.javatraining.beseda.webproject.model.exception.entityexception.EntityLogicException;
import by.epam.javatraining.beseda.webproject.model.dao.util.database.SQLQuery;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static by.epam.javatraining.beseda.webproject.model.dao.util.database.DBEntityTable.*;
import static by.epam.javatraining.beseda.webproject.model.dao.util.database.SQLQuery.*;

public class AddressDAO extends AbstractDAO<Address> {

    public AddressDAO() {
        super();
    }

//    private static class SingletonHolder {
//        public static final AddressDAO instance = new AddressDAO();
//    }
//
//    public static AddressDAO getDAO() {
//        return SingletonHolder.instance;
//    }


    @Override
    protected Address createEntity(ResultSet result) throws SQLException, EntityLogicException {
        Address addr = null;
        if (result != null) {
            addr = new Address();
            addr.setId(result.getInt(SQLQuery.ADDRESS_ID));
            addr.setCountry(result.getString(COUNTRY));
            addr.setDistrict(result.getString(DISTRICT));
            addr.setCity(result.getString(CITY));
            addr.setHouse(result.getInt(HOUSE_NUMBER));
            addr.setStreet(result.getString(STREET));
            addr.setBuilding(result.getString(BUILDING));
        }
        return addr;
    }

    @Override
    protected String getAllStatement() {
        return SELECT_ALL_ADDRESSES;
    }

    @Override
    protected String getEntityByIdStatement() {
        return SELECT_ADDRESS_BY_ID;
    }

    @Override
    protected String deleteStatement() {
        return DELETE_ADDRESS_BY_ID;
    }

    @Override
    protected String addStatement() {
        return INSERT_ADDRESS;
    }

    @Override
    protected String updateStatement() {
        return UPDATE_ADDRESS;
    }

    @Override
    protected int updateIdParameterNumber() {
        return 7;
    }

    @Override
    protected void setDataOnPreparedStatement(PreparedStatement st, Address addr) throws SQLException, NotEnoughArgumentsException {
        if (st != null && addr != null) {
            st.setString(1, addr.getCountry());
            st.setString(2, addr.getDistrict());
            st.setString(3, addr.getCity());
            st.setString(4, addr.getStreet());
            st.setInt(5, addr.getHouse());
            st.setString(6, addr.getBuilding());
        } else {
            throw new NotEnoughArgumentsException();
        }
    }

}
