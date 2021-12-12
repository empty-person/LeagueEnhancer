package InformationParser;

import Helper.env;
import InformationRetriever.Method;
import InformationRetriever.RequestRobot;

import java.io.IOException;
import java.util.Arrays;
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
    }

    public static String getTag() throws Exception {
        return getMe("gameTag");
    }

    public static String getLeagueTier() throws Exception {
        return getMe("rankedLeagueTier");
    }

    public static String getLeagueQueue() throws Exception {
        return getMe("rankedLeagueQueue");
    }

    public static String getLeagueDivision() throws Exception {
        return getMe("rankedLeagueDivision");
    }

    public static String getIconId() throws Exception {
        return getMe("icon");
    }

    public static String getStatusMessage() throws Exception {
        return getMe("statusMessage");
    }

    public static String getSearchState() throws Exception {
        return getMe("searchState");
    }

    public static String[] getOwnedChampions() throws Exception {
        return champsRetrieve();
    }

    private static String[] champsRetrieve() throws Exception {
        String[] s = robot.doRequest("/lol-champions/v1/owned-champions-minimal", Method.GET).split("},");
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < s.length; i++) {
            String temp = s[i] + s[i + 1];
            i++;
            text.append(temp, temp.indexOf("ownership") + 45, temp.indexOf("rental") - 2)
                    .append(":")
                    .append(temp, temp.indexOf("alias") + 8, temp.indexOf("banVoPath") - 3)
                    .append("#");
        }
        System.out.println(text);

        StringBuilder returnChampions = new StringBuilder();

        s = String.valueOf(text).split("#");

        for (int i = 0; i < s.length; i++) {
            String[] temp = s[i].split(":");
            if (temp[0].equals("true")) {
                returnChampions.append(temp[1] + ",");
            }
        }
        return String.valueOf(returnChampions).split(",");
    }

    private static String getMe(String key) throws Exception {
        String response;
        if (key.equals("searchState")) {
            response = robot.doRequest("/lol-lobby/v2/lobby/matchmaking/search-state", Method.GET);
        } else {
            response = robot.doRequest("/lol-chat/v1/me", Method.GET);
        }
        if (!key.equals("statusMessage")) {
            for (String s : response.split(",")) {
                if (s.split(":")[0].matches("\"" + key + "\"")) {
                    return new String(s.split(":")[1].replaceAll("\"", "")).replaceAll("}", "");
                }
            }
        } else {
            String[] split = response.split(",");
            for (int i = split.length - 1; i > 0; i--) {
                String s = split[i];
                if (s.split(":")[0].matches("\"" + key + "\"")) {
                    return new String(s.split(":")[1].replaceAll("\"", ""));
                }
            }
        }


        return "Undefined";
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
            if (env.isLogging()){
                System.out.println(lala);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
