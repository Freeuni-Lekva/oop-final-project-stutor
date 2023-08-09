import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class StutorDBTest {
    private StutorDB stutorDB;

    @Before
    public void setUp() {
        stutorDB = new StutorDB();
    }


    @Test
    public void ratingTest(){
        stutorDB.removeAllUsers();
        Account ac1 = new StudentAccount("ac1", "aaa", "name", "lastname",1);
        Account ac2 = new StudentAccount("ac2", "bbb", "name", "lastname",1);
        Account ac3 = new StudentAccount("ac3", "ccc", "name", "lastname",1);
        Account ac4 = new StudentAccount("ac4", "ddd", "name", "lastname",1);

        stutorDB.addAccount(ac1);
        stutorDB.addAccount(ac2);
        stutorDB.addAccount(ac3);
        stutorDB.addAccount(ac4);

        stutorDB.addRating(stutorDB.getAccount("ac2").getUserId(), stutorDB.getAccount("ac1").getUserId(), 5);
        stutorDB.addRating(stutorDB.getAccount("ac3").getUserId(), stutorDB.getAccount("ac1").getUserId(), 5);
        stutorDB.addRating(stutorDB.getAccount("ac4").getUserId(), stutorDB.getAccount("ac1").getUserId(), 4);

        stutorDB.addRating(stutorDB.getAccount("ac3").getUserId(), stutorDB.getAccount("ac2").getUserId(), 1);
        stutorDB.addRating(stutorDB.getAccount("ac4").getUserId(), stutorDB.getAccount("ac2").getUserId(), 4);

        stutorDB.addRating(stutorDB.getAccount("ac4").getUserId(), stutorDB.getAccount("ac3").getUserId(), 3);

        assertEquals(stutorDB.getRating(stutorDB.getAccount("ac1").getUserId()), (double) 14 / 3, 0);
        assertEquals(stutorDB.getRating(stutorDB.getAccount("ac2").getUserId()), (double) 5 / 2, 0);
        assertEquals(stutorDB.getRating(stutorDB.getAccount("ac3").getUserId()), (double) 3, 0);
        assertEquals(stutorDB.getRating(stutorDB.getAccount("ac4").getUserId()), (double) 0, 0);

        stutorDB.removeAllUsers();

    }



    @Test
    public void removeFriendTest() {
        stutorDB.removeAllUsers();
        Account ac1 = new StudentAccount("ac1", "aaa", "name", "lastname",1);
        Account ac2 = new StudentAccount("ac2", "bbb", "name", "lastname",1);
        Account ac3 = new StudentAccount("ac3", "ccc", "name", "lastname",1);
        Account ac4 = new StudentAccount("ac4", "ddd", "name", "lastname",1);

        stutorDB.addAccount(ac1);
        stutorDB.addAccount(ac2);
        stutorDB.addAccount(ac3);
        stutorDB.addAccount(ac4);

        stutorDB.addFriend(stutorDB.getAccount("ac1").getUserId(), stutorDB.getAccount("ac2").getUserId());
        stutorDB.addFriend(stutorDB.getAccount("ac1").getUserId(), stutorDB.getAccount("ac3").getUserId());
        stutorDB.addFriend(stutorDB.getAccount("ac1").getUserId(), stutorDB.getAccount("ac4").getUserId());

        stutorDB.addFriend(stutorDB.getAccount("ac2").getUserId(), stutorDB.getAccount("ac4").getUserId());

        List<Account> list = stutorDB.getFriends(stutorDB.getAccount("ac1").getUserId());
        assertEquals(list.size(), 3);

        list = stutorDB.getFriends(stutorDB.getAccount("ac2").getUserId());
        assertEquals(list.size(), 2);

        stutorDB.removeFriend(stutorDB.getAccount("ac1").getUserId(), stutorDB.getAccount("ac2").getUserId());
        list = stutorDB.getFriends(stutorDB.getAccount("ac1").getUserId());
        assertEquals(list.size(), 2);

        assertEquals(list.get(0).getUsername(), "ac3");
        assertEquals(list.get(1).getUsername(), "ac4");

        list = stutorDB.getFriends(stutorDB.getAccount("ac2").getUserId());
        assertEquals(list.size(), 1);

        assertEquals(list.get(0).getUsername(), "ac4");

        stutorDB.removeAllUsers();

    }


    @Test
    public void addGetFriends() {
        stutorDB.removeAllUsers();
        Account ac1 = new StudentAccount("ac1", "aaa", "name", "lastname",1);
        Account ac2 = new StudentAccount("ac2", "bbb", "name", "lastname",1);
        Account ac3 = new StudentAccount("ac3", "ccc", "name", "lastname",1);

        stutorDB.addAccount(ac1);
        stutorDB.addAccount(ac2);
        stutorDB.addAccount(ac3);

        stutorDB.addFriend(stutorDB.getAccount("ac1").getUserId(), stutorDB.getAccount("ac2").getUserId());
        stutorDB.addFriend(stutorDB.getAccount("ac1").getUserId(), stutorDB.getAccount("ac3").getUserId());

        List<Account> list = stutorDB.getFriends(stutorDB.getAccount("ac1").getUserId());
        assertEquals(list.size(), 2);
        assertEquals(list.get(0).getUsername(), "ac2");
        assertEquals(list.get(1).getUsername(), "ac3");

        list = stutorDB.getFriends(stutorDB.getAccount("ac2").getUserId());
        assertEquals(list.size(), 1);

        stutorDB.removeAllUsers();

    }



    // This test is to check if connections are released properly. (It looks useless but believe me it's not)
    @Test
    public void additionalTest() {
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
    public void removeAllTest() {
        stutorDB.removeAllUsers();
        Account ac1 = new StudentAccount("ac1", "aaa", "name", "lastname",1);
        Account ac2 = new StudentAccount("ac2", "bbb", "name", "lastname",1);
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
    public void removeTest() {
        stutorDB.removeAllUsers();
        Account ac1 = new StudentAccount("ac1", "aaa", "name", "lastname",1);
        Account ac2 = new StudentAccount("ac2", "bbb", "name", "lastname",1);
        stutorDB.addAccount(ac1);
        stutorDB.addAccount(ac2);

        ArrayList<Account> accounts = stutorDB.searchUsernameLike("");
        assertEquals(accounts.size(), 2);

        stutorDB.removeAccount("ac2");
        accounts = stutorDB.searchUsernameLike("");
        assertEquals(accounts.size(), 1);
        assertEquals(accounts.get(0).getUsername(), ac1.getUsername());
        stutorDB.removeAllUsers();

    }

    @Test
    public void searchTest() {
        stutorDB.removeAllUsers();
        Account ac1 = new StudentAccount("abc", "aaa", "name", "lastname",1);
        Account ac2 = new StudentAccount("cde", "bbb", "name", "lastname",1);
        stutorDB.addAccount(ac1);
        stutorDB.addAccount(ac2);

        ArrayList<Account> accounts = stutorDB.searchUsernameLike("c");

        assertEquals(accounts.size(), 2);
        accounts = stutorDB.searchUsernameLike("b");
        assertEquals(accounts.size(), 1);
        accounts = stutorDB.searchUsernameLike("a");
        assertEquals(accounts.size(), 1);

        Account ac3 = new StudentAccount("bdf", "ccc", "name", "lastname",1);
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
    public void passwordChangeTest() {
        stutorDB.removeAllUsers();
        Account ac1 = new StudentAccount("abc", "aaa", "name", "lastname",1);
        Account ac2 = new StudentAccount("cde", "bbb", "name", "lastname",1);
        Account ac3 = new StudentAccount("fgh", "ccc", "name", "lastname",1);

        stutorDB.addAccount(ac1);
        stutorDB.addAccount(ac2);
        stutorDB.addAccount(ac3);

//        stutorDB.changePassword("abc", "ccc");
//        stutorDB.changePassword("cde", "aaa");
//        stutorDB.changePassword("fgh", "bbb");
        stutorDB.updateUser("abc", "password", "ccc");
        stutorDB.updateUser("cde", "password", "aaa");
        stutorDB.updateUser("fgh", "password", "bbb");

        assertEquals(stutorDB.searchUsernameLike("abc").get(0).getUserPassword(), "ccc");
        assertEquals(stutorDB.searchUsernameLike("cde").get(0).getUserPassword(), "aaa");
        assertEquals(stutorDB.searchUsernameLike("fgh").get(0).getUserPassword(), "bbb");
        stutorDB.removeAllUsers();
    }



    @Test
    public void addTest() {
        stutorDB.removeAllUsers();
        Account ac1 = new StudentAccount("ac1", "aaa", "name", "lastname",1);
        Account ac2 = new StudentAccount("ac2", "bbb", "name", "lastname",1);
        stutorDB.addAccount(ac1);
        stutorDB.addAccount(ac2);
        ArrayList<Account> accounts = stutorDB.searchUsernameLike("");
        assertEquals(accounts.size(), 2);
        assertEquals(accounts.get(0).getUsername(), ac1.getUsername());
        assertEquals(accounts.get(0).getUserPassword(), ac1.getUserPassword());
        assertEquals(accounts.get(0).getUserRoles(), ac1.getUserRoles());
        assertEquals(accounts.get(1).getUsername(), ac2.getUsername());
        assertEquals(accounts.get(1).getUserPassword(), ac2.getUserPassword());
        assertEquals(accounts.get(1).getUserRoles(), ac2.getUserRoles());
        stutorDB.removeAllUsers();
    }
}