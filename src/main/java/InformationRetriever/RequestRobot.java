package InformationRetriever;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class RequestRobot {

    RequestStation requestStation;
    String LockfileText;

    public RequestRobot() throws IOException {
        this.LockfileText = RetrieveLockfileText();
        requestStation = new RequestStation(LockfileText);
    }

    private String RetrieveLockfileText() throws IOException {
        final String returnS;

        Optional<ProcessHandle> leagueClientUx = ProcessHandle.allProcesses()
                .parallel()
                .filter(processHandle -> processHandle.info().toString().contains("LeagueClientUx.exe"))
                .findAny();
        System.out.println(leagueClientUx.isPresent());
        while (leagueClientUx.isEmpty()) {
            leagueClientUx = ProcessHandle.allProcesses()
                    .parallel()
                    .filter(processHandle -> processHandle.info().toString().contains("LeagueClientUx.exe"))
                    .findAny();
            try {
                TimeUnit.SECONDS.sleep(5);
                System.out.println("Waiting! ");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //String s = ProcessHandle.of(23).get().info().toString();
        String path = leagueClientUx.orElseThrow().info().command().orElseThrow()
                .replaceAll("LeagueClientUx.exe", "lockfile");

        BufferedReader reader = new BufferedReader(new FileReader(path));


        return reader.readLine();
    }
    /**
     This method runs with /lol-chat/v1/me address if request is empty
     */
    public String doRequest(String request, Method method) throws Exception {
        if (request.isEmpty()){
            request = "/lol-chat/v1/me";
        }
        if (method == Method.GET) {

            return requestStation.sendGet(request);
        }
        if (method == Method.PUT) {
            requestStation.sendPut(request, new String[]{"rankedLeagueTier", "IRON"});
        }
        if (method == Method.POST){
            requestStation.sendPost(request, new String[]{""});
        }


        return "";

    }

    public String doRequest(String request, Method method, String[] body) throws Exception {
        if (request.isEmpty()){
            request = "/lol-chat/v1/me";
        }
        if (method == Method.GET) {

            return requestStation.sendGet(request);
        }
        if (method == Method.PUT) {
            requestStation.sendPut(request, body);
        }
        if (method == Method.POST){
            requestStation.sendPost(request, body);
        }
        if (method==Method.PATCH){
            requestStation.sendPatch(request);
        }

        return "";

    }

}