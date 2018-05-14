package de.swt.inf.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DaoFactory {
    public static boolean test = false;

    private static String dbDriver = "";
    private static String dbUrl = "";
    private static String username = "";
    private static String password = "";


    public static Connection getConnection(){
        if (test) {
            dbDriver = "org.h2.Driver";
            dbUrl = "jdbc:h2:mem:testdb";
            username = "sa";
            password = "";
        } else {
            dbDriver = "org.mariadb.jdbc.Driver";
            dbUrl = "jdbc:mysql://localhost:3306/infswt";
            username = "root";
            password = "";
        }
        try{
            Class.forName(dbDriver);
            Connection dbConnection = DriverManager.getConnection(dbUrl,username,password);
            return dbConnection;
        }
        catch(SQLException ex){
            System.err.println(ex);
        }
        catch(ClassNotFoundException ex){
            System.err.println(ex);
        }
        return null;
    }

    public static UserDao getUserDao() {
        return new UserDaoImpl();
    }

    public static TerminDao getTerminDao() {
        return new TerminDaoImpl(getConnection());
    }

    public static VCardDao getVCardDao() {
        return new VCardDaoImpl(getConnection());
    }

    public static CategoryDao getCategoryDao() {return new CategoryDaoImpl(getConnection());
    }

}
