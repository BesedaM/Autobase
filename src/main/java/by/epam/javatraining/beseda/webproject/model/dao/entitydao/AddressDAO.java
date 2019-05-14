package by.epam.javatraining.beseda.webproject.model.dao.entitydao;

import by.epam.javatraining.beseda.webproject.model.entity.route.Address;
import by.epam.javatraining.beseda.webproject.model.exception.DAOexception.DAOLayerException;
import by.epam.javatraining.beseda.webproject.model.exception.DAOexception.DAOTechnicalException;
import by.epam.javatraining.beseda.webproject.util.database.DBEntityTableName;
import by.epam.javatraining.beseda.webproject.util.database.SQLQuery;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static by.epam.javatraining.beseda.webproject.util.database.DBEntityTableName.*;
import static by.epam.javatraining.beseda.webproject.util.database.SQLQuery.*;

public class AddressDAO extends AbstractDAO<Address> {

    private Logger log = Logger.getLogger(AddressDAO.class.getSimpleName());
    private static AddressDAO instance = null;

    private AddressDAO() {
        super();
        this.dbTableName = DBEntityTableName.T_ADDRESSES;
    }

    public static AddressDAO getDAO() {
        if (instance == null) {
            instance = new AddressDAO();
        }
        return instance;
    }

    @Override
    public List<Address> getAll() throws DAOLayerException {
        List<Address> list = new ArrayList<>();
        Statement st = null;
        try {
            st = connector.createStatement();
            ResultSet result = st.executeQuery(SELECT_ALL_ADDRESSES + END_OF_STATEMENT);
            while (result.next()) {
                Address adr = createAddress(result);
                list.add(adr);
            }
        } catch (SQLException e) {
            throw new DAOTechnicalException("Error retrieving data from database", e);
        } finally {
            closeStatement(st);
        }
        return list;
    }

    @Override
    public Address findEntityById(int id) throws DAOTechnicalException {
        Address adr;
        PreparedStatement st = null;
        try {
            st = connector.prepareStatement(SELECT_ADDRESS_BY_ID);
            st.setInt(1, id);
            ResultSet res = st.executeQuery();
            adr = createAddress(res);
        } catch (SQLException e) {
            throw new DAOTechnicalException("Error retrieving data from database", e);
        } finally {
            closeStatement(st);
        }
        return adr;
    }

    @Override
    protected String setAddStetement() {
        return INSERT_ADDRESS;
    }

    @Override
    protected String setDeleteStetement(){
        return DELETE_ADDRESS_BY_ID;
    }

    @Override
    public void update(Address addr) throws DAOLayerException {
        PreparedStatement st = null;
        try {
            st = connector.prepareStatement(UPDATE_ADDRESS);
            setDataOnPreparedStatement(st, addr);
            st.setInt(7,addr.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOTechnicalException("Error updating database", e);
        } finally {
            closeStatement(st);
        }
    }

    private Address createAddress(ResultSet result) throws SQLException {
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
    protected void setDataOnPreparedStatement(PreparedStatement st, Address addr) throws SQLException {
        st.setString(1, addr.getCountry());
        st.setString(2, addr.getDistrict());
        st.setString(3, addr.getCity());
        st.setString(4, addr.getStreet());
        st.setInt(5, addr.getHouse());
        st.setString(6, addr.getBuilding());
    }

}
