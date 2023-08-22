package DAO;

import DAO.Interfaces.SubjectDAO;
import Model.Subject;
import junit.framework.TestCase;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import java.sql.SQLException;
import java.util.List;

public class SqlSubjectDAOTest extends TestCase {
    private static SubjectDAO subjectDAO;
    private ConnectionPool connectionPool;

    @BeforeAll
    public void setUp() throws ClassNotFoundException, SQLException {
        subjectDAO = new SqlSubjectDAO();
        connectionPool = new ConnectionPool();
        subjectDAO.clearSubjects();
    }

    @AfterEach
    public void clearUp() throws SQLException {
        subjectDAO.clearSubjects();
    }

    @AfterAll
    public static void clearUpEverything() throws SQLException {
        subjectDAO.clearSubjects();
    }

    public void testAddAndRemove() throws SQLException {
        Subject subject = new Subject("Math");

        assertTrue(subjectDAO.addSubject(subject));
        assertFalse(subjectDAO.addSubject(subject)); // Adding the same subject should fail

        assertTrue(subjectDAO.removeSubject(subject.getName()));
        assertFalse(subjectDAO.removeSubject(subject.getName())); // Removing a non-existent subject should fail
    }

    public void testGetSubjectByName() throws SQLException {
        Subject subject = new Subject("Physics");

        assertTrue(subjectDAO.addSubject(subject));

        Subject retrievedSubject = subjectDAO.getSubjectByName(subject.getName());
        assertNotNull(retrievedSubject);
        assertEquals(subject.getName(), retrievedSubject.getName());
    }

    public void testGetAllSubjects() throws SQLException {
        Subject subject1 = new Subject("Biology");
        Subject subject2 = new Subject("History");

        assertTrue(subjectDAO.addSubject(subject1));
        assertTrue(subjectDAO.addSubject(subject2));

        List<Subject> subjects = subjectDAO.getAllSubjects();
        assertEquals(2, subjects.size());

        assertTrue(subjects.contains(subject1));
        assertTrue(subjects.contains(subject2));
    }
}