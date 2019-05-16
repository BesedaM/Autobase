package by.epam.javatraining.beseda.webproject.model.dao.entitydao;

import by.epam.javatraining.beseda.webproject.model.entity.route.Address;
import by.epam.javatraining.beseda.webproject.model.exception.EntityException.EntityLogicException;
import by.epam.javatraining.beseda.webproject.util.database.SQLQuery;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static by.epam.javatraining.beseda.webproject.util.database.DBEntityTableName.*;
import static by.epam.javatraining.beseda.webproject.util.database.SQLQuery.*;

public class AddressDAO extends AbstractDAO<Address> {

    private static AddressDAO instance = null;

    private AddressDAO() {
        super();
    }

    public static AddressDAO getDAO() {
        if (instance == null) {
            instance = new AddressDAO();
        }
        return instance;
    }

    @Override
    protected Address createEntity(ResultSet result) throws SQLException, EntityLogicException {
        Address adr = new Address();
        adr.setId(result.getInt(SQLQuery.ADDRESS_ID));
        adr.setCountry(result.getString(COUNTRY));
        adr.setDistrict(result.getString(DISTRICT));
        adr.setCity(result.getString(CITY));
        adr.setHouse(result.getInt(HOUSE_NUMBER));
        adr.setStreet(result.getString(STREET));
        adr.setBuilding(result.getString(BUILDING));
        return adr;
    }

    @Override
    protected String getAllStatement() {
        return SELECT_ALL_ADDRESSES;
    }

    @Override
    protected String findEntityByIdStatement() {
        return SELECT_ADDRESS_BY_ID;
    }

    @Override
    protected String deleteStatement(){
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
    protected void setDataOnPreparedStatement(PreparedStatement st, Address addr) throws SQLException {
        st.setString(1, addr.getCountry());
        st.setString(2, addr.getDistrict());
        st.setString(3, addr.getCity());
        st.setString(4, addr.getStreet());
        st.setInt(5, addr.getHouse());
        st.setString(6, addr.getBuilding());
    }

}
