package DAO.Interfaces;

import Model.User;

import java.sql.SQLException;
import java.util.List;

public interface FollowerDAO {
    /**
     * Adds the follower into the database and returns true if the operation was successful.
     * @param following username of the user.
     * @param follower username of the follower.
     * @return false if some kind of error occurred. returns true otherwise.
     */
    public boolean addFollower(String following, String follower) throws SQLException;

    /**
     * removes the follower from the database and returns true if the operation was successful.
     * @param following to be removed.
     * @param follower to be removed.
     * @return false if some kind of error occurred. returns true otherwise.
     */
    public boolean removeFollower(String following, String follower) throws SQLException;

    /**
     * gets all the followers from the database of the given user.
     * @param user to be removed.
     * @return List of followers for the given user.
     */
    public List<User> getFollowers(String user) throws SQLException;

    /**
     * gets all the followers from the database of the given user.
     * @param user username of the followings one.
     * @return List of followers for the given user.
     */
    public List<User> getFollowings(String user) throws SQLException;

    /**
     * this method is telling us if follower follows the following
     * @param follower username of the follower
     * @param following username of the following
     * @return false if some kind of error occurred or follower doesn't follows following. returns true otherwise.
     */
    public boolean isFollowing(String follower, String following) throws SQLException;

}