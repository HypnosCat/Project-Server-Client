import java.io.*;
import java.net.*;

public class Main {

    public static final int PORT = 1050; // porta al di fuori del range 1-1024 !
    private static boolean exit = false;
    public static ServerThread serverThread;
    public static DataBaseManager dataBaseManager = new DataBaseManager();

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */

    public static void main(String[] args) throws IOException {
        initDataBase();

        //System.out.println(dataBaseManager.findDataByTipologia("Guest House o Affittacamere"));
        
        try (  ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("EchoServer: started ");
            System.out.println("Server Socket: " + serverSocket);

            Socket clientSocket=null;

            while (!exit){
                try {
                    // bloccante finchè non avviene una connessione
                    clientSocket = serverSocket.accept();
                    System.out.println("Connection accepted: "+ clientSocket);
                    serverThread = new ServerThread(clientSocket , dataBaseManager);
                    serverThread.start();
                }
                catch (IOException e) {
                    System.err.println("Accept failed");
                    System.exit(1);
                }
            }
                        // chiusura di stream e socket
            System.out.println("EchoServer: closing...");
        } 
    }

    public static synchronized void stopServer() {
        exit = true;
        System.out.println("Stop server");
    }

    public static void initDataBase() throws IOException{
        dataBaseManager.initDataBase();
    }
}

