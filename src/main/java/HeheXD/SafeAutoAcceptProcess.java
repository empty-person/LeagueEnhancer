package HeheXD;

import InformationParser.Parser;
import InformationRetriever.Method;
import InformationRetriever.RequestRobot;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class SafeAutoAcceptProcess {

    static RequestRobot robot;

    static {
        try {
            robot = new RequestRobot();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws Exception {
        Timer timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                try {
                    System.out.println(Parser.getSearchState());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 30, 5000);



    }


}
