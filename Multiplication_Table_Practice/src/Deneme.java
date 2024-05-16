
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Saadettin
 */
public class Deneme {
    public static void main(String[] args){
     /*   try {
            FileTransaction file = new FileTransaction();
            HashMap<Integer, Person> person = file.dosyaOku();
            Parent parent = (Parent)person.get(1);
            parent.findChild(1).getScores().get(0).first().printExercise();
                
        } catch (IOException ex) {
            Logger.getLogger(Deneme.class.getName()).log(Level.SEVERE, null, ex);
        }*/
     Parent p1 = new Parent("Sado","1","a", 1);
     Child c1 = new Child("Zeynep", "a", p1.getSonID());
     Child c2 = new Child("Yunus", "123", p1.getSonID());
     p1.addChild(c2);
     int index = c2.getID();
     c2.setName("Sado");
     p1.childGuncelle(c2);
     ArrayList<Child> children = p1.getChildren();
        System.out.println(children.get(0));
    }
}
