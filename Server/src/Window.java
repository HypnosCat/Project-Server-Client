import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.*;


public class Window extends Thread {
    public final int ALTEZZA = 800;
    public final int LUNGEZZA = 1000;
    public JTextArea ServerStatusMSG = new JTextArea();
    public JTabbedPane clientTabbedPane = new JTabbedPane();
    private int clientCount = 0; // To keep track of the number of clients

    public Window() {}

    @Override
    public void run() {
        windowDraw();
    }

    public void windowDraw() {
        JFrame f = new JFrame();
        f.setSize(LUNGEZZA, ALTEZZA);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
        f.setLayout(new BorderLayout());

        f.add(ServerStatus(), BorderLayout.WEST);
        f.add(clientTabbedPane, BorderLayout.CENTER); // Use the tabbed pane directly

        f.setVisible(true);
    }

    public JPanel ServerStatus() {
        JPanel panel = new JPanel();
        JScrollPane scrollPane = new JScrollPane(ServerStatusMSG);

        ServerStatusMSG.setEditable(false);
        ServerStatusMSG.setLineWrap(true);
        ServerStatusMSG.setWrapStyleWord(true);
        ServerStatusMSG.setBackground(Color.LIGHT_GRAY);
        ServerStatusMSG.setPreferredSize(new Dimension(500, 1000));

        scrollPane.setPreferredSize(new Dimension(440, 200));
        panel.setBorder(BorderFactory.createTitledBorder("Server Status"));
        panel.add(scrollPane);
        return panel;
    }

    // Method to add a new client
    public int addClient() {
        clientCount++; // Increment the client count
        JTextArea clientTextArea = new JTextArea();
        clientTextArea.setEditable(false);
        clientTextArea.setLineWrap(true);
        clientTextArea.setWrapStyleWord(true);
        clientTextArea.setBackground(Color.LIGHT_GRAY);

        JScrollPane scrollPane = new JScrollPane(clientTextArea);
        scrollPane.setPreferredSize(new Dimension(500, ALTEZZA));

        // Create a new tab for the client
        clientTabbedPane.addTab("Client " + clientCount, scrollPane);
        return  clientCount-1;
    }

    public void setServeStatusMSG(String msg) {
        ServerStatusMSG.append(msg + "\n");
    }

    public void setClientMSG(int clientIndex, String msg) {
        // Assuming clientIndex is 0-based
        JTextArea clientTextArea = (JTextArea) ((JScrollPane) clientTabbedPane.getComponentAt(clientIndex)).getViewport().getView();
        clientTextArea.append(msg + "\n");
    }

    public void removeTabbedPane(int index) {
        if (index >= 0 && index < clientTabbedPane.getTabCount()) {
            clientTabbedPane.removeTabAt(index); // Remove the tab at the specified index
        } else {
            System.out.println("Invalid index: " + index);
        }
    }
}