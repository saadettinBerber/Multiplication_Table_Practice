
import java.util.*;

/**
 *
 * @author Saadettin
 */
public class Child extends Person{
    
    private ArrayList<Exercise> exercises;                                  //parent nesnesi exercise tanimladigi zaman buraya ekleme yapılıyor.
    private HashMap<Integer, TreeSet<Exercise>> scores;                     //Buradai HasMap yapisindaki Integer execise ID degeri. TreeSet yapisinda ise belli bir sirada tutuyor. Yani siraliyor.
                                                                            //child nesnesi exercise yaptikca dogru soru sayisi daha fazla ise
    private int parentID;//parenti hangisi onu belli ediyor.                //yuksek skor ama dogru sayilar esit ise hangi egzersiz daha az zamanda cozulmusse ona gore siraliyor.
    
    public Child(String name, String password, int id) {                    //Constructor
        super(name, password,id);
        this.exercises = new ArrayList<Exercise>();
        this.scores = new HashMap<Integer, TreeSet<Exercise>>();
    }
    
    public void addExercise(Exercise exercise){                             //parent nesnesi exercise atamasi yaparken child nesnesinden 
        this.setSonID();                                                    //bu metodu kullanarak exercise atamasi yapacak
        exercise.setIdExercise(this.getSonID());
        scores.put(exercise.getIdExercise(), new TreeSet<Exercise>() );
        exercises.add(exercise);
    }
    
    public void addExercise(String name, int a1, int a2, int b1, int b2, int N){    //Yukaridaki metodun farkli inputlu versiyonu
        this.setSonID();
        Exercise exercise = new Exercise(name, a1, a2, b1, b2, N, this.getSonID());
        scores.put(exercise.getIdExercise(), new TreeSet<Exercise>());
        exercises.add(exercise);
    }
    
    public void addDoneExercise(Exercise exercise,int exerciseID){    //child nesnesi exercise yaptıkca scores HashMap yapisina ekliyor.
        scores.get(exerciseID).add(exercise);                   //Misal ID bilgisi 3 olan exercisi yapti ve yapilan dogru yanlis sayilari bu scores HashMap yapisina ekliyor.
    }
    
    public void printExercises(){                               //exercises ArrayList icindeki bilgileri konsola yazdiriyor.
        int i=0;
        for(Exercise ex : exercises){
            i++;
            System.out.println(i+". alistirma");
            ex.printExercise();
        }
    }
    
    public void printExercise(int exerciseNumber){              //exercises ArrayList icindeki  secili exercise bilgisini konsola yazdiriyor.
        if(exercises.size() < exerciseNumber)
            return;
        
        Exercise ex = exercises.get(exerciseNumber);
        System.out.println(exerciseNumber + ". alistirma ");
        ex.printExercise();
    }

    
    //Asagidaki kodlar Getter ve Setter metotlari
    
    public ArrayList<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(ArrayList<Exercise> exercises) {
        this.exercises = exercises;
    }


    public HashMap<Integer, TreeSet<Exercise>> getScores() {
        return scores;
    }

    public void setScores(HashMap<Integer, TreeSet<Exercise>> scores) {
        this.scores = scores;
    }
    
    public Exercise findExercise(int exerciseID){                   //exercises ArrayList icinde secili exercise seciyor
        for(Exercise ex : exercises){
            if(ex.getIdExercise() == exerciseID ){
                return ex;
            }
        }
        return null;
    }
    
    public TreeSet<Exercise> getTreeSetExercise(int idExercise){
        return scores.get(idExercise);
}

    public int getParentID() {
        return parentID;
    }

    public void setParentID(int parentID) {
        this.parentID = parentID;
    }

    
    
    
    
    
}
