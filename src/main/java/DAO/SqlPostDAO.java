package DAO;

import DAO.Interfaces.PostDAO;
import Model.POSTTYPE;
import Model.Post;
import Model.Subject;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;
import java.util.List;

public class SqlPostDAO implements PostDAO {

    private final static String USERNAME = "root";
    private final static String PASSWORD = "Ikako2525";
    private final static String DBNAME = "stutor_db";
    private final static String TABLENAME = "posts";

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
        StringBuilder code = new StringBuilder();

        code.append("INSERT INTO " + TABLENAME + " (username, subject, type, text) VALUES (?, ?, ?, ? ); ");

        Connection connection = dataSource.getConnection();

        Statement st = connection.createStatement();
        st.execute("USE " + DBNAME + ";\n");
        st.close();

        PreparedStatement statement = connection.prepareStatement(code.toString());

        statement.setString(1, post.getUsername());
        statement.setString(2, post.getSubject());
        statement.setString(3, post.getType().toString());
        statement.setString(4, post.getText());

        int check = statement.executeUpdate();

        statement.close();
        connection.close();

        return check == 1;
    }

    @Override
    public boolean removePost(int post_id) throws SQLException {
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();

        statement.execute("USE " + DBNAME + ";\n");

        StringBuilder sb = new StringBuilder();

        sb.append("DELETE FROM ").append(TABLENAME).append(" WHERE ");
        sb.append("post_id = ").append(post_id).append(";");

        int check = statement.executeUpdate(sb.toString());

        statement.close();
        connection.close();

        return check == 1;
    }

    @Override
    public Post getPostById(int post_id) throws SQLException {
        Post res = null;

        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();

        statement.execute("USE " + DBNAME + ";\n");

        StringBuilder sb = new StringBuilder();

        sb.append("select * from ").append(TABLENAME);
        sb.append(" where post_id = ").append(post_id);
        sb.append(";");

        ResultSet rs = statement.executeQuery(sb.toString());

        while (rs.next()) {
            res = new Post(rs.getString("username"), rs.getString("subject"),
                    rs.getString("type"),
                    rs.getString("text"));
            res.setId(post_id);
        }

        statement.close();
        connection.close();

        return res;
    }

    @Override
    public List<Post> getPostByUser(String username, POSTTYPE type) throws SQLException {
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();

        statement.execute("USE " + DBNAME + ";\n");

        StringBuilder sb = new StringBuilder();

        sb.append("select * from ").append(TABLENAME).append(" WHERE ");
        sb.append("username = '").append(username).append("'");
        if (!type.toString().equals("both")) sb.append(" and type = '").append(type.toString()).append("'");
        sb.append(";");

        return getPosts(connection, statement, sb);
    }

    @Override
    public List<Post> getAllPosts(POSTTYPE type) throws SQLException {
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();

        statement.execute("USE " + DBNAME + ";\n");

        StringBuilder sb = new StringBuilder();

        sb.append("select * from ").append(TABLENAME);
        if (!type.toString().equals("both")) sb.append("where type = '").append(type.toString()).append("'");
        sb.append(";");

        return getPosts(connection, statement, sb);
    }

    public List<Post> getPosts() throws SQLException {
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();

        statement.execute("USE " + DBNAME + ";\n");

        StringBuilder sb = new StringBuilder();

        sb.append("select * from ").append(TABLENAME).append(" WHERE ");

        return getPosts(connection, statement, sb);
    }

    @Override
    public void clearPosts() throws SQLException {
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();

        StringBuilder code = new StringBuilder();

        statement.execute("USE " + DBNAME + ";\n");

        code.append("DELETE FROM posts;");

        statement.executeUpdate(code.toString());

        statement.close();
        connection.close();
    }

    private List<Post> getPosts(Connection connection, Statement statement, StringBuilder sb) throws SQLException {
        List<Post> res = null;
        ResultSet rs = statement.executeQuery(sb.toString());

        while (rs.next()) {
            Post post = new Post(rs.getString("username"), rs.getString("subject"),
                    rs.getString("type"),
                    rs.getString("text"));
            post.setId(rs.getInt("id"));
            res.add(post);
        }

        statement.close();
        connection.close();

        return res;
    }
}