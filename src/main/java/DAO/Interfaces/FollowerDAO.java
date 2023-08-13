package DAO.Interfaces;

import Model.User;

import java.sql.SQLException;
import java.util.List;

public interface FollowerDAO {
    /**
     * Adds the follower into the database and returns true if the operation was successful.
     * @param user_id id of the user.
     * @param follower_id id of the follower.
     * @return false if some kind of error occurred. returns true otherwise.
     */
    public boolean addFollower(int user_id, int follower_id) throws SQLException;

    /**
     * removes the follower from the database and returns true if the operation was successful.
     * @param user_id to be removed.
     * @param follower_id to be removed.
     * @return false if some kind of error occurred. returns true otherwise.
     */
    public boolean removeFollower(int user_id, int follower_id) throws SQLException;

    /**
     * gets all the followers from the database of the given user.
     * @param user_id to be removed.
     * @return List of followers for the given user.
     */
    public List<User> getFollowers(int user_id) throws SQLException;

}