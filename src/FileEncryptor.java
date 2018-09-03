import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

public class FileEncryptor {
    public static void main(String [] args)
    {
        MsgEnc encryptor = new MsgEnc();

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(800,200);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        frame.add(panel);
        panel.setBackground(Color.GRAY);

        JTextField urlField = new JTextField();
        urlField.setToolTipText("paste url of file");
        urlField.setFont(new Font("TimesRoman" ,Font.PLAIN, 25));
        panel.add(urlField);
        urlField.setBounds(100, 50, 600, 50);

        JButton encrypt = new JButton("Encrypt");
        JButton decrypt = new JButton("Decrypt");
        panel.add(encrypt);
        panel.add(decrypt);

        encrypt.setBounds(100, 100, 300, 50);
        decrypt.setBounds(400, 100, 300, 50);

        encrypt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                byte [] fileBytes = null;
                File file = new File(urlField.getText());
                try {
                    fileBytes = Files.readAllBytes(file.toPath());
                    encryptor.setBytes(fileBytes);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try (FileOutputStream fos = new FileOutputStream(urlField.getText()))
                {
                    fos.write(encryptor.getEncryptedBytes());
                    System.out.println("Encrypted");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        decrypt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                byte [] fileBytes = null;
                File file = new File(urlField.getText());
                try {
                    fileBytes = Files.readAllBytes(file.toPath());
                    encryptor.setBytes(fileBytes);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try (FileOutputStream fos = new FileOutputStream(urlField.getText()))
                {
                    fos.write(encryptor.getDecryptedBytes());
                    System.out.println("Decrypted");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
