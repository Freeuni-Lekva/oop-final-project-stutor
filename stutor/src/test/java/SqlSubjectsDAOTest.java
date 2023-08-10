import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class SqlSubjectsDAOTest {

    private SqlSubjectsDAO sDao;
    private SqlAccountsDAO acDao;
    @Before
    public void setUp() throws Exception {
        sDao = new SqlSubjectsDAO();
        acDao = new SqlAccountsDAO();
    }

    @Test
    public void subjectTest(){
        acDao.removeAllUsers();
        Account ac1 = new StudentAccount("ac1", "aaa", "name", "lastname",1);

        acDao.addAccount(ac1);

        sDao.addSubject(acDao.getAccount("ac1").getUserId(), "MATH");
        sDao.addSubject(acDao.getAccount("ac1").getUserId(), "SCIE");
        sDao.addSubject(acDao.getAccount("ac1").getUserId(), "ENGL");

        List<String> subjects = sDao.getSubjects(acDao.getAccount("ac1").getUserId());
        assertEquals(subjects.size(), 3);
        assertEquals(subjects.get(0), "Mathematics");
        assertEquals(subjects.get(1), "Science");
        assertEquals(subjects.get(2), "English Language");

        sDao.removeSubject(acDao.getAccount("ac1").getUserId(), "MATH");
        subjects = sDao.getSubjects(acDao.getAccount("ac1").getUserId());
        assertEquals(subjects.size(), 2);
        assertEquals(subjects.get(0), "Science");
        assertEquals(subjects.get(1), "English Language");

        acDao.removeAllUsers();
    }

    @Test
    public void allSubjectsTest() {
        List<String> list = sDao.getAllSubjectIds();
        for (String str: list) {
            System.out.println(str);
        }

        assertEquals(list.get(0), "MATH - Mathematics");
        assertEquals(list.get(1), "SCIE - Science");
        assertEquals(list.get(2), "ENGL - English Language");
        assertEquals(list.get(3), "PHYS - Physics");
        assertEquals(list.get(4), "CHEM - Chemistry");
        assertEquals(list.size(), 20);
    }
}