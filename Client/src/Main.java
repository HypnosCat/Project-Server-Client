
import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Main {
    final static String nomeServer = "localhost";
    final static int portaServer = 1050;
    public final static Window window = new Window();
    public static boolean exit = false;
    private static PrintWriter outToServer;
    public static void main(String[] args) {
        System.out.println("Connessione al server in corso...");
        try (Socket sck = new Socket(nomeServer, portaServer)) {
            String rem = sck.getRemoteSocketAddress().toString();
            String loc = sck.getLocalSocketAddress().toString();
            System.out.format("Server (remoto): %s%n", rem);
            System.out.format("Client (client): %s%n", loc);
            initOutToServer(sck);
            window.start();
            startServerListener(sck, window);
            while (!exit) {
                try {
                    Thread.sleep(1000); // Sleep for a short time to prevent busy-waiting
                } catch (InterruptedException e) {
                    // Handle interruption if necessary.  For example, if you want
                    // the program to exit when interrupted.
                    System.err.println("Main thread interrupted: " + e.getMessage());
                    exit = true; // Consider setting exit flag here if appropriate
                }
            }
        } catch (UnknownHostException e) {
            System.err.format("Nome di server non valido: %s%n", e.getMessage());
        } catch (IOException e) {
            System.err.format("Errore durante la comunicazione con il server: %s%n",
                    e.getMessage());
        }
    }

    private static void startServerListener(Socket sck, Window window) {
        new Thread(() -> {
            try (BufferedReader inFromServer = new BufferedReader(new InputStreamReader(sck.getInputStream()))) {
            String response ;
                while (!exit && (response = inFromServer.readLine()) != null) {
                    String [] splitMSG = response.split(" ");

                    if (splitMSG.length > 1 ){
                        if(splitMSG[1].equals("+") || splitMSG[1].equals("!")){
                            window.setCommandLineAreaText(window.getCommandLineAreaText() + "Server: " + response + "\n");
                        }
                        if (splitMSG.length > 2 && splitMSG[2].equals("$")){
                            window.clearTable();
                            window.dataProcessing(response);
                        }
                        if (splitMSG[1].equals("LIST")){
                            String [] msg  = response.split("\\|");
                            for (int i = 1; i < msg.length; i++) {
                                System.out.print(msg[i]);
                                window.setCommandLineAreaText(window.getCommandLineAreaText() + "Server: " + msg[i] + "\n");
                            }
                        }
                    }
                }
                System.out.println("Server listening thread completed.");
            } catch (IOException e) {
                System.err.println("Error reading from server: " + e.getMessage());
            }
        }).start();
    }

    public static void initOutToServer(Socket sck) throws IOException {
        outToServer = new PrintWriter(new OutputStreamWriter(sck.getOutputStream()), true);
    }

    public static void setOutToServer(String msg) {
        if (outToServer != null) {
            outToServer.println(msg);
            outToServer.flush();
        } else {
            System.err.println("Error: outToServer not initialized.");
        }
        if (msg.equalsIgnoreCase("END")){
            exit = true;
        }
    }
}
