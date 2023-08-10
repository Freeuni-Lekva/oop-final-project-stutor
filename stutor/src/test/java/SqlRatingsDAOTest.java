import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SqlRatingsDAOTest {

    private SqlRatingsDAO rDao;
    private SqlAccountsDAO acDao;

    @Before
    public void setUp() throws Exception {
        rDao = new SqlRatingsDAO();
        acDao = new SqlAccountsDAO();
    }


    @Test
    public void ratingTest(){
        acDao.removeAllUsers();
        Account ac1 = new StudentAccount("ac1", "aaa", "name", "lastname",1);
        Account ac2 = new StudentAccount("ac2", "bbb", "name", "lastname",1);
        Account ac3 = new StudentAccount("ac3", "ccc", "name", "lastname",1);
        Account ac4 = new StudentAccount("ac4", "ddd", "name", "lastname",1);

        acDao.addAccount(ac1);
        acDao.addAccount(ac2);
        acDao.addAccount(ac3);
        acDao.addAccount(ac4);

        rDao.addRating(acDao.getAccount("ac2").getUserId(), acDao.getAccount("ac1").getUserId(), 5);
        rDao.addRating(acDao.getAccount("ac3").getUserId(), acDao.getAccount("ac1").getUserId(), 5);
        rDao.addRating(acDao.getAccount("ac4").getUserId(), acDao.getAccount("ac1").getUserId(), 4);

        rDao.addRating(acDao.getAccount("ac3").getUserId(), acDao.getAccount("ac2").getUserId(), 1);
        rDao.addRating(acDao.getAccount("ac4").getUserId(), acDao.getAccount("ac2").getUserId(), 4);

        rDao.addRating(acDao.getAccount("ac4").getUserId(), acDao.getAccount("ac3").getUserId(), 3);

        assertEquals(rDao.getRating(acDao.getAccount("ac1").getUserId()), (double) 14 / 3, 0);
        assertEquals(rDao.getRating(acDao.getAccount("ac2").getUserId()), (double) 5 / 2, 0);
        assertEquals(rDao.getRating(acDao.getAccount("ac3").getUserId()), (double) 3, 0);
        assertEquals(rDao.getRating(acDao.getAccount("ac4").getUserId()), (double) 0, 0);

        acDao.removeAllUsers();

    }
}