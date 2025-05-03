import javax.swing.*;
import java.awt.*;

class Client {
    private JTextArea textArea;

    public Client(int id) {
        this.textArea = new JTextArea();
        this.textArea.setEditable(false);
        this.textArea.setLineWrap(true);
        this.textArea.setWrapStyleWord(true);
        //this.textArea.setBackground(Color.LIGHT_GRAY);
    }

    public JTextArea getTextArea() {
        return textArea; // This method returns the JTextArea for this client
    }
}