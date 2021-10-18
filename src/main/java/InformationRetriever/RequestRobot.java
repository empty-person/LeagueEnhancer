package InformationRetriever;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;

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
        while (leagueClientUx.isEmpty()) {
            leagueClientUx = ProcessHandle.allProcesses()
                    .parallel()
                    .filter(processHandle -> processHandle.info().toString().contains("LeagueClientUx.exe"))
                    .findAny();
            try {
                leagueClientUx.wait(5000);
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

    public void doRequest(String request, Method method) {
        if (method == Method.GET){
            requestStation.sendGet(request);
        }

    }

}