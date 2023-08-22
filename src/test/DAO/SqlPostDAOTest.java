package DAO;

import DAO.Interfaces.PostDAO;
import DAO.Interfaces.SubjectDAO;
import DAO.Interfaces.UserDAO;
import Model.POSTTYPE;
import Model.Post;
import Model.Subject;
import Model.User;
import junit.framework.TestCase;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import java.sql.SQLException;
import java.util.List;

public class SqlPostDAOTest extends TestCase {
    private static PostDAO postDAO;
    private static UserDAO userDAO;
    private static SubjectDAO subjectDAO;
    private ConnectionPool connectionPool;

    @BeforeAll
    public void setUp() throws ClassNotFoundException, SQLException {
        postDAO = new SqlPostDAO();
        userDAO = new SqlUserDAO();
        subjectDAO = new SqlSubjectDAO();
        connectionPool = new ConnectionPool();
        postDAO.clearPosts();
        userDAO.clearUsers();
        subjectDAO.clearSubjects();
    }

    @AfterEach
    public void clearUp() throws SQLException {
        postDAO.clearPosts();
        userDAO.clearUsers();
        subjectDAO.clearSubjects();
    }

    @AfterAll
    public static void clearUpEverything() throws SQLException {
        postDAO.clearPosts();
        userDAO.clearUsers();
        subjectDAO.clearSubjects();
    }

    public void testAdd() throws SQLException {
        Post post = new Post("akali", "asasinoba", "teach", "perfect execution");
        User user = new User("akali", "1", "shuriken", "flip", "execute");
        Subject subject = new Subject("asasinoba");

        assertTrue(userDAO.addUser(user));
        assertTrue(subjectDAO.addSubject(subject));

        assertTrue(postDAO.addPost(post));
        assertTrue(postDAO.addPost(post));

        postDAO.clearPosts();
        userDAO.clearUsers();
        subjectDAO.clearSubjects();
    }

   public void testGetAllAndRemove() throws SQLException {
       Post post = new Post("akali", "asasinoba", "teach", "perfect execution");

       User user = new User("akali", "1", "shuriken", "flip", "execute");
       Subject subject = new Subject("asasinoba");

       assertTrue(userDAO.addUser(user));
       assertTrue(subjectDAO.addSubject(subject));

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

       postDAO.clearPosts();
       userDAO.clearUsers();
       subjectDAO.clearSubjects();
   }

   public void testGetPostByUserAndGetPost() throws SQLException {
       Post post1 = new Post("akali", "asasinoba", "teach", "perfect execution");

       User user1 = new User("akali", "1", "shuriken", "flip", "execute");
       Subject subject1 = new Subject("asasinoba");

       assertTrue(userDAO.addUser(user1));
       assertTrue(subjectDAO.addSubject(subject1));

       Post post2 = new Post("ezreal", "qurdoba", "teach", "mystic");

       User user2 = new User("ezreal", "1", "shuriken", "flip", "mystic");
       Subject subject2 = new Subject("qurdoba");

       assertTrue(userDAO.addUser(user2));
       assertTrue(subjectDAO.addSubject(subject2));

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

       postDAO.clearPosts();
       userDAO.clearUsers();
       subjectDAO.clearSubjects();
   }
}