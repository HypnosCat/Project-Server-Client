import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerThread extends Thread {
    private final String GET_TYPE = "GET_T";
    private final String GET_CATEGORY = "GET_C";
    private final String GET_MUNICIPIO = "GET_M";
    private final String TEST = "TEST";
    private final String MAN = "MAN";

    private Socket clientSocket = null;
    private BufferedReader in = null;
    private PrintWriter out = null;
    private DataBaseManager dataBaseManager = null;

    public ServerThread(Socket clientSocket, DataBaseManager dataBaseManager) {
        this.clientSocket = clientSocket;
        this.dataBaseManager = dataBaseManager;
    }

    @Override
    public void run() {
        try {
            // creazione stream di input da clientSocket
            InputStreamReader isr = new InputStreamReader(clientSocket.getInputStream());
            in = new BufferedReader(isr);
            // creazione stream di output su clientSocket
            OutputStreamWriter osw = new OutputStreamWriter(clientSocket.getOutputStream());
            BufferedWriter bw = new BufferedWriter(osw);
            out = new PrintWriter(bw, true);
            // ciclo di ricezione dal client e invio di risposta
            out.print("(END to close connection): ");
            out.flush();

            while (true) {
                String msg = "";
                out.print(">: " + msg + "\n");
                out.flush();

                String str = in.readLine();
                System.out.println("Da client: " + str);

                msg = manager(str);
                if (msg != null) {
                    out.print(">: " + msg + "\n");
                    out.flush();
                }
                // ferma il server
                if (str.equals("STOP")) {
                    Main.stopServer();
                    break;
                }

                // ferma il conesione server con client
                if (str.equals("END")) {
                    out.print("END");
                    break;
                }
            }
            clientSocket.close();
            out.close();
            in.close();
            System.out.println("connection completed: " + clientSocket);
        } catch (IOException e) {
            System.err.println("Accept failed");
            System.exit(1);
        }
    }

    public String MSG(String str) {
        String msg = "";
        if (str.equals("TEST")) {
            msg = "TEST MESSAGE";
        }
        return msg;
    }

    public String manager(String srt) {
        String msg = null;

        String[] n = srt.split(" ");

        switch (n[0]) {
            case MAN:
                msg = "Comands: " + GET_TYPE + " ; " + GET_CATEGORY + " ; " + GET_MUNICIPIO;
                break;

            case TEST:
                msg = "TEST MESSEGE";
                break;

            case GET_TYPE:
                if (n.length == 2) {
                    if (!n[1].isEmpty()) {
                        String dataT = dataBaseManager.findDataByTipologia(n[1]);
                        System.out.println("sending data to the client: " + clientSocket + "\n");
                        // Debugging output
                        // System.out.println(data);
                        msg = dataT + "\n";
                    }
                }else{
                    msg = "incomplete command !";
                }

                break;

            case GET_CATEGORY:
                if (n.length == 2) {
                    if (!n[1].isEmpty()) {
                        String dataC = dataBaseManager.findDataByCategoria(n[1]);
                        System.out.println("sending data to the client: " + clientSocket + "\n");
                        // Debugging output
                        // System.out.println(data);
                        msg = dataC + "\n";
                    }
                }else{
                    msg = "incomplete command !";
                }

                break;

            case GET_MUNICIPIO:
                if (n.length == 3) {
                    if (!n[1].isEmpty() & !n[2].isEmpty()) {
                        String strM = n[1];
                        String strMEX = n[2];
                        String dataM = dataBaseManager.findDataByMunicipio(strM, strMEX);
                        System.out.println("sending data to the client: " + clientSocket + "\n");
                        // Debugging output
                        // System.out.println(data);
                        msg = dataM + "\n";
                    }
                }else{
                    msg = "incomplete command !";
                }

                break;
        }
        return msg;
    }
}
