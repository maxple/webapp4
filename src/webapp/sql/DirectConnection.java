package webapp.sql;

import webapp.WebAppException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import static webapp.Config.*;

/**
 * User: gkislin
 * Date: 06.11.13
 */
public class DirectConnection implements ConnectionFactory {

    public DirectConnection()  {
        try {
            Class.forName("org.postgresql.Driver").newInstance();
        } catch (Exception e) {
            throw new WebAppException("Driver initialization exception", e);
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection( DB_URL, DB_USER, DB_PASSWORD);
//                "jdbc:postgresql://ec2-54-247-99-244.eu-west-1.compute.amazonaws.com:5432/dap8baaauorm64?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory",
//                "elfkolfqypggvo", "vmnWGnCVY5jbSD5nrpnYdU-FEd");

    }
}
