import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class SqlFriendsDAOTest {

    private SqlFriendsDAO fDao;
    private SqlAccountsDAO acDao;

    @Before
    public void setUp() throws Exception {
        fDao = new SqlFriendsDAO();
        acDao = new SqlAccountsDAO();
    }


    @Test
    public void removeFriendTest() {
        acDao.removeAllUsers();
        Account ac1 = new StudentAccount("ac1", "aaa", "name", "lastname",1);
        Account ac2 = new StudentAccount("ac2", "bbb", "name", "lastname",1);
        Account ac3 = new StudentAccount("ac3", "ccc", "name", "lastname",1);
        Account ac4 = new StudentAccount("ac4", "ddd", "name", "lastname",1);

        acDao.addAccount(ac1);
        acDao.addAccount(ac2);
        acDao.addAccount(ac3);
        acDao.addAccount(ac4);

        fDao.addFriend(acDao.getAccount("ac1").getUserId(), acDao.getAccount("ac2").getUserId());
        fDao.addFriend(acDao.getAccount("ac1").getUserId(), acDao.getAccount("ac3").getUserId());
        fDao.addFriend(acDao.getAccount("ac1").getUserId(), acDao.getAccount("ac4").getUserId());

        fDao.addFriend(acDao.getAccount("ac2").getUserId(), acDao.getAccount("ac4").getUserId());

        List<Account> list = fDao.getFriends(acDao.getAccount("ac1").getUserId());
        assertEquals(list.size(), 3);

        list = fDao.getFriends(acDao.getAccount("ac2").getUserId());
        assertEquals(list.size(), 2);

        fDao.removeFriend(acDao.getAccount("ac1").getUserId(), acDao.getAccount("ac2").getUserId());
        list = fDao.getFriends(acDao.getAccount("ac1").getUserId());
        assertEquals(list.size(), 2);

        assertEquals(list.get(0).getUsername(), "ac3");
        assertEquals(list.get(1).getUsername(), "ac4");

        list = fDao.getFriends(acDao.getAccount("ac2").getUserId());
        assertEquals(list.size(), 1);

        assertEquals(list.get(0).getUsername(), "ac4");

        acDao.removeAllUsers();

    }


    @Test
    public void addGetFriends() {
        acDao.removeAllUsers();
        Account ac1 = new StudentAccount("ac1", "aaa", "name", "lastname",1);
        Account ac2 = new StudentAccount("ac2", "bbb", "name", "lastname",1);
        Account ac3 = new StudentAccount("ac3", "ccc", "name", "lastname",1);

        acDao.addAccount(ac1);
        acDao.addAccount(ac2);
        acDao.addAccount(ac3);

        fDao.addFriend(acDao.getAccount("ac1").getUserId(), acDao.getAccount("ac2").getUserId());
        fDao.addFriend(acDao.getAccount("ac1").getUserId(), acDao.getAccount("ac3").getUserId());

        List<Account> list = fDao.getFriends(acDao.getAccount("ac1").getUserId());
        assertEquals(list.size(), 2);
        assertEquals(list.get(0).getUsername(), "ac2");
        assertEquals(list.get(1).getUsername(), "ac3");

        list = fDao.getFriends(acDao.getAccount("ac2").getUserId());
        assertEquals(list.size(), 1);

        acDao.removeAllUsers();

    }
}