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
    private final String FIND_KEYWORD = "FIND";
    private final String GET_LIST_T = "GET_LT";
    private final String GET_LIST_C = "GET_LC";
    private final String GET_LIST_M = "GET_LM";
    private final String TEST = "TEST";
    private final String MAN = "MAN";
    private final String HELP = "H";

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
                if (str.equalsIgnoreCase("STOP")) {
                    Main.stopServer();
                    break;
                }

                // Ferma la connessione server con client
                if (str.equalsIgnoreCase("END")) {
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
    
            String[] n = srt.split("-");
            String command = n[0].toUpperCase();
            switch (command) {
                case MAN:
                    msg = "+ Comands: "
                            + GET_TYPE +"- returns all data that are the same type "+ " ; "
                            + GET_CATEGORY + "- returns all data that are the same category "+" ; "
                            + GET_MUNICIPIO + "- returns all data that are the same 'municipio' "+" ; "
                            + GET_LIST_T +"- returns all Type name "+" ; "
                            + GET_LIST_C +"- returns all  Category name "+" ; "
                            + GET_LIST_M +"- returns all 'municipio' name "+" ; "
                            + FIND_KEYWORD +"- returns all all data are the same sequence of char"+" ; ";
                    break;
    
                case TEST:
                    msg = "TEST MESSEGE";
                    break;
    
                case GET_TYPE:
                    if (n.length == 2) {
                        if (!n[1].equalsIgnoreCase(HELP)) {
                            String dataT = dataBaseManager.findDataByTipologia(n[1]);
                            System.out.println("sending data to the client: " + clientSocket + "\n");
                            this.window.setClientMSG(index,"sending data to the client: " + clientSocket + "\n");
                            // Debugging output
                            // System.out.println(data);
                            msg = dataT;
                        } else {
                            msg = "+ "+GET_TYPE + "-P <-(parametr is STRING) Exempl of comand -> "+GET_TYPE + "-Residence";
                        }
    
                    } else {
                        msg = "! incomplete command !";
                    }
    
                    break;
    
                case GET_CATEGORY:
                    if (n.length == 2) {
                        if (!n[1].equalsIgnoreCase(HELP)) {
                            String dataC = dataBaseManager.findDataByCategoria(n[1]);
                            System.out.println("sending data to the client: " + clientSocket + "\n");
                            this.window.setClientMSG(index,"sending data to the client: " + clientSocket + "\n");
    
                            // Debugging output
                            System.out.println(dataC);
                            msg = dataC;
                        } else {
                            msg = "+ "+GET_CATEGORY + "-P <-(parametr is String) Exempl of comand -> "+ GET_CATEGORY +"-Categoria 1 or Unica" ;
                        }
                    } else {
                        msg = "! incomplete command !";
                    }
    
                    break;
    
                case GET_MUNICIPIO:
                    String[] p = n[1].split(" ");
                    if (!n[1].equalsIgnoreCase(HELP)) {
                        if (p.length == 2) {
                            if (!p[0].isEmpty() & !p[1].isEmpty()) {
                                String strM = p[0];
                                String strMEX = p[1];
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
                                + "-P1 p2 <-(the first parametr is STRING , the second parametr is STRING) Exempl of comand -> "
                                + GET_MUNICIPIO + "-I XVII";
                    }
                    break;
                case FIND_KEYWORD:
                    if (n.length == 2) {
                        if (!n[1].equalsIgnoreCase(HELP)) {
                            String dataT = dataBaseManager.findDataByKeyword(n[1]);
                            System.out.println("sending data to the client: " + clientSocket + "\n");
                            this.window.setClientMSG(index,"sending data to the client: " + clientSocket + "\n");
                            // Debugging output
                            // System.out.println(data);
                            msg = dataT;
                        } else {
                            msg = "+ "+FIND_KEYWORD + "-P <-(parametr is STRING) Exempl of comand -> "+FIND_KEYWORD + "-ost";
                        }
    
                    } else {
                        msg = "! incomplete command !";
                    }
                    break;

            case GET_LIST_T:
                String dataT = dataBaseManager.getListOfT();
                System.out.println("sending data to the client: " + clientSocket + "\n");
                this.window.setClientMSG(index,"sending data to the client: " + clientSocket + "\n");
                msg = dataT;
                break;

            case GET_LIST_C:
                String dataC = dataBaseManager.getListOfC();
                System.out.println("sending data to the client: " + clientSocket + "\n");
                this.window.setClientMSG(index,"sending data to the client: " + clientSocket + "\n");
                msg = dataC;
                break;

            case GET_LIST_M:
                String dataM = dataBaseManager.getListOfM();
                System.out.println("sending data to the client: " + clientSocket + "\n");
                this.window.setClientMSG(index,"sending data to the client: " + clientSocket + "\n");
                msg = dataM;
                break;
            default: msg = "";
        }
        return msg;
    }
}
