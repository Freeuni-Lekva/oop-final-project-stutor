package DAO.Interfaces;

import Model.Subject;

import java.sql.SQLException;
import java.util.List;

public interface SubjectDAO {

    /**
     * adds the subject into the database.
     * @param subject to be added.
     * @return true if successful.
     */
    boolean addSubject(Subject subject) throws SQLException;

    /**
     * removes the subject from the database.
     * @param subjectName to be removed.
     * @return true if successful.
     */
    boolean removeSubject(String subjectName) throws SQLException;

    /**
     * gets the subject by name from the database.
     * @param subjectName of the subject.
     * @return Subject object if successful.
     */
    Subject getSubjectByName(String subjectName) throws SQLException;


    /**
     * gets all the subjects from the database.
     * @return List of all Subjects.
     */
    List<Subject> getAllSubjects() throws SQLException;

    /**
     * clears all the subjects from the database.
     */
    void clearSubjects() throws SQLException;

    /**
     * adds new learning subject for the given user into the database.
     * @param user_id learning user.
     * @param sub_id subject id.
     * @return true if successful.
     */
}