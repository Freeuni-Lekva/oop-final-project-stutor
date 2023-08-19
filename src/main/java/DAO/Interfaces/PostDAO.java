package DAO.Interfaces;

import Model.Post;

import java.sql.SQLException;
import java.util.List;

public interface PostDAO {

    /**
     * adds the post into the database.
     * @param post to be added.
     * @return true if successful.
     */
    boolean addPost(Post post) throws SQLException;

    /**
     * removes the post from the database.
     * @param post_id to be removed.
     * @return true if successful.
     */
    boolean removePost(int post_id) throws SQLException;

    /**
     * gets the post by id from the database.
     * @param post_id of the post.
     * @return post object if successful.
     */
    Post getPostById(int post_id) throws SQLException;

    /**
     * gets the posts of the given user from the database.
     * @param user_id of the given user.
     * @return list of posts if successful.
     */
    List<Post> getPostByUser(int user_id) throws SQLException;


    /**
     * gets all the posts from the database.
     * @return List of all posts.
     */
    List<Post> getAllPosts() throws SQLException;

    /**
     * clears all the posts from the database.
     */
    void clearPosts() throws SQLException;
}
