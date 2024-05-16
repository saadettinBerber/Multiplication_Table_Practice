
import java.util.*;

/**
 *
 * @author Saadettin
 */
public class Parent extends Person{
    private ArrayList<Child> children;     //Parent nesnesinin birden fazla çocuğu olabilir.
    private String securityAnswer;         //Parent nesnesine özel sorunun cevap bilgisini tutuyorum
                                           //Aslında istediği herhangi bir sorunun cevabını kullanıcı girebilir.
    
    public Parent(String name, String password, String securityAnswer, int id){
        super(name, password, id);
        this.securityAnswer = securityAnswer;
        children = new ArrayList<Child>();
    }

    public ArrayList<Child> getChildren() {
        return children;
    }
    
    public Child removeChild(int childId){              //Child nesnesinin ID bilgisi kullanarak siliyoruz. Tabi eğer varsa
        Child child = findChild(childId);
        if(child != null)
            children.remove(child);
        return child;
    }
    
    public boolean addChild(Child child){               //Child ekliyoruz. 
        if(!children.contains(child)){
            child.setParentID(this.getID());    //Child hangi ebeveyene ait onu belirmek için set ettik.
            children.add(child);                     
            return true;
        }
        return false;
    }
    
    public Child findChild(int childID){                //child nesnesinin ID bilgisini alarak buluyor.
        for(Child child : children){
            if(child.getID() == childID)
                return child;
        }
        return null;
    }
    
    public void addExercise(Child child, Exercise exercise){    //child nesnesine exercise nesnesi atama
        child.addExercise(exercise);
    }
    
    public void addExercise(Exercise exercise, int childID){    //Verilen child ID bilgisine exercise nesnesi atama
        Child child = findChild(childID);
        if(child != null)
            child.addExercise(exercise);
    }

    public String getSecurityAnswer() {
        return securityAnswer;
    }

    public void setSecurityAnswer(String securityAnswer){
        this.securityAnswer = securityAnswer;
    }
    
    public void childGuncelle(Child child){                    //child nesnesi misal exercise yaptı guncelleme geldi yani. Onu eklemek gerek.
        int i = 0;
        for(Child child2 : children){
            if(child2.getID() == child.getID()){
                children.set(i, child);
                return;
            }
            i++;
        }
    }
    
    
    
}
