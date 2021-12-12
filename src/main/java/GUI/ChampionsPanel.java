package GUI;

import InformationParser.Parser;
import com.formdev.flatlaf.FlatIntelliJLaf;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.WindowEvent;

public class ChampionsPanel {
    static JFrame frame = new JFrame("dfg");
    private static String value = "empty";
    private JPanel panel1;
    private JList list1;

    public ChampionsPanel() throws Exception {

        list1.setListData(Parser.getOwnedChampions());

        list1.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!list1.getValueIsAdjusting()) {
                    return;
                }
                value = String.valueOf(list1.getSelectedValue()).split("\n")[0];
            }
        });


    }

    public static String getValue() {
        return value;
    }
    public static void turnOff(){
        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
    }

    public static void generate() throws Exception {
        FlatIntelliJLaf.setup();
        //UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        // UIManager.put("ComboBox.squareButton", Boolean.FALSE);



        frame.setContentPane(new ChampionsPanel().panel1);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}
