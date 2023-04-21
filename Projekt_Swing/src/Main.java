import javax.swing.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new Game();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
