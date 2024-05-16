
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Saadettin
 */
public class Main {
    public static void main(String[] args){
        
        FileTransaction file = null;
        try {
            file = new FileTransaction();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        Exercise x1 = new Exercise("Alistirma1", 5, 10, 5, 10, 1,1);
        Exercise x2 = new Exercise("Alistirma", 1, 10, 5, 10, 3,1);
        Parent p1 = new Parent("Sado","1","a", 1);
        p1.setSonID();
        Child c1 = new Child("Zeynep", "a", p1.getSonID());
        p1.addChild(c1);
        p1.setSonID();
        Child c2 = new Child("Yunus", "123", p1.getSonID());
        p1.addChild(c2);
        p1.addExercise(c1, x1);
        p1.addExercise(c2, x2);
        file.start(p1);
    

    }
}
