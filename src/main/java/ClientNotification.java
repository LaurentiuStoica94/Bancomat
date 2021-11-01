import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class ClientNotification {
    static Path fileName = Path.of("ClientNotifications.txt");
    static boolean once = true;

    public static void sendNotification(int amount) {
        String content = String.format("The amount %s was withdrawn. If you do not recognize this operation please contact the bank %n", amount);
        try {
            if (once) {
                Files.writeString(fileName, String.format("This file contains client notifications:%n"));
                once = false;
            }
            Files.writeString(fileName, content, StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("Notification was not successful");
        }
    }
}
