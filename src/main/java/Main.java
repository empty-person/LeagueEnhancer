import Helper.AuthHelper;
import Helper.TrustModifier;
import InformationRetriever.Method;
import InformationRetriever.RequestRobot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

public class Main {
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {

        RequestRobot requestRobot = new RequestRobot();
        requestRobot.doRequest("/lol-chat/v1/me", Method.GET);

    }


}

