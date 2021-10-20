package GUI;

import GUI.Helper.FormRobot;
import InformationParser.Parser;
import com.formdev.flatlaf.FlatIntelliJLaf;

import javax.swing.*;
import javax.swing.plaf.ComboBoxUI;
import javax.swing.plaf.metal.MetalComboBoxUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Objects;

public class MainPanelForm {
    private JPanel panel1;
    private JLabel name;
    private JLabel tag;
    private JLabel icon;
    private JComboBox rankedLeagueTierBox;
    private JComboBox rankedLeagueDivisionBox;
    private JComboBox rankedLeagueQueueBox;

    public MainPanelForm() throws Exception {
        icon.setIcon(new ImageIcon(new URL("https://ddragon.leagueoflegends.com/cdn/11.21.1/img/profileicon/" + Parser.getIconId() + ".png")));
        name.setText(Parser.getName());
        tag.setText(Parser.getTag());
        rankedLeagueTierBox.setUI(hideArrow(rankedLeagueTierBox));
        rankedLeagueDivisionBox.setUI(hideArrow(rankedLeagueDivisionBox));
        rankedLeagueQueueBox.setUI(hideArrow(rankedLeagueQueueBox));


    }

    public static void generate() throws Exception {
        FlatIntelliJLaf.setup();
        //UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        UIManager.put("ComboBox.squareButton", Boolean.FALSE);

        JFrame frame = new JFrame("dfg");

        frame.setContentPane(new MainPanelForm().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void setProperItems(JComboBox comboBox) throws Exception {
        String[] comboBoxList = new String[comboBox.getItemCount() + 1];

        for (int i = 0; i < comboBox.getItemCount(); i++) {
            comboBoxList[i + 1] = comboBox.getItemAt(i).toString();

        }
        comboBox.removeAllItems();

        String firstItem;
        if (comboBox.getName().equals("rankedLeagueTier")) {
            firstItem = Parser.getLeagueTier();

        } else if (comboBox.getName().equals("rankedLeagueQueue")) {
            firstItem = Parser.getLeagueQueue();

        } else if (comboBox.getName().equals("rankedLeagueDivision")) {
            firstItem = Parser.getLeagueDivision();

        } else {
            firstItem = "In Dev";
        }
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
