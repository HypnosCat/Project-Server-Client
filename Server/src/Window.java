import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class Window extends Thread{
    public final int ALTEZZA = 600;
    public final int LUNGEZZA = 800;
    public String msg;
    public  JTextArea textArea = new JTextArea();

    @Override
    public void run() {
        windowDraw();
    }

    public void windowDraw(){
        JFrame f = new JFrame();
        f.setSize(LUNGEZZA, ALTEZZA); 
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
        
        // Використовуємо BorderLayout для JFrame
        f.setLayout(new BorderLayout());

        JPanel panelEst = new JPanel();
        panelEst.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        //panel.setBackground(Color.GRAY);
        
        // Створюємо JTextArea для відображення тексту
       
        textArea.setEditable(false); // Зробити текстове поле тільки для читання
        textArea.setLineWrap(true); // Увімкнути перенесення рядків
        textArea.setWrapStyleWord(true); // Переносити за словами
        textArea.setBackground(Color.LIGHT_GRAY);
        
        // Встановлюємо фіксовані розміри для JTextArea
        textArea.setPreferredSize(new Dimension(100, 1000)); 

        // Обертаємо JTextArea в JScrollPane
        JScrollPane scrollPaneEst = new JScrollPane(textArea);
        scrollPaneEst.setPreferredSize(new Dimension(350, 100)); // Встановлюємо розміри для JScrollPane

        // Додаємо JScrollPane до JPanel
        panelEst.add(scrollPaneEst);

        // Додаємо JPanel до JFrame
        f.add(panelEst, BorderLayout.EAST);

        // Додаємо деякий текст для демонстрації
        textArea.append(msg + ".\n");
        f.setVisible(true); 
    }

    public void SetMesenge(String masenge){
       textArea.append(masenge+"\n");
    }

}