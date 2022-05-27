package Database;
import java.sql.*;
public class DBConnection {
    Connection c = null;
    String username = "root";
    String password = "123456";
    String url = "jdbc:mysql://localhost:3306/stadyum";
    public DBConnection(){

    }
    public void Showeror(SQLException exception){
        System.out.println("eror code : "+exception.getMessage());
    }
    public  Connection connDB(){
        try {

            this.c = DriverManager.getConnection(url, username, password);
            System.out.println("Veritabanı bağlantısı gerçekleşti");
            return c;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return c;
    }
}
