package DAO.Interfaces;

import Model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {
    /**
     * Adds the user into the database and returns true if the operation was successful.
     * @param user to be added
     * @return false if some kind of error occurred or user could not be added. returns true otherwise.
     */
    public boolean addUser(User user) throws SQLException;

    /**
     * removes the user from the database and returns true if the operation was successful.
     * @param username to be removed
     * @return false if some kind of error occurred or user could not be found. returns true otherwise.
     */
    public boolean removeUser(String username) throws SQLException;

    /**
     * searches the user by his/her email.
     * @param email of the user
     * @return null if some kind of error occurred or user could not be found. returns User otherwise.
     */
    public User getUserByEmail(String email) throws SQLException;

    /**
     * searches the user by his/her username.
     * @param username to be added
     * @return null if some kind of error occurred or user could not be found. returns User otherwise.
     */
    public User getUserByUsername(String username) throws SQLException;

    /**
     * checks if the users email and password is correct and return true if it is.
     * @param email to be added
     * @return false if some kind of error occurred or user could not be found. returns true otherwise.
     */
    public boolean isValidUser(String email, String password) throws SQLException;


    /**
     * searches users which have same prefix of the email
     * @param prefix prefix of the desired one
     * @return list of all the users;
     */
    public List<User> searchUsersByUsername(String prefix) throws SQLException;

    /**
     * deletes every user in the table.
     */
    public void clearUsers() throws SQLException;

    /**
     * get all users in the database
     * @return list of All users in database;
     */
    public List<User> getAllUsers() throws SQLException;
}
