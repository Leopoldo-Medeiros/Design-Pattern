import java.sql.*;

/**
 * @author Leopoldo Medeiros_2017288
 * Code based on Amilcar's classes
 */

public class DataSource {

    private String db = "jdbc:mysql://52.50.23.197:3306/world";
    private String un = "cctstudent";
    private String pw = "Pass1234!";
    private Connection conn;
    private Statement stmt;
    private ResultSet rs = null;

    public DataSource() {

        try{

            // Get a connection to the database
            conn = DriverManager.getConnection( db, un, pw );

            // Get a statement from the connection
            stmt = conn.createStatement();
        }
        catch( SQLException se ){
            System.out.println( "SQL Exception:");

            // Loop through the SQL Exceptions
            while( se != null ){
                System.out.println( "State  : " + se.getSQLState());
                System.out.println( "Message: " + se.getMessage());
                System.out.println( "Error  : " + se.getErrorCode());

                se = se.getNextException();
            }
        }

        catch(Exception e) {
            System.out.println(e);
        }
    }

    // Saving data into the Database
    public boolean save(String query){

        try {
            stmt.execute(query);
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Receiving data and returns the ResultSet
    public ResultSet select(String query) {

        try {
            rs = stmt.executeQuery(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rs;
    }

    public void closing(){
        // Close the ResultSet, statement and the connection
        try {

            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}