package Model;

import junit.framework.TestCase;
import org.junit.jupiter.api.BeforeAll;

import java.util.ArrayList;
import java.util.List;

public class PostTest extends TestCase {
    private List<Post> posts;
    private final static int NUM_POSTS = 10;
    @BeforeAll
    public void setUp(){
        posts = new ArrayList<>();
        for(int i = 0; i < NUM_POSTS; i++){
            Post post = new Post(Integer.toString(i), Integer.toString(i), "learn", Integer.toString(i));
            post.setId(i);
            posts.add(post);
        }
    }

    public void testUser(){
        for(int i = 0; i < NUM_POSTS; i++){
            Post post = new Post(Integer.toString(i), Integer.toString(i), "learn", Integer.toString(i));
            post.setId(i);

            assertEquals(post.getUsername(), posts.get(i).getUsername());
            assertEquals(post.getSubject(), posts.get(i).getSubject());
            assertEquals(post.getType().toString(), posts.get(i).getType().toString());
            assertEquals(post.getText(), posts.get(i).getText());
            assertEquals(post.getId(), posts.get(i).getId());

            assertEquals(post, posts.get(i));

            if(i > 0) assertTrue(post.compareTo(posts.get(i - 1)) < 0);
        }
    }
}