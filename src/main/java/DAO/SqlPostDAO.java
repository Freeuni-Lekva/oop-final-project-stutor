package DAO;

import DAO.Interfaces.PostDAO;
import Model.Post;
import Model.Subject;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.SQLException;
import java.util.List;

public class SqlPostDAO implements PostDAO {

    private final static String USERNAME = "root";
    private final static String PASSWORD = "apokalips";
    private final static String DBNAME = "stutor_db";
    private final static String TABLE_NAME = "posts";

    BasicDataSource dataSource;

    public SqlPostDAO() throws ClassNotFoundException {
        dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/" + DBNAME);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);
        Class.forName("com.mysql.cj.jdbc.Driver");
    }


    @Override
    public boolean addPost(Post post) throws SQLException {
        return false;
    }

    @Override
    public boolean removePost(int post_id) throws SQLException {
        return false;
    }

    @Override
    public Post getPostById(int post_id) throws SQLException {
        return null;
    }

    @Override
    public List<Post> getPostByUser(int user_id) throws SQLException {
        return null;
    }

    @Override
    public List<Post> getAllPosts() throws SQLException {
        return null;
    }

    @Override
    public void clearPosts() throws SQLException {

    }
}
