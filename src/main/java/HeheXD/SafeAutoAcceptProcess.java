package HeheXD;

import InformationParser.Parser;
import InformationRetriever.Method;
import InformationRetriever.RequestRobot;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


public class SafeAutoAcceptProcess {

    static RequestRobot robot;
    static Timer timer = new Timer(30000, null);
    static {
        try {
            robot = new RequestRobot();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void stop(){
        timer.stop();
    }
    public static void start(){
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String searchState = Parser.getSearchState();
                    if (searchState.equals("Found")){

                        robot.doRequest("/lol-matchmaking/v1/ready-check/accept", Method.POST);
                        timer.stop();
                    }
                } catch (Exception exception){
                    exception.printStackTrace();
                }
            }
        });
        timer.restart();

//        timer.scheduleAtFixedRate(new TimerTask() {
//            @Override
//            public void run() {
//                try {
//                    String searchState = Parser.getSearchState();
//                    if (searchState.equals("Found")){
//                        robot.doRequest("/lol-matchmaking/v1/ready-check/accept", Method.POST);
//                        timer.cancel();
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }, 30, 1000);
        //Invalid Searching Found

    }


}
