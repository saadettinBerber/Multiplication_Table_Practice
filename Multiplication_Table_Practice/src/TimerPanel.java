import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimerPanel extends JFrame {

    private JLabel timeLabel;
    private Timer timer;
    private int seconds;

    public TimerPanel() {
        setTitle("Timer");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(200, 100);  //Cercevenin boyutlari
        timeLabel = new JLabel("0");
        add(timeLabel);

        // Timer oluşturma
        timer = new Timer(1000, new TimerListener()); //1 saniyede 1 calismasini sagliyor
        seconds = 0;
        
        setVisible(true);
        // Timer'ı başlatma
        timer.start();
    }

    private class TimerListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        seconds++;
        int minutes = seconds / 60;
        int sayi = minutes / 60;
        int remainingSeconds = seconds % 60;
        //saat, dakika, saniye cinsinde JPanelde yaziyor.
        String time = String.format("%02d:%02d:%02d", sayi,minutes, remainingSeconds);
        timeLabel.setText(time);
    }
}


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TimerPanel();
            }
        });
    }
}

