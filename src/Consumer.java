//import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import java.awt.event.*;
import javax.swing.*;
import java.util.concurrent.TimeUnit;

public class Consumer implements Runnable {

    Buffer buff;
    int cnt = 0;
    String maxPrime, file;
    long startTime;

    public Consumer(Buffer b, String s, long st) {
        this.buff = b;
        file = s;
        startTime = st;
    }

    @Override
    public void run() {
        String item;
//        File newFile = new File(file);

        try {
            FileWriter fw = new FileWriter(file);
            while (true) {
                if (buff.numOfElements == 0 && buff.checker) {
                    fw.close();
                    long endTime = System.currentTimeMillis();

                    // GUI *************************************************************************
                    JFrame frame2 = new JFrame("frame2");
                    JLabel label1, output1, label2, output2, label3, output3;

                    label1 = new JLabel("The largest prime number");
                    label1.setBounds(30,20,300,30);
                    output1 = new JLabel(maxPrime);
                    output1.setBounds(310,20, 40,30);

                    label2 = new JLabel("# of element (Prime Number) generated");
                    label2.setBounds(30,60, 300,30);
                    output2 = new JLabel(Integer.toString(cnt));
                    output2.setBounds(310,60, 40,30);

                    label3 = new JLabel("Time elapsed since the start of processing");
                    label3.setBounds(30,90, 300,30);
                    long timeCal = endTime - startTime;
                    String time = String.valueOf(timeCal) + "ms";
                    output3 = new JLabel(time);
                    output3.setBounds(310,90, 200,30);

                    JButton button2 = new JButton("Finish Program");
                    button2.setBounds(30,125,150,30);
                    button2.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent actionEvent) {
                            System.exit(0);
                        }
                    });


                    frame2.add(label1);
                    frame2.add(output1);
                    frame2.add(label2);
                    frame2.add(output2);
                    frame2.add(label3);
                    frame2.add(output3);
                    frame2.add(button2);
                    frame2.setSize(450,230);
                    frame2.setLayout(null);
                    frame2.setVisible(true);
                    TimeUnit.SECONDS.sleep(20);
                    System.exit(0);

                    //**************************************************************************************

                } else {
                    item = buff.consume();
                    fw.write(String.valueOf(item) + " , ");
                    maxPrime = item;
                    cnt++;
                }
            }
        } catch (IOException e) {
            Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, e);
        } catch (InterruptedException e) {
            Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}