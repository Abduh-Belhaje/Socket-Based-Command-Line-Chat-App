import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static final int PORT = 3334;
    private static final String HOST = "196.117.166.255";

    public static void main(String[] args) {
        try (Socket clientSocket = new Socket(HOST, PORT);
                BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
                Scanner scanner = new Scanner(System.in)) {

            System.out.println("Client Connected Successfully!");

            Thread inputThread = new Thread(() -> handleServerInput(reader, clientSocket));
            Thread outputThread = new Thread(() -> handleClientOutput(scanner, writer, clientSocket));

            inputThread.start();
            outputThread.start();

            inputThread.join();

        } catch (IOException | InterruptedException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void handleServerInput(BufferedReader reader, Socket clientSocket) {
        try {
            String message;
            while (clientSocket.isConnected() && (message = reader.readLine()) != null) {
                System.out.println(message);
            }
        } catch (IOException e) {
            System.out.println("Connection closed.");
        } finally {
            Thread.currentThread().interrupt();
        }
    }

    private static void handleClientOutput(Scanner scanner, PrintWriter writer, Socket clientSocket) {
        try {
            while (clientSocket.isConnected() && scanner.hasNextLine()) {
                String message = scanner.nextLine();
                writer.println(message);
                writer.flush();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            Thread.currentThread().interrupt();
        }

    }
}
