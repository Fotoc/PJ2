package Projecte2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnexioBD {

    private static Connection conn = null;
    private String driver;
    private String url;
    private String usuario;
    private String password;

    
    public ConnexioBD() {

        String url = "jdbc:mysql://localhost:3306/dama_gimnas";
        String driver = "com.mysql.jdbc.Driver";
        String usuario = "root";
        String password = "1234";

        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, usuario, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    } 

    public static Connection getConnection() {

        if (conn == null) {
            new ConnexioBD();
        }

        return conn;
    }
    
    
    public void tancarConnexioBD() throws SQLException {
        
    }
   
}