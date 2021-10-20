import GUI.MainPanelForm;
import InformationRetriever.Method;
import InformationRetriever.RequestRobot;

import java.io.IOException;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        MainPanelForm.generate();

        RequestRobot robot = new RequestRobot();
        String s = robot.doRequest("/lol-chat/v1/me", Method.GET);
        //robot.doRequest("/lol-chat/v1/me", Method.PUT);
       // robot.doRequest("/lol-chat/v1/me", Method.PUT, new String[]{"rankedLeagueDivision", "II"});


        System.out.println(s);
//        try {
//            URL url = new URL("http://cdn.merakianalytics.com/riot/lol/resources/latest/en-US/champions/Akali.json");
//            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
//            conn.setRequestMethod("GET");
//            conn.connect();
//            if(conn.getResponseCode() == 200) {
//                Scanner scan = new Scanner(url.openStream());
//                while(scan.hasNext()) {
//                    String temp = scan.nextLine();
//
//                    System.out.println(temp + " line");
//                }
//            }
//        }finally {
//            System.out.println("finally");
//        }
    }

}

