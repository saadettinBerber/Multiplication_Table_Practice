
import java.io.Serializable;

/**
 *
 * @author Saadettin
 */
public class Person implements Serializable{
    private String name;      //Kullanıcının ismi
    private String password;  //Şifresi  
    private int ID;           //Child ya da parent fark etmeksizin kendilerine özgü ID bilgileri var  
    private int sonID;        //Eğer child ise nesne son Exercise ID bilgisini tutuyor. Eşsiz exercise IDsi bulsun
                              //Eğer parent ise nesne son child ID bilgisini tutuyor. Eşsiz child IDsi üretebilsin diye.
    
    
    
    //Asagida constructor, getter, setter metotlar var.
    public Person(String name, String password) {
        this.name = name;
        this.password = password;
        this.sonID = 0;
    }

    public Person(String name, String password, int ID) {
        this.name = name;
        this.password = password;
        this.ID = ID;
        this.sonID = 0;
    }

    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getSonID() {
        return sonID;
    }

    public void setSonID() {
        this.sonID++;
    }

    @Override
    public String toString() {
        return "Person{" + "name=" + name + ", ID=" + ID + '}';
    }
    
    
    
}
