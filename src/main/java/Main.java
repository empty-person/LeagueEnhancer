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

