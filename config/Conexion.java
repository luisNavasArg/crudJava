package config;
import java.sql.*;

public class Conexion {
    public String driver = "com.mysql.jdbc.Driver";
    public Connection getConnection(){  
        Connection conexion = null;
        try{
            Class.forName(driver);
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/codoacodo",
                    "luis","123456");           
        }catch(ClassNotFoundException | SQLException e){
            System.out.println(e.toString());
        }       
        return conexion;
    }  
}