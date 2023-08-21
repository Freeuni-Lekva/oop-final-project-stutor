package DAO.Interfaces;

import javax.jws.soap.SOAPBinding;
import java.sql.SQLException;
import java.util.List;

public interface AdminDAO {
    /**
     * this method adds user in the admins list
     * @param username
     * @return false if some kind of error occurred or user could not be added. returns true otherwise.
     */
    public boolean addAdmin(String username) throws SQLException;

    /**
     * this method removes user from the admin list.
     * @param username
     * @return false if some kind of error occurred. returns true otherwise.
     */
    public boolean removeAdmin(String username) throws SQLException;

    /**
     * this method checks if user is an admin or not.
     * @param username
     * @return false if user is not an admin. returns true otherwise.
     */
    public boolean isAdmin(String username) throws SQLException;

    /**
     * this method gets all admins.
     * @return list of the admins.
     */
    public List<String> getAllAdmins() throws SQLException;

    /**
     * this method deletes all the admins from the list.
     */
    public void clearAdmins() throws SQLException;
}
