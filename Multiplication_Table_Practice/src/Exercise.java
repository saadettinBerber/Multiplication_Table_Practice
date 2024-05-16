
import java.io.Serializable;
import java.time.*;
import java.util.*;

/**
 *
 * @author Saadettin
 */
public class Exercise implements Serializable, Comparable<Exercise>{
    private String exerciseName;                //exercise ismi
    private int idExercise;                     //essiz exercise ID
    private long start;                         
    private long finish;
    private long duration;                      //child nesnesi exercise nesnesini kac milisaniyede yapmıs onu tutuyorum.
    private ArrayList<Soru> sorular;            //exercise nesnesi soru nesnelerinden olusuyor.
    private int trueNumber;                     //child nesnesi exercise yapinca toplam dogru sayisini tutuyorum
    private int N;                              //exercise nesnesi kaç tane soru nesnesi iceriyor. Onun bilgisi
    private int a1;                             //a ve b aralik degerlerini burada tutuyorum.
    private int a2;                             
    private int b1;
    private int b2;
    private int solved;
    
    public Exercise(String name, int a1, int a2,int b1, int b2, int N, int id){ //ID, a ve b degerlerini alan constructor
        this.exerciseName = name;
        this.idExercise = id;
        duration = 0;
        this.a1 = a1;
        this.b1= b1;
        this.a2 = a2;
        this.b2= b2;
        solved = 0;
        setN(N, a2-a1+1, b2-b1+1);          //parent nesnesi aralik degerleri girecek ve benim de uretebilecegim maksimum soru sayisi var 
    }                                       //eger onu asan deger girerse benim ona göre mudahele etmem gerek
    
    public Exercise(String name, int a1, int a2,int b1, int b2, int N){
        this.exerciseName = name;
        this.a1 = a1;
        this.b1= b1;
        this.a2 = a2;
        this.b2= b2;
        solved = 0;
        setN(N, a2-a1+1, b2-b1+1);
    }
    
    public void setSorulardanSoru(int index, int childAnswer){ 
        if(sorular.get(index).soruDogruMu(childAnswer)) //child nesnesi exercise yaparken sorular nesnesindeki bilgiler panele yansitiliyor.
            setTrueNumber();                            //Eger cocuk dogru yaparsa exercise nesnesindeki toplam sogru sayisi artacak
    }
    
    public void setStartSoruSuresi( int index , long currentTime){ //exercise nesnesi icindeki soru nesnesini cözmeye basladigi anda milisaniye cinsinden tutuyorum.
        sorular.get(index).setStart(currentTime);
    }
    
    public void setFinishSoruSuresi( int index , long currentTime){ //exercise nesnesinde toplam gecen süreyi hesaplarken ayni zamanda soru nesnesini ne zamanda bitirdigini milisaniye cinsinde sakliyorum
        duration += sorular.get(index).setFinish(currentTime);
    }
    
    public int getSoruCarpan1(int index){
        return sorular.get(index).getCarpan1();
    }
    
    public int getSoruCarpan2(int index){
        return sorular.get(index).getCarpan2();
    }
        
    public void SoruOlustur(){               //Farkli sorular olussun diye HashMap icinde carpan1 degerini key carpan2 degerini value olarak sakladim.
        this.sorular = new ArrayList<Soru>();
        HashMap<Integer, Integer> uniqueNumber = new HashMap<Integer,Integer>();
        Random random = new Random();
        int i = 0;
        while(i < N){                                               //Girilen toplam soru kadar uretmesi icin while dongusu
            int carpan1 = random.nextInt(a1, (a2+1) );         //alt satir ve üst sinir degeri verilerek o aralikta sayi uretilecek
            int carpan2 = random.nextInt(b1, (b2+1) );
            if(!uniqueNumber.containsKey(carpan1)){               //key degerleri ayni ise bakalim value degerleri de ayni ise demek ki soru tamamen ayni
                uniqueNumber.put(carpan1, carpan2);         //O zaman bastan random sayi üretmem gerek. Ayni degilse o zaman sorular listeme ekleyebilirim.
                this.sorular.add(new Soru(carpan1, carpan2));
                i++;
            }
            else{                                                  //key degeri ile eslesmemisse zaten bu soru yok sorular litesine ekle.
                if(uniqueNumber.get(carpan1) != carpan2){
                    uniqueNumber.put(carpan1, carpan2);
                    this.sorular.add(new Soru(carpan1, carpan2));
                    i++;
                }
            }
            
        }
    }

    public void setN(int N, int carpan1Bound, int carpan2Bound){
        if(N >= carpan1Bound * carpan2Bound)
            this.N = carpan1Bound * carpan2Bound;
        else
            this.N = N;
    }

    public int getSolved() {
        return solved;
    }

    public void setSolved(int solved) {
        this.solved = solved;
    }

    
    
    public ArrayList<Soru> getSorular() {
        return sorular;
    }
    
    public void printExercise(){
        for(Soru soru : sorular)
            System.out.println(soru);
    }

    @Override
    public String toString() {
        return "Exercise{" + "exercesiName=" + exerciseName + ", N=" + N + ", a1=" + a1 + ", a2=" + a2 + ", b1=" + b1 + ", b2=" + b2 + '}';
    }

    @Override
    public int compareTo(Exercise o) {
        //Ben skor olustururken onceligim dogru sayisi. Puanlama sistemin de 100 uzerinden dogru sayisina orantili sekilde aliyor.
        //Ama skor tablosunda siralarken dogru sayisina bakiyor ama dogru sayilarim esit ise kac saniyede yapmis ona bakiyor
        //Eger daha yavas sürede cözerse skor tablosunda o daha onde oluyor.
       if(this.trueNumber == o.trueNumber){
           if(this.duration < o.duration){
                return -1;
            }
       }
       if(this.trueNumber > o.trueNumber)
           return -1;
       if( ( (double)this.trueNumber ) / this.duration > ( (double)this.trueNumber ) / this.duration)
           return -1;
       return 1;
    }

    //Asagidaki kodlar Getter ve Setter metotlari
    
    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getFinish() {
        return finish;
    }

    public void setFinish(long finish) {
        this.finish = finish;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public int getTrueNumber() {
        return trueNumber;
    }

    public void setTrueNumber() {
        this.trueNumber++;
    }

    public int getN() {
        return N;
    }

    public void setN(int N) {
        this.N = N;
    }

    public int getA1() {
        return a1;
    }

    public void setA1(int a1) {
        this.a1 = a1;
    }

    public int getA2() {
        return a2;
    }

    public void setA2(int a2) {
        this.a2 = a2;
    }

    public int getB1() {
        return b1;
    }

    public void setB1(int b1) {
        this.b1 = b1;
    }

    public int getB2() {
        return b2;
    }

    public void setB2(int b2) {
        this.b2 = b2;
    }

    public int getIdExercise() {
        return idExercise;
    }

    public void setIdExercise(int idExercise) {
        this.idExercise = idExercise;
    }
    
    
    
    
}
