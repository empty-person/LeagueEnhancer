package InformationRetriever;

import Helper.AuthHelper;
import Helper.TrustModifier;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

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

    public void sendPut(String request, String[] body) {
        try {
            sendPutRequest(request, body);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String sendGet(String request) throws Exception {

        return sendGetRequest(request);

    }

    private String sendGetRequest(String request) throws Exception {
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
        System.out.println("__________________________________________________");
        System.out.println("Response from [" + url + "] (GET) is " + responsez + " " + responsex);
        System.out.println("__________________________________________________");
//
        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(httpClient.getInputStream()))) {

            StringBuilder response = new StringBuilder();
            String line;

            while ((line = in.readLine()) != null) {
                response.append(line);
            }

            //print result
            //System.out.println(response.toString());
            return response.toString();
        }
    }

    private void sendPutRequest(String request, String[] keyValue) throws Exception {
        String url = "https://127.0.0.1:" + AuthHelper.getPort(lockfile) + request;
        HttpURLConnection httpClient =
                (HttpURLConnection) new URL(url).openConnection();

        TrustModifier.relaxHostChecking(httpClient);

        httpClient.setRequestMethod("PUT");
        httpClient.setRequestProperty("User-Agent", "Mozilla/5.0");
        httpClient.setRequestProperty("Accept", "application/json");
        httpClient.setRequestProperty("Content-Type", "application/json");
        httpClient.setRequestProperty("Authorization", "Basic " + AuthHelper.get64(lockfile));
        httpClient.setDoOutput(true);
        String data1 = "{\n  \"lol\":{\n  \"rankedLeagueQueue\":\"RANKED_SOLO_5x5\",\n  \"rankedLeagueTier\":\"PLATINUM\",\n  \"rankedLeagueDivision\":\"I\"\n  }}\n";
        String data = "{\n  \"lol\":{\n  \"" + keyValue[0] + "\":\"" + keyValue[1] + "\" \n  }}\n";
        byte[] out = data.getBytes(StandardCharsets.UTF_8);

        OutputStream stream = httpClient.getOutputStream();
        stream.write(out);
        System.out.println("__________________________________________________");
        System.out.println("Put request..."+data+httpClient.getResponseCode() + " " + httpClient.getResponseMessage());
        System.out.println("__________________________________________________");
        httpClient.disconnect();
//
    }
}
