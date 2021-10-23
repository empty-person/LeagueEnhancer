package GUI;

import GUI.Helper.FormRobot;
import InformationParser.Parser;
import com.formdev.flatlaf.FlatIntelliJLaf;
import org.imgscalr.Scalr;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class IconPanel {
    private JPanel panel1;
    private JTextArea textArea1;
    private JLabel icc;
    private JButton button1i;

    public IconPanel() throws Exception {
        refreshIcon();

        //char[] forSomeWeirdReason = new char[6];
        textArea1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String text = String.copyValueOf(textArea1.getText().toCharArray()).replaceAll("[^0-9]", "");
                textArea1.setText(text);


                try {
                    URL url = new URL(("https://ddragon.leagueoflegends.com/cdn/11.21.1/img/profileicon/" + text + ".png"));
                    HttpURLConnection httpClient =
                            (HttpURLConnection) url.openConnection();
                    if (httpClient.getResponseCode() == 200) {

                        BufferedImage bufferedImage = ImageManager.simpleResizeImage(ImageManager.imageToBufferedImage(new ImageIcon(url).getImage()), 200);
                        ImageIcon imageIcon1 = new ImageIcon(bufferedImage);
                        icc.setText(text);
                        icc.setIcon(imageIcon1);
                        button1i.setVisible(true);
                    }else{
                        button1i.setVisible(false);
                    }

                    httpClient.disconnect();
                } catch (Exception malformedURLException) {
                    malformedURLException.printStackTrace();
                }




            }
        });

        panel1.addMouseMotionListener(new MouseMotionAdapter() {
            String a = "0";

            @Override
            public void mouseMoved(MouseEvent e) {
                if (a.equals("0")) {
                    button1i.setVisible(false);
                    a = "!0";
                }
            }
        });

        button1i.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    FormRobot.changeMe("icon", icc.getText());

                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
    }



    public static void generate() throws Exception {
        FlatIntelliJLaf.setup();
        //UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        // UIManager.put("ComboBox.squareButton", Boolean.FALSE);

        JFrame frame = new JFrame("dfg");

        frame.setContentPane(new IconPanel().panel1);

        frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }

    private void refreshIcon() throws Exception {
      URL url = new URL("https://ddragon.leagueoflegends.com/cdn/11.21.1/img/profileicon/"+Parser.getIconId()+".png");
        BufferedImage bufferedImage = ImageManager.simpleResizeImage(ImageManager.imageToBufferedImage(new ImageIcon(url).getImage()), 200);
        ImageIcon imageIcon1 = new ImageIcon(bufferedImage);

        icc.setIcon(imageIcon1);
    }

    private void refreshIcon(String id) throws MalformedURLException {
        icc.setIcon((new ImageIcon(new URL("https://ddragon.leagueoflegends.com/cdn/11.21.1/img/profileicon/" + id + ".png"))));
    }


}
