package InformationRetriever;

import Helper.AuthHelper;
import Helper.TrustModifier;
import Helper.env;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class RequestStation {
    private final String lockfile;
    private final boolean enableLogging = env.isLogging();
    public RequestStation(String lockfile) {
        this.lockfile = lockfile;
        try {
            sendGetRequest("/lol-chat/v1/me");
        } catch (Exception e) {
            System.out.println("Something went wrong with RequestStation!!!");
            System.out.println(".\n.\n.\n." + " " + e.getMessage());
        }
    }

    public void sendPatch(String request) {
        try {
            sendPatchRequest(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendPut(String request, String[] body) {
        try {
            sendPutRequest(request, body);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendPost(String request, String[] body) {
        try {
            sendPostRequest(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String sendGet(String request) throws Exception {

        return sendGetRequest(request);

    }

    private String sendPostRequest(String request) throws Exception {
//https://127.0.0.1:65227/
//https://127.0.0.1:65227/lol-chat/v1/me
        String url = "https://127.0.0.1:" + AuthHelper.getPort(lockfile) + request;
        HttpURLConnection httpClient =
                (HttpURLConnection) new URL(url).openConnection();

        TrustModifier.relaxHostChecking(httpClient);

        httpClient.setRequestMethod("POST");
        httpClient.setRequestProperty("User-Agent", "Mozilla/5.0");
        httpClient.setRequestProperty("Accept", "application/json");
        httpClient.setRequestProperty("Content-Length", "0");
        httpClient.setRequestProperty("Authorization", "Basic " + AuthHelper.get64(lockfile));
//
        int responsez = httpClient.getResponseCode();
        String responsex = httpClient.getResponseMessage();
        if (enableLogging){
            System.out.println("__________________________________________________");
            System.out.println("Response from [" + url + "] (POST) is " + responsez + " " + responsex);
            System.out.println("__________________________________________________");
        }

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
        if (enableLogging){
            System.out.println("__________________________________________________");
            System.out.println("Response from [" + url + "] (GET) is " + responsez + " " + responsex);
            System.out.println("__________________________________________________");
        }

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
        String data = "{\n  \"" + "how" + "\":\"" + "is this possible" + "\" \n  }";
        if (!keyValue[0].equals("statusMessage") && !keyValue[0].equals("icon")) {
            data = "{\n  \"lol\":{\n  \"" + keyValue[0] + "\":\"" + keyValue[1] + "\" \n  }}\n";
        } else {
            data = "{\n  \"" + keyValue[0] + "\":\"" + keyValue[1] + "\" \n  }";
        }
        byte[] out = data.getBytes(StandardCharsets.UTF_8);

        OutputStream stream = httpClient.getOutputStream();
        stream.write(out);
        if (enableLogging) {
            System.out.println("__________________________________________________");
            System.out.println("Put request..." + data + httpClient.getResponseCode() + " " + httpClient.getResponseMessage());
            System.out.println("__________________________________________________");
        }
        httpClient.disconnect();
//
    }

    private void sendPatchRequest(String request) throws Exception {
        String url = "https://127.0.0.1:" + AuthHelper.getPort(lockfile) + request;
        HttpURLConnection httpClient =
                (HttpURLConnection) new URL(url).openConnection();

        TrustModifier.relaxHostChecking(httpClient);
        httpClient.setRequestProperty("X-HTTP-Method-Override", "PATCH");
        httpClient.setRequestMethod("POST");
        httpClient.setRequestProperty("User-Agent", "Mozilla/5.0");
        httpClient.setRequestProperty("Accept", "application/json");
        httpClient.setRequestProperty("Content-Type", "application/json");
        httpClient.setRequestProperty("Authorization", "Basic " + AuthHelper.get64(lockfile));
        httpClient.setDoOutput(true);
        String data =

                "      {\n" +
                "        \"championId\": 36,\n" +
                "        \"completed\": true,\n" +
                "      }\n"
                ;

        byte[] out = data.getBytes(StandardCharsets.UTF_8);

        OutputStream stream = httpClient.getOutputStream();
        stream.write(out);
        System.out.println("__________________________________________________");
        System.out.println("PATCH request..." + data +" "+ httpClient.getResponseCode() + " " + httpClient.getResponseMessage());
        System.out.println("__________________________________________________");
        httpClient.disconnect();
    }

}
