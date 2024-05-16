
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
/**
 *
 * @author Saadettin
 */
public class FileTransaction {
    
    private HashMap<Integer, Person> person;            
    private int sonID;

    public FileTransaction() throws IOException {
        this.person = dosyaOku();                   //parent.bin dosyasi icindeki HasMap yapisini okuyup aktariyor.
    }

    public int getSonID() {
        return sonID;
    }

    public void setSonID() {                        //sonID degerini 1 artiriyor.
        this.sonID++;
    }
    
    
    
       
    public void dosyaYazExercise(Exercise ex){ //exercise nesnesini dosyaya yaziyor
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("exercise.bin"))){
            out.writeObject(ex);
        } catch (FileNotFoundException ex1) {
            Logger.getLogger(FileTransaction.class.getName()).log(Level.SEVERE, null, ex1);
        } catch (IOException ex1) {
            Logger.getLogger(FileTransaction.class.getName()).log(Level.SEVERE, null, ex1);
        }
    }
    
    public Exercise doosyaOkuExercise(){ //exercise nesnesini dosyadan okuyor.
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream("exercise.bin"))){
            Exercise ex = (Exercise)in.readObject();
            return ex;
        } catch (IOException ex) {
            Logger.getLogger(FileTransaction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FileTransaction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public void dosyaYaz(HashMap<Integer, Person> person){ //person HashMap yapisini parent.bin dosyasini yazdi.
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("parent.bin"))){
            out.writeObject(person);
        } catch (FileNotFoundException ex) {
            System.out.println("Dosya bulunamadı...");
        } catch (IOException ex) {
            System.out.println("Dosya açılırken IOException Oluştu...");
        }
    }
    
    public void dosyaGuncelle(Parent parent){//parent bilgisi guncellendigi icin artık dosyaya da yazmak gerekiyor.
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("parent.bin"))){
            int id = parent.getID();
            if(id == 0){
                int ekleID = person.size() +1;
                parent.setID(ekleID);
                person.put(ekleID, parent);
                this.sonID = ekleID;
            }
            else
                person.replace(id, parent);
            out.writeObject(person);
        } catch (FileNotFoundException ex) {
            System.out.println("Dosya bulunamadı...");
        } catch (IOException ex) {
            System.out.println("Dosya açılırken IOException Oluştu...");
        }
    }
    
    public void dosyaGuncelleChild(Child child){//parent bilgisinin icindeki child bilgisi guncellendigi icin artık dosyaya da yazmak gerekiyor.
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("parent.bin"))){
            int id = child.getParentID();
            Parent parent = (Parent)person.get(id);
            parent.childGuncelle(child);
            person.replace(parent.getID(), parent);
            out.writeObject(person);
        } catch (FileNotFoundException ex) {
            System.out.println("Dosya bulunamadı...");
        } catch (IOException ex) {
            System.out.println("Dosya açılırken IOException Oluştu...");
        }
    }
    
    public HashMap<Integer, Person> dosyaOku() throws IOException{ //parent.bin dosyasindan okuma yapiyor.
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream("parent.bin"))){
            HashMap<Integer, Person> person = (HashMap<Integer, Person>)in.readObject();
            this.sonID = person.size();
            return person;
        } catch (FileNotFoundException ex) {
            System.out.println("Dosya bulunamadı...");
        }  catch (ClassNotFoundException ex) {
            System.out.println("Sınıf Bulunamadı...");
        }
        return null;
    }
    
    public void dosyaInsideYaz(Person person){      //Misal child ya da parent giris yapinca hangi nesnenin bilgisini ekrana yazdiracagini bilmek icin insdie.bin dosyasina yazdik. Boylelikle panelde hangi nesnenin bilgilerini yansitacagimi biliyorum.
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("inside.bin"))){
            out.writeObject(person);
        } catch (FileNotFoundException ex) {
            System.out.println("Dosya bulunamadı...");
        } catch (IOException ex) {
            System.out.println("Dosya açılırken IOException Oluştu...");
        }
    }
    
    public Person dosyaInsideOku() throws IOException{ //dosyayi okuyarak hangi nesnenin bilgilerini yazdiracağimi biliyorum.
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream("inside.bin"))){
            Person person = (Person)in.readObject();
            return person;
        } catch (FileNotFoundException ex) {
            System.out.println("Dosya bulunamadı...");
        }  catch (ClassNotFoundException ex) {
            System.out.println("Sınıf Bulunamadı...");
        }
        return null;
    }
    
    public void start(Person parent){       //Proje icin baslangic kosullarini olusturmak icin
        HashMap<Integer, Person> person = new HashMap<Integer, Person>();
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("parent.bin"))){
            int size = person.size();
            person.put(++size, parent);
            out.writeObject(person);       
        } catch (FileNotFoundException ex) {
            System.out.println("Dosya bulunamadı...");
        } catch (IOException ex) {
            System.out.println("Dosya açılırken IOException Oluştu...");
        }
    }
    
    public void dosyaSil(String name){ //Herhangi dosyayi silmek icin
        File dosya = new File(name);

        // Dosyayı silme işlemi
        boolean silindi = dosya.delete();

        if (silindi) {
            System.out.println("Dosya başariyla silindi.");
        } else {
            System.out.println("Dosya silinirken bir hata olustu.");
        }
    }
    
    public void getExcelFile(String name, TreeSet<Exercise> exercises) {
        //Dosyanin ismi
        String fileName = name + "_" + "Alistirma ID_" + exercises.first().getIdExercise() + "_" + exercises.first().getExerciseName() + ".xlsx";

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Alistirma");

            // Header satırı oluşturma
            Row headerRow = sheet.createRow(0);
            ArrayList<String> header = new ArrayList<String>();
            header.add("Isim\t");header.add("Puan\t");
            header.add("Toplam Soru\t");header.add("Toplam Dogru\t");
            header.add("Toplam Yanlis\t");header.add("Toplam Sure(ms)\t");
            for(int i = 0; i < exercises.first().getN();i++){
                header.add(i+1+". soru\t");header.add("Cocuk||Cevap\t");header.add("Soru Sure(ms)\t");
            }
            for (int i = 0; i < header.size(); i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(header.get(i));
            }
            //Header satiri olustuktan sonra 2. satirdan itibaren excele yazilmali.
            //Ondan sonra sutünlara da belli basli bilgiler yaziliyor.
            int rowIndex = 1;
            for (Exercise exercise : exercises) {
                Row dataRow = sheet.createRow(rowIndex++);
                Cell nameCell = dataRow.createCell(0);
                nameCell.setCellValue(exercise.getExerciseName());

                int puan = (int) (100 * (double) exercise.getTrueNumber() / exercise.getN());
                Cell puanCell = dataRow.createCell(1);
                puanCell.setCellValue(puan);

                Cell totalSoruCell = dataRow.createCell(2);
                totalSoruCell.setCellValue(exercise.getN());

                Cell totalDogruCell = dataRow.createCell(3);
                totalDogruCell.setCellValue(exercise.getTrueNumber());

                Cell totalYanlisCell = dataRow.createCell(4);
                totalYanlisCell.setCellValue(exercise.getN() - exercise.getTrueNumber());

                Cell totalSureCell = dataRow.createCell(5);
                totalSureCell.setCellValue(exercise.getDuration());

                int cellIndex = 6;
                for (Soru soru : exercise.getSorular()) {
                    Cell soruCell = dataRow.createCell(cellIndex++);
                    soruCell.setCellValue(soru.getCarpan1() + "x" + soru.getCarpan2());

                    Cell cevapCell = dataRow.createCell(cellIndex++);
                    cevapCell.setCellValue(soru.getChildAnswer() + "||" + soru.getResult());

                    Cell sureCell = dataRow.createCell(cellIndex++);
                    sureCell.setCellValue(soru.getFinish() - soru.getStart());
                }
            }

            // Excel dosyasını kaydetme
            try (FileOutputStream fileOut = new FileOutputStream(fileName)) {
                workbook.write(fileOut);
            }

            System.out.println("Excel dosyasi olusturuldu: " + fileName);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


        
    
}
