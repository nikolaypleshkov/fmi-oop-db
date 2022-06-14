import java.sql.*;

public class DBConnection {

    static Connection conn = null;

    public static Connection getConnected() {

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/course_project", "postgres", "root");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return conn;

    }

    public static MyModel getAllModel() {

        conn = getConnected();
        String sql = "SELECT movie_id,M.TITLE,genre,M.year,D.FNAME,D.LNAME FROM movies M JOIN DIRECTOR D ON M.DIRECTOR_ID = D.DIRECTOR_ID JOIN GENRE G ON M.GENRE_ID = G.GENRE_ID";
        ResultSet result = null;
        MyModel model = null;

        try {
            PreparedStatement state = conn.prepareStatement(sql);
            result = state.executeQuery();
            model = new MyModel(result);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return model;

    }

    public static MyModel getAllDirectors() {

        conn = getConnected();
        String sql = "select * from director;";
        ResultSet result = null;
        MyModel model = null;

        try {
            PreparedStatement state = conn.prepareStatement(sql);
            result = state.executeQuery();
            model = new MyModel(result);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return model;

    }

    public static MyModel getAllGenre() {

        conn = getConnected();
        String sql = "select * from GENRE;";
        ResultSet result = null;
        MyModel model = null;

        try {
            PreparedStatement state = conn.prepareStatement(sql);
            result = state.executeQuery();
            model = new MyModel(result);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return model;

    }

    public static MyModel getComplexSorting(int created,String genre) {

        conn = getConnected();

        System.out.println("Created: " + created);
        System.out.println("genre: " + genre);

        String sql = "SELECT  title,year, genre FROM genre G JOIN movies M ON G.genre_ID = M.genre_ID WHERE year=" + created
                + "AND genre='" + genre + "';";
        ResultSet result = null;
        MyModel model = null;

        try {
            PreparedStatement state = conn.prepareStatement(sql);
            result = state.executeQuery();
            model = new MyModel(result);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return model;
    }
}
