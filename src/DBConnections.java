import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;

public class DBConnections {
    private static final String URL = "jdbc:postgresql://localhost:5433/Gradebook";
//    private static final String DATABASE = "GradeBook";
    private static final String USER = "postgres";
    private static final String PASSWORD = "basecamp";

    public static Connection getConnection() {
        try {
            System.out.println("Connection Established");
            return DriverManager.getConnection( URL, USER, PASSWORD);


        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database", e);
        }
    }
}


