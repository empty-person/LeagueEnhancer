package InformationRetriever;

import Helper.AuthHelper;
import Helper.TrustModifier;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RequestStation {
    private final String lockfile;

    public RequestStation(String lockfile) {
        this.lockfile = lockfile;
        try {
            sendGetRequest("/lol-chat/v1/me");
        } catch (Exception e) {
            System.out.println("Something went wrong with RequestStation!!!");
            System.out.println(".\n.\n.\n." + " " + e.getMessage());
        }
    }
    public void sendGet(String request){
        try {
            sendGetRequest(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void sendGetRequest(String request) throws Exception {
//https://127.0.0.1:65227/
//https://127.0.0.1:65227/lol-chat/v1/me
        String url = "https://127.0.0.1:" + AuthHelper.getPort(lockfile) + request;
        HttpURLConnection httpClient =
                (HttpURLConnection) new URL(url).openConnection();

        TrustModifier.relaxHostChecking(httpClient);

        httpClient.setRequestMethod("GET");
        httpClient.setRequestProperty("User-Agent", "Mozilla/5.0");
        httpClient.setRequestProperty("Accept", "application/json");
        httpClient.setRequestProperty("Authorization", "Basic " + AuthHelper.get64(lockfile));
//
        int responsez = httpClient.getResponseCode();
        String responsex = httpClient.getResponseMessage();
        System.out.println("Response from [" + url + "] is " + responsez + " " + responsex);
//
        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(httpClient.getInputStream()))) {

            StringBuilder response = new StringBuilder();
            String line;

            while ((line = in.readLine()) != null) {
                response.append(line);
            }

            //print result
            System.out.println(response.toString());

        }
    }
}
