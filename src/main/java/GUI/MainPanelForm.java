package GUI;

import GUI.Helper.FormRobot;
import HeheXD.SafeAutoAcceptProcess;
import Helper.env;
import InformationParser.Parser;
import com.formdev.flatlaf.FlatIntelliJLaf;

import javax.swing.*;
import javax.swing.plaf.ComboBoxUI;
import javax.swing.plaf.metal.MetalButtonUI;
import javax.swing.plaf.metal.MetalComboBoxUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class MainPanelForm {
    private JPanel panel1;
    private JLabel name;
    private JLabel tag;
    private JLabel icon;
    private JComboBox rankedLeagueTierBox;
    private JComboBox rankedLeagueDivisionBox;
    private JComboBox rankedLeagueQueueBox;
    private JPanel iconPanel;
    private JTextArea statusMessage;
    private JButton submitStatusButton;
    private JCheckBox autoacceptCheckBox;
    private JCheckBox devCheckBox1;
    private JCheckBox autobanCheckBox;
    private JCheckBox instalockCheckBox;
    private JPanel checkmarkButtons;

    public MainPanelForm() throws Exception {
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                try {
                    updateImageIcon();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 30, 4000);

        invokeImageUpdate(5);
        name.setText(Parser.getName());
        tag.setText(Parser.getTag());
        rankedLeagueTierBox.setUI(hideArrow(rankedLeagueTierBox));
        rankedLeagueDivisionBox.setUI(hideArrow(rankedLeagueDivisionBox));
        rankedLeagueQueueBox.setUI(hideArrow(rankedLeagueQueueBox));
        submitStatusButton.setUI((new MetalButtonUI()));
        //statusMessage.setText(Parser.getStatusMessage());
        submitStatusButton.addActionListener(e -> {
            try {
                FormRobot.changeMe(statusMessage.getName(), Objects.requireNonNull(statusMessage.getText()).toString());
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
        iconPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    IconPanel.generate();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });

//        panel1.addMouseMotionListener(new MouseMotionAdapter() {
//            @Override
//            public void mouseMoved(MouseEvent e) {
//                try {
//                    updateImageIcon();
//                } catch (Exception exception) {
//                    exception.printStackTrace();
//                }
//            }
//        });
        autoacceptCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(autoacceptCheckBox.isSelected());
                if (autoacceptCheckBox.isSelected()) {
                    try {
                        SafeAutoAcceptProcess.start();
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                } else {
                    SafeAutoAcceptProcess.stop();
                }
            }
        });
        instalockCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (instalockCheckBox.isSelected()){
                    try {
                        ChampionsPanel.generate();
                        new Timer().scheduleAtFixedRate(new TimerTask() {
                            @Override
                            public void run() {
                                System.out.println(ChampionsPanel.getValue());
                            }
                        }, 30, 1000);
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }else {
                    ChampionsPanel.turnOff();
                }

            }
        });
    }

    public static void generate() throws Exception {
        FlatIntelliJLaf.setup();
        //UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        UIManager.put("ComboBox.squareButton", Boolean.FALSE);

        JFrame frame = new JFrame("dfg");

        frame.setContentPane(new MainPanelForm().panel1);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    // TODO: 10/23/2021 do.it.
    private void invokeImageUpdate(int timer) throws InterruptedException {

    }

    private void updateImageIcon() throws Exception {
        BufferedImage bufferedImage =
                ImageManager.simpleResizeImage(ImageManager.imageToBufferedImage(new ImageIcon(new URL("https://ddragon.leagueoflegends.com/cdn/11.21.1/img/profileicon/" + Parser.getIconId() + ".png")).getImage()), 240);
        ImageIcon imageIcon1 = new ImageIcon(bufferedImage);
        icon.setIcon(imageIcon1);
        if (env.isLogging()) {
            System.out.println("Profile icon updated");

        }
    }

    private void setProperItems(JComboBox comboBox) throws Exception {
        String[] comboBoxList = new String[comboBox.getItemCount() + 1];

        for (int i = 0; i < comboBox.getItemCount(); i++) {
            comboBoxList[i + 1] = comboBox.getItemAt(i).toString();

        }
        comboBox.removeAllItems();

        String firstItem = switch (comboBox.getName()) {
            case "rankedLeagueTier" -> Parser.getLeagueTier();
            case "rankedLeagueQueue" -> Parser.getLeagueQueue();
            case "rankedLeagueDivision" -> Parser.getLeagueDivision();
            default -> "In Dev";
        };
        comboBoxList[0] = firstItem;
        for (int i = 0; i < comboBoxList.length; i++) {
            if (i > 0) {
                if (comboBoxList[i].equals(firstItem)) {
                    continue;
                }
            }
            comboBox.addItem(comboBoxList[i]);


        }
        System.out.println("Setting the \"" + comboBox.getName() + "\" combo box");
        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    FormRobot.changeMe(comboBox.getName(), Objects.requireNonNull(comboBox.getSelectedItem()).toString());
                } catch (Exception exception) {
                    exception.printStackTrace();
                }

//                rankedLeagueTierBox.getSelectedItem()
            }
        });
    }

    private ComboBoxUI hideArrow(JComboBox comboBox) throws Exception {
        //System.out.println(comboBox.getItemCount());
        setProperItems(comboBox);
        return (new MetalComboBoxUI() {
            public void layoutComboBox(Container parent, MetalComboBoxLayoutManager manager) {
                super.layoutComboBox(parent, manager);
                arrowButton.setBounds(0, 0, 0, 0);
            }
        });
    }
}
