
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Gui {

    JLabel label1;
    JTextArea area;
    Handler handler = new Handler();
    File file;
    int step = 0;

    void go() {
        JFrame frame = new JFrame("Data-Compressor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.GRAY);
        JPanel panel1 = new JPanel();
        panel1.setBackground(Color.GRAY);
        JLabel label = new JLabel("Enter address of file");
        label1 = new JLabel("");
        label.setFont(new Font("serif", Font.BOLD, 20));
        area = new JTextArea(2, 30);

        area.setDragEnabled(true);
        area.setFont(new Font("serif", Font.PLAIN, 15));
        area.setLineWrap(true);
        JButton confirm = new JButton("confirm");
        confirm.addActionListener(new confirmListener());
        panel1.add(label);
        panel1.add(BorderLayout.CENTER, area);
        panel1.add(confirm);
        panel1.add(label1);
        JPanel panel2 = new JPanel();
        panel2.setBackground(Color.GRAY);
        JButton encode = new JButton("Encode");
        encode.addActionListener(new encodeListener());
        JButton decode = new JButton("Decode");
        decode.addActionListener(new decodeListener());
        panel2.add(encode);
        panel2.add(decode);
        panel.add(panel1);
        panel.add(panel2);
        //
        frame.getContentPane().add(BorderLayout.CENTER, panel);
        frame.setSize(400, 400);
        frame.setVisible(true);
    }

    class confirmListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.out.println(area.getText());

            file = new File(area.getText()+".txt");
            //System.out.println(file.getName());
            //System.out.println(file.getAbsolutePath());

            if (file.exists()) {
                label1.setText("confirmed");
                step = 1;
            }
            else
                label1.setText("Not a valid File");
        }
    }

    class encodeListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (step > 0)
                if (file.exists() && file.length() > 0) {
                    handler.start(file);
                    step=0;
                    label1.setText("");
                }
        }
    }

    class decodeListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int stat=0;
            if (step > 0 && file.exists()) {
                File dfile = new File(file.getParentFile().getAbsolutePath() + File.separator + file.getName().substring(0,file.getName().length()-4) + "_Decoded.txt");
                stat=handler.decode(file, dfile);
                label1.setText("");
                step=0;
            }
            if(stat==-1){
                label1.setText("Decoding DataFile Doesn't exist");
            }
        }
    }

}
