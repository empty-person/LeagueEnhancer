package HeheXD;

import InformationParser.Parser;
import InformationRetriever.Method;
import InformationRetriever.RequestRobot;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class SafeAutoAcceptProcess {

    static RequestRobot robot;
    static Timer timer = new Timer();
    static {
        try {
            robot = new RequestRobot();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void stop(){
        timer.cancel();
    }
    public static void start() throws Exception {


        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                try {
                    String searchState = Parser.getSearchState();
                    if (searchState.equals("Found")){
                        robot.doRequest("/lol-matchmaking/v1/ready-check/accept", Method.POST);
                        timer.cancel();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 30, 1000);
        //Invalid Searching Found

    }


}
