package InformationParser;

import InformationRetriever.Method;
import InformationRetriever.RequestRobot;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Parser {
    static RequestRobot robot;

    static {
        try {
            robot = new RequestRobot();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static String getName() throws Exception {
        return getMe("gameName");
    }public static String getTag() throws Exception {
        return getMe("gameTag");
    }
    public static String getLeagueTier() throws Exception {
        return getMe("rankedLeagueTier");
    }public static String getLeagueQueue() throws Exception {
        return getMe("rankedLeagueQueue");
    }public static String getLeagueDivision() throws Exception {
        return getMe("rankedLeagueDivision");
    }public static String getIconId() throws Exception {
        return getMe("icon");
    }


    private static String getMe(String key) throws Exception {
        String response = robot.doRequest("/lol-chat/v1/me", Method.GET);
        for (String s : response.split(",")) {
            if (s.split(":")[0].matches("\"" + key + "\"")) {
                return new String(s.split(":")[1].replaceAll("\"", "")) ;
            }
        }
        System.out.println();
        return "fail";
    }

    private void tem() throws Exception {
        try {
            Map<String, String> lala = new HashMap<String, String>();
            RequestRobot robot = new RequestRobot();
            String response = robot.doRequest("/lol-chat/v1/me", Method.GET);
            for (String s : response.split(",")) {
                //gameName tag icon rank status
                if (s.split(":")[0].matches("\"gameName\"")) {
                    lala.put(s.split(":")[0].replaceAll("\"", ""), s.split(":")[1].replaceAll("\"", ""));
                }
                if (s.split(":")[0].matches("\"gameTag\"")) {
                    lala.put(s.split(":")[0].replaceAll("\"", ""), s.split(":")[1].replaceAll("\"", ""));
                }
                if (s.split(":")[0].matches("\"icon\"")) {
                    lala.put(s.split(":")[0].replaceAll("\"", ""), s.split(":")[1].replaceAll("\"", ""));
                }
                if (s.split(":")[0].matches("\"rankedLeagueDivision\"")) {
                    lala.put(s.split(":")[0].replaceAll("\"", ""), s.split(":")[1].replaceAll("\"", ""));
                }
                if (s.split(":")[0].matches("\"rankedLeagueQueue\"")) {
                    lala.put(s.split(":")[0].replaceAll("\"", ""), s.split(":")[1].replaceAll("\"", ""));
                }
                if (s.split(":")[0].matches("\"rankedLeagueTier\"")) {
                    lala.put(s.split(":")[0].replaceAll("\"", ""), s.split(":")[1].replaceAll("\"", ""));
                }
                if (s.split(":")[0].matches("\"statusMessage\"")) {
                    lala.put(s.split(":")[0].replaceAll("\"", ""), s.split(":")[1].replaceAll("\"", ""));
                }

            }
            System.out.println(lala);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
