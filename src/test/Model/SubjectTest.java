package Model;

import junit.framework.TestCase;

public class SubjectTest extends TestCase {
    public void testSubject(){
        Subject math = new Subject("Math");
        math.setId(1);
        Subject physics = new Subject("Physics");
        physics.setId(2);
        Subject paradigms = new Subject("Paradigms");
        paradigms.setId(3);

        assertEquals(1, math.getId());
        assertEquals(2, physics.getId());
        assertEquals(3, paradigms.getId());

        assertEquals("Math", math.getName());
        assertEquals("Physics", physics.getName());
        assertEquals("Paradigms", paradigms.getName());

        paradigms.setId(1);
        assertTrue(math.equals(paradigms));
        assertFalse(math.equals(physics));
    }
}