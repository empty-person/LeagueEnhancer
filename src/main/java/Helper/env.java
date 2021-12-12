package Helper;

import io.github.cdimascio.dotenv.Dotenv;

public class env {
    // TODO: 12/12/2021 Enable/Disable all console logs there.
    static Dotenv dotenv = Dotenv.load();

    public static boolean isLogging() {
        if (dotenv.get("EnableLogging").equals("true")) {
            return true;
        }
        return false;
    }
}
