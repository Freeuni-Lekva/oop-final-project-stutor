package DAO.Interfaces;

import java.sql.SQLException;

public interface RatingDAO {
    /**
     * Adds the rating into the database and returns true if the operation was successful.
     * @param user_id id of the user.
     * @param rated_id id of the rated user.
     * @return false if some kind of error occurred. returns true otherwise.
     */
    public boolean addRating(int user_id, int rated_id, int rating) throws SQLException;

    /**
     * gets the rating of given user from the database.
     * @param user_id id of the user.
     * @return average rating from 1 to 5.
     */
    public double getRating(int user_id) throws SQLException;

}
