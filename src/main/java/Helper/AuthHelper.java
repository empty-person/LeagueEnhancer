package Helper;

import java.util.Base64;

public class AuthHelper {
    //LeagueClient:11148:62887:ZzE0dS228X2YhZuI0BaYRA:https

    public static String get64(String lockfile){
        String[] split = lockfile.split(":");
        String temp = "riot:" + split[3];

        return Base64.getEncoder().encodeToString(temp.getBytes());
    }

    public static String getPort(String lockfile){
        String[] split = lockfile.split(":");

        return split[2];
    }
}
