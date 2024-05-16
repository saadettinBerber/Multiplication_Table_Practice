
import java.io.Serializable;
import java.time.*;

/**
 *
 * @author Saadettin
 */
public class Soru implements Serializable{
    private int carpan1;        //çarpim isleminin ilk carpani
    private int carpan2;        //çarpim isleminin ikinci carpani
    private int result;         //carpan1 * carpan2 degeri
    private int childAnswer;    //soru nesnesine verdigi cevap
    private long start;         //soruya basladigi zamanin milisaniye cinsi
    private long finish;        //soruya bitirdigi zamanin milisaniye cinsi
    private long duration;      //soruyu ne kadar surede bitirdigi
    private boolean soruDogru;  //soru dogru mu yanlis mi

    public Soru(int carpan1, int carpan2) {
        this.carpan1 = carpan1;
        this.carpan2 = carpan2;
        this.result = carpan1 * carpan2;
        this.soruDogru = false;             //child nesnesi soruyu bos birakmis ise yanlis kabul ediyoruz.
    }

     //Asagidaki kodlar Getter ve Setter metotlari
    public int getCarpan1() {
        return carpan1;
    }

    public void setCarpan1(int carpan1) {
        this.carpan1 = carpan1;
    }

    public int getCarpan2() {
        return carpan2;
    }

    public void setCarpan2(int carpan2) {
        this.carpan2 = carpan2;
    }

    public int getChildAnswer() {
        return childAnswer;
    }

    public void setChildAnswer(int childAnswer) {
        this.childAnswer = childAnswer;
    }

    public boolean soruDogruMu(int childAnswer){ //child nesnesinin verdigi cevabi set ediyor ve dogru mu diye kontrol edip ona göre set ediyor.
        setChildAnswer(childAnswer);
        if(result == childAnswer){
            this.soruDogru = true;
            return true;
        }
        return false;    
    }

    public boolean isSoruDogru() {
        return soruDogru;
    }

    public void setSoruDogru(boolean soruDogru) {
        this.soruDogru = soruDogru;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
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

    public long setFinish(long finish) {
        this.finish = finish;
        this.duration = finish - this.start;
        return duration;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }
    
    
    
    
    @Override
    public String toString() {
        return "Soru{" + "carpan1=" + carpan1 + ", carpan2=" + carpan2 + ", result/childAnswer=" + result +"/" +childAnswer+'}';
    }

    
    
    
    
    
}
