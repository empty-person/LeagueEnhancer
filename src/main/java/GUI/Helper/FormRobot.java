package GUI.Helper;

import InformationRetriever.Method;
import InformationRetriever.RequestRobot;

import java.io.IOException;

public class FormRobot {
    static RequestRobot robot;

    static {
        try {
            robot = new RequestRobot();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void changeMe(String key, String value) throws Exception {

        robot.doRequest("", Method.PUT, new String[]{key, value});


    }

}
