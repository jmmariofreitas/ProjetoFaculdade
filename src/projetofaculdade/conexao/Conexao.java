
package projetofaculdade.conexao;

/**
 *
 * @author Jmariofreitas
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    
    private static final String url = "jdbc:mysql://localhost:3306/projetodb";
    private static final String user = "root";
    private static final String password = "root";

    private static Connection conn;
    
    public static Connection gConnection(){
        try {
            if (conn == null || conn.isClosed()) {
                conn = DriverManager.getConnection(url, user, password);
            }
            return conn;
        } 
        catch (SQLException e) {
            return null;
        }
    }
}
