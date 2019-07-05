package by.epam.javatraining.beseda.webproject.test;

import by.epam.javatraining.beseda.webproject.model.dao.util.connectionpool.ConnectionPool;
import by.epam.javatraining.beseda.webproject.model.dao.util.connectionpool.DBConnector;
import by.epam.javatraining.beseda.webproject.model.entity.user.User;
import by.epam.javatraining.beseda.webproject.model.service.exception.ServiceTechnicalException;
import by.epam.javatraining.beseda.webproject.model.service.factory.ServiceEntityFactory;

import java.sql.Connection;
import java.sql.SQLException;

import static by.epam.javatraining.beseda.webproject.model.dao.util.dataloader.DatabaseProperties.*;


public class MyTest {

    static ConnectionPool pool=DBConnector.createConnectionPool(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);

    public static void main(String[] args) throws SQLException, ServiceTechnicalException {
//        WrapperConnector connector = new WrapperConnector();
//               Statement st=connector.createStatement();
//        ResultSet res=st.executeQuery("SELECT * FROM autobase.users;");
//        res.first();
//        while(res.next()){
//            System.out.println(res.getInt(1)+" "+res.getString(2));
//        }

//        st.executeUpdate("DELETE FROM autobase.users WHERE id=11;");
//
//        PreparedStatement st = connector.prepareStatement("UPDATE autobase.users SET password=? WHERE id=?;");
//        String[] passwords = {"admin","fbh676","j65hj8","eet7e4","D6u99y","Nh89hkl"
//                ,"GooD56","Tik_Toe8","boria123","25tania"};
//
//        for (int i = 0; i < passwords.length; i++) {
//            st.setBytes(1, PasswordHash.getHash(passwords[i]));
//            st.setInt(2, i + 1);
//            st.addBatch();
//        }
//        st.executeBatch();

        Connection conn=pool.getConnection();
//        PreparedStatement st = conn.prepareStatement("SELECT login from autobase.users WHERE password=?;");
//        st.setBytes(1, PasswordHash.getHash("eet7e4"));
//        ResultSet res = st.executeQuery();
//        if (res.next()) {
//            System.out.println(res.getString(1));
//        }
//        conn.close();

        User user=ServiceEntityFactory.getFactory().getUserService().getUserByLoginAndPassword("admin","admin");
        System.out.println(user);
    }
}

