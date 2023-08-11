import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class StutorDBTest {
    private StutorDB stutorDB;

    @Before
    public void setUp() {
        stutorDB = new StutorDB();
    }

    // This test is to check if connections are released properly. (It looks useless but believe me it's not)
    @Test
    public void additionalTest(){
        stutorDB.removeAllUsers();
        stutorDB.removeAllUsers();
        stutorDB.removeAllUsers();
        stutorDB.removeAllUsers();
        stutorDB.removeAllUsers();
        stutorDB.removeAllUsers();
        stutorDB.removeAllUsers();
        stutorDB.removeAllUsers();
        ArrayList<Account> accounts = stutorDB.searchUsernameLike("");
        assertEquals(accounts.size(), 0);
    }

    @Test
    public void removeAllTest(){
        stutorDB.removeAllUsers();
        Account ac1 = new StudentAccount("ac1", "aaa", 1);
        Account ac2 = new StudentAccount("ac2", "bbb", 1);
        stutorDB.addAccount(ac1);
        ArrayList<Account> accounts = stutorDB.searchUsernameLike("");
        assertEquals(accounts.size(), 1);

        stutorDB.removeAllUsers();
        accounts = stutorDB.searchUsernameLike("");
        assertEquals(accounts.size(), 0);

        stutorDB.addAccount(ac1);
        stutorDB.addAccount(ac2);
        accounts = stutorDB.searchUsernameLike("");
        assertEquals(accounts.size(), 2);

        stutorDB.removeAllUsers();
        accounts = stutorDB.searchUsernameLike("");
        assertEquals(accounts.size(), 0);
    }

    @Test
    public void searchTest(){
        stutorDB.removeAllUsers();
        Account ac1 = new StudentAccount("abc", "aaa", 1);
        Account ac2 = new StudentAccount("cde", "bbb", 1);
        stutorDB.addAccount(ac1);
        stutorDB.addAccount(ac2);

        ArrayList<Account> accounts = stutorDB.searchUsernameLike("c");

        assertEquals(accounts.size(), 2);
        accounts = stutorDB.searchUsernameLike("b");
        assertEquals(accounts.size(), 1);
        accounts = stutorDB.searchUsernameLike("a");
        assertEquals(accounts.size(), 1);

        Account ac3 = new StudentAccount("bdf", "ccc", 1);
        stutorDB.addAccount(ac3);

        accounts = stutorDB.searchUsernameLike("b");
        assertEquals(accounts.size(), 2);
        assertEquals(accounts.get(0).getUsername(), ac1.getUsername());
        assertEquals(accounts.get(1).getUsername(), ac3.getUsername());

        accounts = stutorDB.searchUsernameLike("d");
        assertEquals(accounts.size(), 2);
        assertEquals(accounts.get(0).getUsername(), ac2.getUsername());
        assertEquals(accounts.get(1).getUsername(), ac3.getUsername());

        accounts = stutorDB.searchUsernameLike("f");
        assertEquals(accounts.size(), 1);
        stutorDB.removeAllUsers();

    }

    @Test
    public void passwordChangeTest(){
        stutorDB.removeAllUsers();
        Account ac1 = new StudentAccount("abc", "aaa", 1);
        Account ac2 = new StudentAccount("cde", "bbb", 1);
        Account ac3 = new StudentAccount("fgh", "ccc", 1);

        stutorDB.addAccount(ac1);
        stutorDB.addAccount(ac2);
        stutorDB.addAccount(ac3);

        stutorDB.changePassword("abc", "ccc");
        stutorDB.changePassword("cde", "aaa");
        stutorDB.changePassword("fgh", "bbb");

        assertEquals(stutorDB.searchUsernameLike("abc").get(0).getPassword(), "ccc");
        assertEquals(stutorDB.searchUsernameLike("cde").get(0).getPassword(), "aaa");
        assertEquals(stutorDB.searchUsernameLike("fgh").get(0).getPassword(), "bbb");
        stutorDB.removeAllUsers();
    }



    @Test
    public void addTest(){
        stutorDB.removeAllUsers();
        Account ac1 = new StudentAccount("ac1", "aaa", 1);
        Account ac2 = new StudentAccount("ac2", "bbb", 1);
        stutorDB.addAccount(ac1);
        stutorDB.addAccount(ac2);
        ArrayList<Account> accounts = stutorDB.searchUsernameLike("");
        assertEquals(accounts.size(), 2);
        assertEquals(accounts.get(0).getUsername(), ac1.getUsername());
        assertEquals(accounts.get(0).getPassword(), ac1.getPassword());
        assertEquals(accounts.get(0).getUserTypes(), ac1.getUserTypes());
        assertEquals(accounts.get(1).getUsername(), ac2.getUsername());
        assertEquals(accounts.get(1).getPassword(), ac2.getPassword());
        assertEquals(accounts.get(1).getUserTypes(), ac2.getUserTypes());
        stutorDB.removeAllUsers();
    }
}