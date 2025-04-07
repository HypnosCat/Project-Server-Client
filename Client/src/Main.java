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

    public static void main(String[] args) {
        System.out.println("Connessione al server in corso...");
        try (Socket sck = new Socket(nomeServer, portaServer)) {
            String rem = sck.getRemoteSocketAddress().toString();
            String loc = sck.getLocalSocketAddress().toString();
            System.out.format("Server (remoto): %s%n", rem);
            System.out.format("Client (client): %s%n", loc);
            comunica(sck);
        } catch (UnknownHostException e) {
            System.err.format("Nome di server non valido: %s%n", e.getMessage());
        } catch (IOException e) {
            System.err.format("Errore durante la comunicazione con il server: %s%n",
                    e.getMessage());
        }
    }

    public static void comunica(Socket sck) throws IOException {
        boolean exit = false;
        BufferedReader in = new BufferedReader(new InputStreamReader(sck.getInputStream()));
        PrintWriter out = new PrintWriter(new OutputStreamWriter(sck.getOutputStream()), true);
        Scanner s = new Scanner(System.in, "UTF-8");
        String response = "";
        do {
            response = in.readLine();
            System.out.print(response);
            String comand = s.nextLine();
            // System.out.format("Invio al server: %s%n" + "\n", comand);
            out.println(comand);
            out.flush();
            
            if (comand.equals("END")) {
                exit = true;
            }
        } while (!exit);
        s.close();
    }
}