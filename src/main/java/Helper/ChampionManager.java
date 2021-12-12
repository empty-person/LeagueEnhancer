package Helper;

import InformationRetriever.Method;
import InformationRetriever.RequestRobot;
import InformationRetriever.RequestStation;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class ChampionManager {
    File file;

    public ChampionManager() {
        try {
            retrieveChampionsData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {

        new RequestRobot().doRequest("/lol-champ-select/v1/session/actions/", Method.PATCH, new String[]{});
    }

    public String getChampionID(String championName) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file.getPath())));
        String e = new String();
        String strLine;
        StringBuilder lines = new StringBuilder();
        while ((strLine = reader.readLine()) != null) {
            lines.append(strLine);
        }
        reader.close();
        String a = new String(lines).substring(lines.lastIndexOf("\"" + championName + "\",\"key\":") + championName.length() + 10, lines.indexOf("\"" + championName + "\",\"key\":") + 45);
        System.out.println(a);
        return a.substring(0, a.indexOf("\",\"name\":\""));
    }

    private void retrieveChampionsData() throws IOException {

        file = new File("src/cache/" + getLatestPatch());
        boolean result = file.createNewFile();
        if (result) {
            System.out.println("File created: " + file.getCanonicalPath());
            InputStream is = new URL("https://ddragon.leagueoflegends.com/cdn/" + getLatestPatch() + "/data/en_US/champion.json").openStream();
            byte[] a = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8)).readLine().getBytes();
            is.close();
            FileOutputStream fileOutputStream = new FileOutputStream(file.getPath(), true);
            fileOutputStream.write(a);
            fileOutputStream.close();
        } else {
            System.out.println("File already exist at location: " + file.getCanonicalPath());
        }

    }

    private String getLatestPatch() throws IOException {
        String a;
        InputStream is = new URL("https://ddragon.leagueoflegends.com/api/versions.json").openStream();
        a = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8)).readLine().split(",")[0];
        is.close();
        return a.substring(a.indexOf("\"") + 1, a.lastIndexOf("\"") + 0);
    }
}
