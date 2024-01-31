package fr.iut.metier;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;
/**
 *
  * @author  Colette Johnen
*/
public class MemberTest {
    /** */
    @Test
    public void testGetName() {
        String st = new String("Colette");
        Member m = new Member(st);
        assertEquals(st, m.getName());
        String stB = new String("Eric");
        m = new Member(stB);
        assertNotEquals(st, m.getName());
    }

}
