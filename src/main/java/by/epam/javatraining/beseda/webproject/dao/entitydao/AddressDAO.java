package by.epam.javatraining.beseda.webproject.dao.entitydao;

import by.epam.javatraining.beseda.webproject.dao.interfacedao.AddressInterface;
import by.epam.javatraining.beseda.webproject.entity.route.Address;
import by.epam.javatraining.beseda.webproject.dao.exception.NotEnoughArgumentsException;
import by.epam.javatraining.beseda.webproject.entity.exception.EntityLogicException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static by.epam.javatraining.beseda.webproject.dao.util.database.DBEntityTable.*;
import static by.epam.javatraining.beseda.webproject.dao.util.database.SQLQuery.*;

public class AddressDAO extends AbstractDAO<Address> implements AddressInterface {

    AddressDAO() {
        super();
    }

    @Override
    protected Address buildEntity(ResultSet result) throws SQLException, EntityLogicException {
        Address addr = null;
        if (result != null) {
            addr = new Address();
            addr.setId(result.getInt(ID));
            addr.setCountry(result.getString(COUNTRY));
            addr.setDistrict(result.getString(DISTRICT));
            addr.setCity(result.getString(CITY));
            addr.setStreet(result.getString(STREET));
            addr.setHouse(result.getInt(HOUSE_NUMBER));
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
    protected String getEntityListByIdStatement() {
        return SELECT_ADDRESSES_BY_ID_LIST;
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
