package DAO;

import DAO.Interfaces.PostDAO;
import Model.POSTTYPE;
import Model.Post;
import junit.framework.TestCase;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import java.sql.SQLException;
import java.util.List;

public class SqlPostDAOTest extends TestCase {
    private static PostDAO postDAO;
    private ConnectionPool connectionPool;

    @BeforeAll
    public void setUp() throws ClassNotFoundException, SQLException {
        postDAO = new SqlPostDAO();
        connectionPool = new ConnectionPool();
        postDAO.clearPosts();
    }

    @AfterEach
    public void clearUp() throws SQLException {
        postDAO.clearPosts();
    }

    @AfterAll
    public static void clearUpEverything() throws SQLException {
        postDAO.clearPosts();
    }

    public void testAdd() throws SQLException {
        Post post = new Post("akali", "asasinoba", "teach", "perfect execution");

        assertTrue(postDAO.addPost(post));
        assertTrue(postDAO.addPost(post));
    }

   public void testGetAllAndRemove() throws SQLException {
       Post post = new Post("akali", "asasinoba", "teach", "perfect execution");

       for(int i = 0; i < 4; i++){
           assertTrue(postDAO.addPost(post));
       }

       List<Post> posts = postDAO.getAllPosts(POSTTYPE.toPostType("all"));
       assertEquals(4, posts.size());

       for (Post value : posts) {
           assertTrue(postDAO.removePost(value.getId()));
       }

       posts = postDAO.getAllPosts(POSTTYPE.toPostType("all"));

       assertEquals(0, posts.size());
   }

   public void testGetPostByUserAndGetPost() throws SQLException {
       Post post1 = new Post("akali", "asasinoba", "teach", "perfect execution");

       Post post2 = new Post("ezreal", "qurdoba", "teach", "mystic");

       for(int i = 0; i < 4; i++){
           assertTrue(postDAO.addPost(post1));
       }

       for(int i = 0; i < 4; i++){
           assertTrue(postDAO.addPost(post2));
       }

       List<Post> posts = postDAO.getAllPosts(POSTTYPE.toPostType("all"));
       assertEquals(8, posts.size());

       posts = postDAO.getPostByUser("akali", POSTTYPE.toPostType("all"));
       assertEquals(4, posts.size());

       for(int i = 0; i < posts.size(); i++){
           assertTrue(posts.contains(postDAO.getPostById(posts.get(i).getId())));
       }
   }
}