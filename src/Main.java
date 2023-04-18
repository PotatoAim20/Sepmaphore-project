import java.awt.event.*;
import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        // GUI ***************************************************************************************
        JFrame frame1 = new JFrame("First Frame");
        JLabel label1, label2, label3;
        final JTextField textField1, textField2, textField3;

        label1 = new JLabel("N");
        label1.setBounds(30, 20, 100, 30);
        textField1 = new JTextField();
        textField1.setBounds(200, 25, 150, 20);

        label2 = new JLabel("Buffer Size");
        label2.setBounds(30, 70, 100, 30);
        textField2 = new JTextField();
        textField2.setBounds(200, 75, 150, 20);

        label3 = new JLabel("Output");
        label3.setBounds(30, 130, 100, 20);
        textField3 = new JTextField();
        textField3.setBounds(200, 130, 150, 20);

        JButton button1 = new JButton("Start Producer");
        button1.setBounds(50, 200, 150, 30);
        //*********************************************************************************************

        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {

                long startTime = System.currentTimeMillis();
                int num = Integer.valueOf(textField1.getText());
                int v = Integer.valueOf(textField2.getText());
                Buffer buff = new Buffer(v);
                String file = String.valueOf(textField3.getText());


                Thread producerThread = new Thread(new Producer(buff, num, startTime));
                Thread consumerThread = new Thread(new Consumer(buff, file + ".txt", startTime));
                producerThread.start();
                consumerThread.start();
            }
        });

        // GUI*****************************************************************************************
        frame1.setSize(500, 500);

        frame1.add(textField1);
        frame1.add(textField2);
        frame1.add(textField3);

        frame1.add(button1);

        frame1.add(label1);
        frame1.add(label2);
        frame1.add(label3);

        frame1.setLayout(null);
        frame1.setVisible(true);
        //*********************************************************************************************
    }
}
