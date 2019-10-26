import java.sql.*;

public class Test {

    private static Connection conn;
    private static Statement stat;
    private static PreparedStatement prepst;

    public static void main(String[] args) throws SQLException {

        try {
            connect();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        createTable();
        updateTable();
        deleteTable();
        close();


    }

    public static void createTable() throws SQLException {
        stat.executeUpdate("INSERT INTO students (name,score) VALUES ('Bob', 211)");
        stat.executeUpdate("INSERT INTO students (name,score) VALUES ('Bob3', 202)");
        stat.executeUpdate("INSERT INTO students (name,score) VALUES ('Bob34', 100)");



    }

    public static void  updateTable() throws SQLException {

        ResultSet rs = stat.executeQuery("SELECT * FROM students  ");
        while (rs.next()){
            System.out.println(rs.getInt(1) + rs.getString("name") + rs.getString("score"));
        }
        int res = stat.executeUpdate("UPDATE students SET name = 'Max' WHERE score = 211");

        rs =stat.executeQuery("SELECT * FROM students  ");

        while (rs.next()){
            System.out.println(rs.getInt(1) + rs.getString("name") + rs.getString("score"));
        }
    }

    public static void deleteTable() throws SQLException {
        int res = stat.executeUpdate("DELETE FROM students WHERE id = 'Bob3'");
        ResultSet rs = stat.executeQuery("SELECT * FROM students");
        while(rs.next()){
            System.out.println(rs.getInt(1)+" " + rs.getString("name")+" " + rs.getString("score"));
        }
    }

    public static void connect() throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        conn = DriverManager.getConnection("jdbc:sqlite:main.db");
        stat = conn.createStatement();

    }

    public  static void close() throws SQLException {
        conn.close();
    }
}
