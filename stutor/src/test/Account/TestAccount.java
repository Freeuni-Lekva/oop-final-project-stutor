import junit.framework.TestCase;
import org.junit.jupiter.api.BeforeAll;

public class TestAccount extends TestCase {
    private Account t;
    private Account s;
    @BeforeAll
    public void setUp(){
        t = new TutorAccount("womanSlayer123", "12345678", "chumber", "kukava", 3, 1);
        s = new StudentAccount("womanSlayer123", "12345678", "chumber", "kukava", 3, 1);
    }

    //this method tests StudentAccounts getter functions
    public void testStudentGetInfo(){
        assertEquals("womanSlayer123", s.getUsername());
        assertEquals("12345678", s.getUserPassword());
        assertEquals("chumber", s.getFirstName());
        assertEquals("kukava", s.getLastName());
        assertEquals(3, s.getUserRoles());
        assertEquals(1, s.getUserId());
    }

    //this method tests TutorAccounts getter functions
    public void testTutorGetInfo(){
        assertEquals("womanSlayer123", t.getUsername());
        assertEquals("12345678", t.getUserPassword());
        assertEquals("chumber", t.getFirstName());
        assertEquals("kukava", t.getLastName());
        assertEquals(3, t.getUserRoles());
        assertEquals(1, t.getUserId());
    }

    //this method tests StudentAccounts setter functions
    public void testStudentSetInfo(){
        s.setUsername("coolkid69");
        assertEquals("coolkid69", s.getUsername());
        s.setPassword("AkameGaKill");
        assertEquals("AkameGaKill", s.getUserPassword());
        s.setFirstName("neimari");
        assertEquals("neimari", s.getFirstName());
        s.setLastName("Messi");
        assertEquals("Messi", s.getLastName());
        s.setUserRoles(2);
        assertEquals(2, s.getUserRoles());
    }

    //this method tests TutorAccounts setter functions
    public void testTutorSetInfo(){
        t.setUsername("coolkid69");
        assertEquals("coolkid69", t.getUsername());
        t.setPassword("AkameGaKill");
        assertEquals("AkameGaKill", t.getUserPassword());
        t.setFirstName("neimari");
        assertEquals("neimari", t.getFirstName());
        t.setLastName("Messi");
        assertEquals("Messi", t.getLastName());
        t.setUserRoles(2);
        assertEquals(2, t.getUserRoles());
    }
}
