import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class SqlAccountsDAOTest {
    private SqlAccountsDAO dao;

    @Before
    public void setUp() throws Exception {
        dao = new SqlAccountsDAO();
    }

    @Test
    public void addTest() {
        dao.removeAllUsers();
        Account ac1 = new StudentAccount("ac1", "aaa", "name", "lastname",1);
        Account ac2 = new StudentAccount("ac2", "bbb", "name", "lastname",1);
        dao.addAccount(ac1);
        dao.addAccount(ac2);
        ArrayList<Account> accounts = dao.searchUsernameLike("");
        assertEquals(accounts.size(), 2);
        assertEquals(accounts.get(0).getUsername(), ac1.getUsername());
        assertEquals(accounts.get(0).getUserPassword(), ac1.getUserPassword());
        assertEquals(accounts.get(0).getUserRoles(), ac1.getUserRoles());
        assertEquals(accounts.get(1).getUsername(), ac2.getUsername());
        assertEquals(accounts.get(1).getUserPassword(), ac2.getUserPassword());
        assertEquals(accounts.get(1).getUserRoles(), ac2.getUserRoles());
        dao.removeAllUsers();
    }


    // This test is to check if connections are released properly. (It looks useless but believe me it's not)
    @Test
    public void additionalTest() {
        dao.removeAllUsers();
        dao.removeAllUsers();
        dao.removeAllUsers();
        dao.removeAllUsers();
        dao.removeAllUsers();
        dao.removeAllUsers();
        dao.removeAllUsers();
        dao.removeAllUsers();
        ArrayList<Account> accounts = dao.searchUsernameLike("");
        assertEquals(accounts.size(), 0);
    }

    @Test
    public void removeAllTest() {
        dao.removeAllUsers();
        Account ac1 = new StudentAccount("ac1", "aaa", "name", "lastname",1);
        Account ac2 = new StudentAccount("ac2", "bbb", "name", "lastname",1);
        dao.addAccount(ac1);
        ArrayList<Account> accounts = dao.searchUsernameLike("");
        assertEquals(accounts.size(), 1);

        dao.removeAllUsers();
        accounts = dao.searchUsernameLike("");
        assertEquals(accounts.size(), 0);

        dao.addAccount(ac1);
        dao.addAccount(ac2);
        accounts = dao.searchUsernameLike("");
        assertEquals(accounts.size(), 2);

        dao.removeAllUsers();
        accounts = dao.searchUsernameLike("");
        assertEquals(accounts.size(), 0);
    }


    @Test
    public void removeTest() {
        dao.removeAllUsers();
        Account ac1 = new StudentAccount("ac1", "aaa", "name", "lastname",1);
        Account ac2 = new StudentAccount("ac2", "bbb", "name", "lastname",1);
        dao.addAccount(ac1);
        dao.addAccount(ac2);

        ArrayList<Account> accounts = dao.searchUsernameLike("");
        assertEquals(accounts.size(), 2);

        dao.removeAccount("ac2");
        accounts = dao.searchUsernameLike("");
        assertEquals(accounts.size(), 1);
        assertEquals(accounts.get(0).getUsername(), ac1.getUsername());
        dao.removeAllUsers();

    }

    @Test
    public void searchTest() {
        dao.removeAllUsers();
        Account ac1 = new StudentAccount("abc", "aaa", "name", "lastname",1);
        Account ac2 = new StudentAccount("cde", "bbb", "name", "lastname",1);
        dao.addAccount(ac1);
        dao.addAccount(ac2);

        ArrayList<Account> accounts = dao.searchUsernameLike("c");

        assertEquals(accounts.size(), 2);
        accounts = dao.searchUsernameLike("b");
        assertEquals(accounts.size(), 1);
        accounts = dao.searchUsernameLike("a");
        assertEquals(accounts.size(), 1);

        Account ac3 = new StudentAccount("bdf", "ccc", "name", "lastname",1);
        dao.addAccount(ac3);

        accounts = dao.searchUsernameLike("b");
        assertEquals(accounts.size(), 2);
        assertEquals(accounts.get(0).getUsername(), ac1.getUsername());
        assertEquals(accounts.get(1).getUsername(), ac3.getUsername());

        accounts = dao.searchUsernameLike("d");
        assertEquals(accounts.size(), 2);
        assertEquals(accounts.get(0).getUsername(), ac2.getUsername());
        assertEquals(accounts.get(1).getUsername(), ac3.getUsername());

        accounts = dao.searchUsernameLike("f");
        assertEquals(accounts.size(), 1);
        dao.removeAllUsers();

    }

    @Test
    public void passwordChangeTest() {
        dao.removeAllUsers();
        Account ac1 = new StudentAccount("abc", "aaa", "name", "lastname",1);
        Account ac2 = new StudentAccount("cde", "bbb", "name", "lastname",1);
        Account ac3 = new StudentAccount("fgh", "ccc", "name", "lastname",1);

        dao.addAccount(ac1);
        dao.addAccount(ac2);
        dao.addAccount(ac3);

//        stutorDB.changePassword("abc", "ccc");
//        stutorDB.changePassword("cde", "aaa");
//        stutorDB.changePassword("fgh", "bbb");
        dao.updateUser("abc", "password", "ccc");
        dao.updateUser("cde", "password", "aaa");
        dao.updateUser("fgh", "password", "bbb");

        assertEquals(dao.searchUsernameLike("abc").get(0).getUserPassword(), "ccc");
        assertEquals(dao.searchUsernameLike("cde").get(0).getUserPassword(), "aaa");
        assertEquals(dao.searchUsernameLike("fgh").get(0).getUserPassword(), "bbb");
        dao.removeAllUsers();
    }
    @Test
    public void getAccountTest() {
        dao.removeAllUsers();
        Account ac1 = new StudentAccount("abc", "aaa", "name1", "lastname1",1);
        Account ac2 = new StudentAccount("cde", "bbb", "name2", "lastname2",2);
        Account ac3 = new StudentAccount("fgh", "ccc", "name3", "lastname3",2);

        dao.addAccount(ac1);
        dao.addAccount(ac2);
        dao.addAccount(ac3);

        Account ac = dao.getAccount("abc");
        assertEquals(ac.getUserPassword(), "aaa");
        assertEquals(ac.getFirstName(), "name1");
        assertEquals(ac.getLastName(), "lastname1");
        assertEquals(ac.getUserRoles(), 1);

        dao.removeAllUsers();

    }
}