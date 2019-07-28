/**
 * Java. Level 2; Lesson 4; HomeWork 4
 *
 * @coauthor: Раков Валерий;
 * @version dated: 28 июля 2019 года
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

public class ClientGUI extends JFrame implements ActionListener, Thread.UncaughtExceptionHandler {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;
    private final DateFormat df = DateFormat.getTimeInstance(DateFormat.DEFAULT,
            new Locale("ru", "RU"));
    private final JTextArea log = new JTextArea();
    private final JPanel panelTop = new JPanel(new GridLayout(2, 3));
    private final JTextField tfIPAddress = new JTextField("127.0.0.1");
    private final JTextField tfPort = new JTextField("8189");
    private final JCheckBox cbAlwaysOnTop = new JCheckBox("Always on top");
    private final JTextField tfLogin = new JTextField("ivan");
    private final JPasswordField tfPassword = new JPasswordField("123");
    private final JButton btnLogin = new JButton("Login");
    private final JPanel panelBottom = new JPanel(new BorderLayout());
    private final JButton btnDisconnect = new JButton("<html><b>Disconnect</b></html>");
    private final JTextField tfMessage = new JTextField();
    private final JButton btnSend = new JButton("Send");
    private final JList<String> userList = new JList<>();
    private boolean IOErrorShown;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ClientGUI());
    }
    private ClientGUI() {
        Thread.setDefaultUncaughtExceptionHandler(this);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(WIDTH, HEIGHT);
        setTitle("Chat Client");

        log.setEditable(false);
        log.setLineWrap(true);
        log.setWrapStyleWord(true);
        JScrollPane scrollLog = new JScrollPane(log);
        JScrollPane scrollUsers = new JScrollPane(userList);
        String[] users = {"user1", "user2", "user3", "user4", "user5", "User_with_a_very_long_name"};
        userList.setListData(users);
        scrollUsers.setPreferredSize(new Dimension(100, 0));

        cbAlwaysOnTop.addActionListener(this);
        btnLogin.addActionListener(this);
        btnSend.addActionListener(this);
        tfMessage.addActionListener(this);

        panelTop.add(tfIPAddress);
        panelTop.add(tfPort);
        panelTop.add(cbAlwaysOnTop);
        panelTop.add(tfLogin);
        panelTop.add(tfPassword);
        panelTop.add(btnLogin);

        panelBottom.add(btnDisconnect, BorderLayout.WEST);
        panelBottom.add(tfMessage, BorderLayout.CENTER);
        panelBottom.add(btnSend, BorderLayout.EAST);

        add(panelTop, BorderLayout.NORTH);
        add(panelBottom, BorderLayout.SOUTH);
        add(scrollLog, BorderLayout.CENTER);
        
        add(scrollUsers, BorderLayout.EAST);
        setVisible(true);
    }
/*
    Отправлять сообщения в лог по нажатию кнопки или по нажатию клавиши Enter.
    Создать лог в файле (показать комментарием, где и как Вы планируете писать сообщение в файловый журнал).
    Прочитать методичку к следующему уроку

* */
    private void sendMessage() {
        String user = tfLogin.getText();
        String time = df.format(new Date());
        String msg = tfMessage.getText();
        tfMessage.setText("");
        String message = String.format("%s (%s): %s\n", user, time, msg);
        putLog(message);
        writeLogFile(message);
    }

    private void putLog(String msg) {
        SwingUtilities.invokeLater(() -> {
            log.append(msg);
        });
    }

    private void writeLogFile(String msg) {
        try (FileWriter fileWriter = new FileWriter("log.txt", true)) {
            fileWriter.write(new Date() + " - " + msg);
            fileWriter.flush();
        } catch (IOException e) {
            if (!IOErrorShown) {
                IOErrorShown = true;
                JOptionPane.showMessageDialog(null, "Log file write error",
                        "Exception", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        if (src == cbAlwaysOnTop) {
            setAlwaysOnTop(cbAlwaysOnTop.isSelected());
        } else if (src == btnSend || src == tfMessage) {
            tfMessage.requestFocus();
            if ("".equals(tfMessage.getText())) return;
            sendMessage();
        } else {
            throw new RuntimeException("Unknown source: " + src);
        }
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        e.printStackTrace();
        String msg;
        StackTraceElement[] ste = e.getStackTrace();
        if (ste.length == 0)
            msg = "Empty StackTrace";
        else {
            msg = e.getClass().getCanonicalName() + ": " + e.getMessage() +
                    "\n\t at " + ste[0];
        }
        JOptionPane.showMessageDialog(null, msg, "Exception", JOptionPane.ERROR_MESSAGE);
        System.exit(1);
    }
}