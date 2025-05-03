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
    private final String HELP = "-H";

    private Socket clientSocket = null;
    private BufferedReader in = null;
    private PrintWriter out = null;
    private DataBaseManager dataBaseManager = null;
    private Window window = null;
    private String index = "";

    public ServerThread(Socket clientSocket, DataBaseManager dataBaseManager , Window window) {
        this.clientSocket = clientSocket;
        this.dataBaseManager = dataBaseManager;
        this.window = window;
    }

    @Override
    public void run() {
        index = window.addClient();
        try {
            // creazione stream di input da clientSocket
            InputStreamReader isr = new InputStreamReader(clientSocket.getInputStream());
            in = new BufferedReader(isr);
            // creazione stream di output su clientSocket
            OutputStreamWriter osw = new OutputStreamWriter(clientSocket.getOutputStream());
            BufferedWriter bw = new BufferedWriter(osw);
            out = new PrintWriter(bw, true);
            // ciclo di ricezione dal client e invio di risposta
            out.println("(END to close connection): ");
            out.flush();
            String str;
            while (true) {
                str = in.readLine(); // Reading a string from the client

                if (str == null || str.isEmpty()) {
                    out.println(">: ");
                    out.flush();
                    continue; // If the string is empty, continue the loop
                }

                System.out.println("Da client: " + str);
                this.window.setClientMSG(index,"- Сommands: " + str +"  -> entered by the client: " + clientSocket+ "\n");
              
                String msg = manager(str);
                if (msg != null) {
                    out.println(">: " + msg);
                    out.flush();
                }

                // Ferma il server
                if (str.equals("STOP")) {
                    Main.stopServer();
                    break;
                }

                // Ferma la connessione server con client
                if (str.equals("END")) {
                    out.println("END");
                    break;
                }
            }
            clientSocket.close();
            System.out.println("connection completed: " + clientSocket);
            this.window.setServeStatusMSG("!-Connection completed: " + clientSocket+ "\n");

            this.window.removeClient(index);
        } catch (IOException e) {
            System.err.println("Error during communication with client: " + clientSocket + " - " + e.getMessage());
            this.window.setServeStatusMSG("!-Error during communication with client: " + clientSocket + " - " + e.getMessage() + "\n");
            this.window.removeClient(index);
        }finally {
            try {
                out.close();
                in.close();
                if (clientSocket != null && !clientSocket.isClosed()) clientSocket.close();
            } catch (IOException e) {
                System.err.println("Error closing resources for client: " + clientSocket + " - " + e.getMessage());
            }
        }
    }

    public String manager(String srt) {
        String msg = null;

        String[] n = srt.split(" ");

        switch (n[0]) {
            case MAN:
                msg = "+ Comands: " + GET_TYPE +" returns all data that are the same type "+ " ; " + GET_CATEGORY + " returns all data that are the same category "+" ; " + GET_MUNICIPIO + " returns all data that are the same 'municipio' ";
                break;

            case TEST:
                msg = "TEST MESSEGE";
                break;

            case GET_TYPE:
                if (n.length == 2) {
                    if (!n[1].equals(HELP)) {
                        String dataT = dataBaseManager.findDataByTipologia(n[1]);
                        System.out.println("sending data to the client: " + clientSocket + "\n");
                        this.window.setClientMSG(index,"sending data to the client: " + clientSocket + "\n");
                        // Debugging output
                        // System.out.println(data);
                        msg = dataT;
                    } else {
                        msg = "+ "+GET_TYPE + " P <-(parametr is STRING) Exempl of comand -> "+GET_TYPE + " Residence";
                    }

                } else {
                    msg = "! incomplete command !";
                }

                break;

            case GET_CATEGORY:
                if (n.length == 2) {
                    if (!n[1].equals(HELP)) {
                        String dataC = dataBaseManager.findDataByCategoria(n[1]);
                        System.out.println("sending data to the client: " + clientSocket + "\n");
                        this.window.setClientMSG(index,"sending data to the client: " + clientSocket + "\n");

                        // Debugging output
                        //System.out.println(dataC);
                        msg = dataC;
                    } else {
                        msg = "+ "+GET_CATEGORY + " P <-(parametr is INTEGER) Exempl of comand -> "+ GET_CATEGORY +" 1" ;
                    }
                } else {
                    msg = "! incomplete command !";
                }

                break;

            case GET_MUNICIPIO:
                if (!n[1].equals(HELP)) {
                    if (n.length == 3) {
                        if (!n[1].isEmpty() & !n[2].isEmpty()) {
                            String strM = n[1];
                            String strMEX = n[2];
                            String dataM = dataBaseManager.findDataByMunicipio(strM, strMEX);
                            System.out.println("sending data to the client: " + clientSocket + "\n");
                            this.window.setClientMSG(index,"sending data to the client: " + clientSocket + "\n");
                            // Debugging output
                            // System.out.println(data);
                            msg = dataM;
                        }
                    } else {
                        msg = "! incomplete command !";
                    }
                } else {
                    msg = "+ "+GET_MUNICIPIO
                            + " P1 p2 <-(the first parametr is STRING , the second parametr is STRING) Exempl of comand -> "
                            + GET_MUNICIPIO + " I XVII";
                }
                break;
            default: msg = "";
        }
        return msg;
    }
}
