package Model;

import junit.framework.TestCase;

public class SubjectTest extends TestCase {
    public void testSubject(){
        Subject math = new Subject("Math");

        Subject physics = new Subject("Physics");

        Subject paradigms = new Subject("Paradigms");


        assertEquals("Math", math.getName());
        assertEquals("Physics", physics.getName());
        assertEquals("Paradigms", paradigms.getName());

        assertTrue(math.equals(math));
        assertFalse(math.equals(physics));
    }
}