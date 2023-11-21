package dev.uppercase.simplerest.demo.connector;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

    private String connectionString = "jdbc:mysql://localhost:3306/mysql";
    private String userString = "root";
    private String passwordString = "31072002oisvK";
    public Connection getConnection(){
        Connection dbConnection = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            dbConnection = DriverManager.getConnection(connectionString,userString, passwordString);
            return dbConnection;
        } catch (ClassNotFoundException e) {
            System.out.println("Драйвер не загрузился");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Коннекшен не создан");
            e.printStackTrace();
        }
        return getConnection();
    }
}