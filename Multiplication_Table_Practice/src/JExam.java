
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Saadettin
 */
public class JExam extends javax.swing.JDialog {

    FileTransaction file;
    Exercise exercise;
    TimerPanel time;
    int i, seconds;
    
    
    public JExam(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        try {
            initComponents();
            setLocationRelativeTo(null);
            time = new TimerPanel();                      //Zamanlayici calismaya basladi.
            file = new FileTransaction();
            exercise = file.doosyaOkuExercise();        //exercise.bin doyasindan hangi exercise nesnesini yapacak onu ogreniyoruz
            exercise.printExercise();
            toplamSoruText.setText(String.valueOf( exercise.getN() ) );
            soruSayisiText.setText("1");
            i = seconds = 0;                            //exercise basladiginda ilk sorudan basladigi icin i degeri 0 olur.
            exercise.setStartSoruSuresi(0, System.currentTimeMillis());
            goruntuleCarpanlar();
            
            
        } catch (IOException ex) {
            Logger.getLogger(JExam.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Eger kullanici direkt kapatirsa egzersizi diye bunu da ekledim.
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                //JOptionPane.showMessageDialog(null, "Alıştırmayı bitirdiniz\nTebrikler");
                Child child;
                try {
                    time.dispose();
                    //kapatilinca JExam hangi soruda kalmissa onun bitis suresini set ediyor ve kapattigi icin yanlis olarak sayiliyor.
                    if(i < exercise.getSorular().size())
                        exercise.setFinishSoruSuresi(i, System.currentTimeMillis());
                    child = (Child)file.dosyaInsideOku();
                    child.addDoneExercise(exercise, exercise.getIdExercise());
                    file.dosyaGuncelleChild(child);
                    file.dosyaInsideYaz(child);
                    exercise.printExercise();
                    file.dosyaSil("exercise.bin");
                } catch (IOException ex) {
                    Logger.getLogger(JExam.class.getName()).log(Level.SEVERE, null, ex);
                }
                JOptionPane.showMessageDialog(null, exercise.getN() + " tane sorudan "+ exercise.getTrueNumber() + "tane doğrunuz var.");
                JChildPanel jChild = new JChildPanel();
                jChild.setVisible(true);
            }
        });
        
    }

    public void goruntuleCarpanlar(){
        //exercise nesnesindeki bulundugu soruyu goruntuluyor
        soruSayisiText.setText(String.valueOf(i+1) + ".");
        ilkCarpanText.setText(String.valueOf( exercise.getSoruCarpan1(i)) );
        ikinciCarpanText.setText(String.valueOf( exercise.getSoruCarpan2(i) ));
        resultTextField.setText("");
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ikinciCarpanText = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        ilkCarpanText = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        resultTextField = new javax.swing.JTextField();
        ileriButton = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        soruSayisiText = new javax.swing.JLabel();
        toplamSoruText = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        timeTextt = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        ikinciCarpanText.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        ikinciCarpanText.setText(".");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel2.setText("x");

        ilkCarpanText.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        ilkCarpanText.setText(".");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel4.setText("=");

        ileriButton.setText("İleri");
        ileriButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ileriButtonActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 0, 0));
        jLabel5.setText("Soru");

        soruSayisiText.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        soruSayisiText.setForeground(new java.awt.Color(255, 0, 51));
        soruSayisiText.setText(".");

        toplamSoruText.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        toplamSoruText.setText(".");

        jLabel1.setText("Toplam Soru Sayısı:");

        timeTextt.setText(".");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(ilkCarpanText, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel2)
                            .addGap(45, 45, 45)
                            .addComponent(ikinciCarpanText, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(resultTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(ileriButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(soruSayisiText, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(95, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(timeTextt, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(toplamSoruText, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(toplamSoruText)
                        .addComponent(jLabel1))
                    .addComponent(timeTextt))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(soruSayisiText)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ikinciCarpanText)
                    .addComponent(jLabel2)
                    .addComponent(ilkCarpanText)
                    .addComponent(jLabel4)
                    .addComponent(resultTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
                .addComponent(ileriButton)
                .addGap(65, 65, 65))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ileriButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ileriButtonActionPerformed
       //Ileri butonuna basildigina gore artik bulundugu soru bitmistir demek.
        exercise.setFinishSoruSuresi(i, System.currentTimeMillis());
       String result = resultTextField.getText();
       if(result.length() == 0)                             //Herhangi bir sey girilmemisse bos kabul edilir ve yanlis olur.
           exercise.setSorulardanSoru(i, 0);
       else{
           //child nesnesinin cevabi set olur.
           exercise.setSorulardanSoru(i, Integer.parseInt(result)); 
       }
       //bir sonraki soruya gecmek icin i degiskeni artirilir.
       i++;
       //if blogu icerisine girmesi demek artik alistirma bitmistir demek.
       if(i >= exercise.getSorular().size()){
           Child child;
                try {
                    time.dispose();                                                          //Timer paneli ile isim bitti artik
                    child = (Child)file.dosyaInsideOku();                                   //hangi child nesnesini guncellemem gerektigini bu dosyadan okuyarak ogreniyorum
                    child.addDoneExercise(exercise, exercise.getIdExercise());      //cocuk artik gecerli exercise nesnesini bitirdigine gore scores HashMap yapisina eklemem gerek
                    file.dosyaGuncelleChild(child);                                         
                    file.dosyaInsideYaz(child);                                         //inside.bin dosyasinin icine child nesnesini yazmam gerek.
                    exercise.printExercise();                                               //Childpanel acilinca guncel cocuk bilgisini panele yansitmam gerek
                    file.dosyaSil("exercise.bin");                                       //artik exercise.bin dosyasina ihtiyacim kalmadi
                    dispose();
                } catch (IOException ex) {
                    Logger.getLogger(JExam.class.getName()).log(Level.SEVERE, null, ex);
                }
                JOptionPane.showMessageDialog(null, exercise.getN() + " tane sorudan "+ exercise.getTrueNumber() + " tane doğrunuz var.");              //Bilgilendirme mesaji
                JChildPanel jChild = new JChildPanel();             //Child panele artik geri donme zamani
                jChild.setVisible(true);
           return;
       }
       //İleri butonuna basildiktan sonra yeni soruya gecilir ondan baslangic degeri set edilir.
       exercise.setStartSoruSuresi(i, System.currentTimeMillis());
       //yeni soru goruntulenir
       goruntuleCarpanlar();
    }//GEN-LAST:event_ileriButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JExam.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JExam.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JExam.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JExam.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JExam dialog = new JExam(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ikinciCarpanText;
    private javax.swing.JButton ileriButton;
    private javax.swing.JLabel ilkCarpanText;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField resultTextField;
    private javax.swing.JLabel soruSayisiText;
    private javax.swing.JLabel timeTextt;
    private javax.swing.JLabel toplamSoruText;
    // End of variables declaration//GEN-END:variables
}
