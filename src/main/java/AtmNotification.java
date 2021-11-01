import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class AtmNotification {
    public static final String NOTIFICATION_FAILED = "Notification was not successful\n";

    static Path fileName = Path.of("AtmNotifications.txt");
    static boolean once = true;
    static boolean flagForTenPercentNotification = true;
    static boolean flagForTwentyPercentNotification = true;
    static boolean flagForFifteenPercentNotification = true;

    public static void sendNotificationBelowTenPercent() {
        if (flagForTenPercentNotification) {
            String content = "SMS was sent to 0700000000 with the message:\n CRITICAL - Your ATM has the one hundred bills stock bellow 10%. Please take care of this situation\n";
            try {
                initialize();
                Files.writeString(fileName, content, StandardOpenOption.APPEND);
            } catch (IOException e) {
                System.out.println(NOTIFICATION_FAILED);
            }
            flagForTenPercentNotification = false;
        }
    }

    public static void sendNotificationBelowTwentyPercent() {
        if (flagForTwentyPercentNotification) {
            String content = "MAIL was sent to laurentiu@mail.com with the message:\n WARNING - Your ATM has the one hundred bills stock bellow 20%. Please take care of this situation\n";
            try {
                initialize();
                Files.writeString(fileName, content, StandardOpenOption.APPEND);
            } catch (IOException e) {
                System.out.println(NOTIFICATION_FAILED);
            }
            flagForTwentyPercentNotification = false;
        }
    }

    public static void sendNotificationBelowFifteenPercent() {
        if (flagForFifteenPercentNotification) {
            String content = "MAIL was sent to laurentiu@mail.com with the message:\n WARNING - Your ATM has the fifty bills stock bellow 15%. Please take care of this situation\n";
            try {
                initialize();
                Files.writeString(fileName, content, StandardOpenOption.APPEND);
            } catch (IOException e) {
                System.out.println(NOTIFICATION_FAILED);
            }
            flagForFifteenPercentNotification = false;
        }
    }

    private static void initialize() throws IOException {
        if (once) {
            Files.writeString(fileName, String.format("This file contains ATM notifications:%n"));
            once = false;
        }
    }
}
