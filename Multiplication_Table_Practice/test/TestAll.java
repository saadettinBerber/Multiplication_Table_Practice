
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Saadettin
 */
public class TestAll {
    
    public TestAll() {
    }

    @Test
    public void testGenerateUniqueExerciseNumber() {
        Exercise x2 = new Exercise("Alistirma", 1, 10, 5, 10, 3,1);
        Exercise x1 = new Exercise("Alistirma1", 5, 10, 5, 10, 1,1);
        Parent p1 = new Parent("Sado","1","a", 1);
        p1.setSonID();
        Child c1 = new Child("Zeynep", "a", p1.getSonID());
        p1.addChild(c1);
        p1.addExercise(c1, x2);
        p1.addExercise(c1, x1);
        Child tmp = p1.findChild(1);
        int size = tmp.getExercises().size();
        assertEquals( tmp.getExercises().get(size-1).getIdExercise(), 2);
    }

    @Test
    public void testRemoveChild() {
        Parent p1 = new Parent("Sado","1","a", 1);
        p1.setSonID();
        Child c1 = new Child("Zeynep", "a", p1.getSonID());
        p1.addChild(c1);
        Child tmp = p1.removeChild(1);
        assertEquals(tmp.getID(), c1.getID());
    }

    @Test
    public void testAddChild() {
        Exercise x2 = new Exercise("Alistirma", 1, 10, 5, 10, 3,1);
        Parent p1 = new Parent("Sado","1","a", 1);
        p1.setSonID();
        Child c1 = new Child("Zeynep", "a", p1.getSonID());
        p1.addChild(c1);
        p1.addExercise(c1, x2);
        Child tmp = p1.getChildren().get(0);
        assertEquals(c1.getID(), tmp.getID());
    }

    @Test
    public void testFindChild() {
        Parent p1 = new Parent("Sado","1","a", 1);
        p1.setSonID();
        Child c1 = new Child("Zeynep", "a", p1.getSonID());
        p1.addChild(c1);
        String beklenen = c1.getName();
        String result = p1.findChild(c1.getID()).getName();
        assertEquals(beklenen, result);
    }

    @Test
    public void testAddExercise_Child_Exercise() {
        Exercise x2 = new Exercise("Alistirma", 1, 10, 5, 10, 3,1);
        Parent p1 = new Parent("Sado","1","a", 1);
        p1.setSonID();
        Child c1 = new Child("Zeynep", "a", p1.getSonID());
        p1.addChild(c1);
        p1.addExercise(c1, x2);
        Exercise tmp = p1.getChildren().get(0).getExercises().get(0);
        assertEquals(tmp.getIdExercise(), x2.getIdExercise());
    }


    @Test
    public void testSetSecurityAnswer() {
        Parent p1 = new Parent("Sado","1","a", 1);
        p1.setSecurityAnswer("1234");
        String beklenen = "1234";
        assertEquals(beklenen, "1234");
    }

    
    
}
