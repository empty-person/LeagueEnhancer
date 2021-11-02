package GUI;

import InformationParser.Parser;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class ChampionsPanel {


    private JPanel panel1;
    private JList list1;

    public ChampionsPanel() throws Exception {

       list1.setListData(Parser.getOwnedChampions());

        list1.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                System.out.println(list1.getSelectedIndex());
            }
        });
    }

    public static void main(String[] args) throws Exception {

        JFrame frame = new JFrame("dfg");

        frame.setContentPane(new ChampionsPanel().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
