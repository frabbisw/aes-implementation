import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String [] args)
    {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(800,600);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        frame.add(panel);
        panel.setBackground(Color.GRAY);

        JTextArea enBox = new JTextArea();
        panel.add(enBox);
        enBox.setBounds(100, 50, 600, 200);

        JTextArea decBox = new JTextArea();
        panel.add(decBox);
        decBox.setBounds(100, 350, 600, 200);

        JButton encrypt = new JButton("Encrypt");
        JButton decrypt = new JButton("Decrypt");
        panel.add(encrypt);
        panel.add(decrypt);

        encrypt.setBounds(100, 275, 300, 50);
        decrypt.setBounds(400, 275, 300, 50);

        MsgEnc encryptor = new MsgEnc();

        encrypt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String msg = enBox.getText();
                encryptor.setMessage(msg);
                String enc = encryptor.getEncrypted();

                decBox.setText(enc);
            }
        });

        decrypt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String msg = enBox.getText();
                encryptor.setMessage(msg);
                String dec = encryptor.getDecrypted();

                decBox.setText(dec);
            }
        });
    }
}