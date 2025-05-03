import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window extends Thread {
    public final int ALTEZZA = 600;
    public final int LUNGEZZA = 800;
    public JTextArea comandLineArea = new JTextArea(); // Перейменуйте для уникнення плутанини
    private JTextField commandInput; // Замінюємо JButton на JTextField
    private JFrame f = new JFrame();
    private static final String [] tableObjects = {"Municipio" ,"Tipologia" ,"Classificazione" ,
                                        "Denominazione" , "Indirizzo" , "Singole",
                                        "Doppie" , "Triple","Quadruple","Quintuple",
                                        "Sestuple","Totale camere" ,"Posti letto" ,"Unita abitative" ,
                                        "Posti letto unita abitative"};

    private JTable dataTable; // Замінюємо JTextArea на JTable
    private DefaultTableModel tableModel; // Модель таблиці

    public Window() {}

    public JFrame getFrame() { // Додаємо геттер для отримання JFrame
        return f;
    }

    @Override
    public void run() {
        windowDraw();
    }

    public void windowDraw() {
        f.setSize(LUNGEZZA, ALTEZZA);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
        f.setLayout(new BorderLayout());

        f.add(commandLine(), BorderLayout.WEST);
        f.add(table(),BorderLayout.CENTER);

        f.setVisible(true);
    }

    public JPanel commandLine() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JScrollPane scrollPane = new JScrollPane(comandLineArea);

        comandLineArea.setEditable(false);
        comandLineArea.setLineWrap(true);
        comandLineArea.setWrapStyleWord(true);
        scrollPane.setPreferredSize(new Dimension(300, 400)); // Збільшуємо висоту JTextArea

        commandInput = new JTextField(); // Створюємо JTextField
        commandInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String command = commandInput.getText();// Отримуємо текст з JTextField
                if (command.equals("END")){
                    Main.exit = true;
                    f.dispose();
                }
                System.out.println("command: " + command);

                // Тут ви можете передати команду для обробки
                Main.setOutToServer(command);
                commandInput.setText(""); // Очищаємо JTextField після виконання (за бажанням)
            }
        });

        panel.add(scrollPane, BorderLayout.CENTER); // Розміщуємо JTextArea зверху
        panel.add(commandInput, BorderLayout.SOUTH); // Розміщуємо JTextField знизу
        panel.setBorder(BorderFactory.createTitledBorder("Command Line"));
        return panel;
    }

    public JPanel table() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout()); // Рекомендується використовувати BorderLayout для розміщення JScrollPane

        // Створюємо модель таблиці з заголовками стовпців
        tableModel = new DefaultTableModel(tableObjects, 0); // 0 - початкова кількість рядків

        // Створюємо JTable з моделлю таблиці
        dataTable = new JTable(tableModel);
        dataTable.setFillsViewportHeight(true); // Забезпечує заповнення видимої області прокрутки

        // Створюємо JScrollPane та додаємо до нього JTable
        JScrollPane scrollPane = new JScrollPane(dataTable);
        scrollPane.setPreferredSize(new Dimension(450, 500)); // Встановлюємо бажаний розмір прокрутки

        panel.setBorder(BorderFactory.createTitledBorder("Table"));
        panel.add(scrollPane, BorderLayout.CENTER); // Додаємо прокрутку до панелі
        return panel;
    }

    public void dataProcessing(String response){
        String [] records = response.split("&");
        for (int i = 1; i < records.length; i++) { // Починаємо з 1, якщо перший елемент містить щось, що не є даними
            String record = records[i];
            String[] values = record.split(",");
            addRowToTable(values); // Передаємо одновимірний масив значень для одного рядка
            System.out.println("Додано рядок до таблиці: " + java.util.Arrays.toString(values));
        }
    }

    // Метод для додавання рядка даних до таблиці
    public void addRowToTable(Object[] rowData) {
        if (tableModel != null) {
            tableModel.addRow(rowData);
        }
    }

    // Метод для очищення всіх рядків з таблиці
    public void clearTable() {
        if (tableModel != null) {
            tableModel.setRowCount(0);
        }
    }

    // Метод для отримання тексту з області команд (JTextArea)
    public String getCommandLineAreaText() {
        return comandLineArea.getText();
    }

    // Метод для встановлення тексту в область команд (JTextArea)
    public void setCommandLineAreaText(String text) {
        comandLineArea.setText(text);
    }

    // Метод для отримання тексту з поля введення команди (JTextField)
    public String getCommandInputText() {
        return commandInput.getText();
    }

    // Метод для встановлення тексту в поле введення команди (JTextField)
    public void setCommandInputText(String text) {
        commandInput.setText(text);
    }

}