package doublesidereciever;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class EchoClient extends JFrame {

    private final String SERVER_ADDR = "127.0.0.1";
    private final int SERVER_PORT = 7;
    private JTextField msgInputField;
    private JTextArea chatArea;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private final Object monitorIn = new Object();
    private final Object monitorOut = new Object();
    private Thread repeater = null;

    public EchoClient() {
        try {
            openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        prepareGUI();
    }

    public void openConnection() throws IOException {
        System.out.println("Client has started, wating for connection...");
        socket = new Socket(SERVER_ADDR, SERVER_PORT);
        System.out.println("Server connected");
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());
        repeater = new Thread( () -> {
            ClientRepeater();
        });
        repeater.start();
    }

    private void ClientRepeater(){
        try {
            Thread currentThread = Thread.currentThread();
            while (!currentThread.isInterrupted()) {
                String str = null;
                synchronized (monitorIn) {
                    str = in.readUTF();
                    chatArea.append(str);
                    chatArea.append("\n");
                }
                if (!str.startsWith("Echo: ")) {
                    synchronized (monitorOut) {
                        out.writeUTF("Echo: " + str);
                    }
                }
            }    
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public void closeConnection() {
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            out.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
        try {
            socket.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage() {
        if (!msgInputField.getText().trim().isEmpty()) {
            try {
                synchronized (monitorOut) {
                    out.writeUTF(msgInputField.getText());
                }
                msgInputField.setText("");
                msgInputField.grabFocus();
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Ошибка отправки сообщения");
            }
        }
    }
  
    public void prepareGUI() {
    // Параметры окна
        setBounds(200, 200, 500, 500);
        setTitle("Клиент");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    // Текстовое поле для вывода сообщений
        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setLineWrap(true);
        add(new JScrollPane(chatArea), BorderLayout.CENTER);
    // Нижняя панель с полем для ввода сообщений и кнопкой отправки сообщений
        JPanel bottomPanel = new JPanel(new BorderLayout());
        JButton btnSendMsg = new JButton("Отправить");
        bottomPanel.add(btnSendMsg, BorderLayout.EAST);
        msgInputField = new JTextField();
        add(bottomPanel, BorderLayout.SOUTH);
        bottomPanel.add(msgInputField, BorderLayout.CENTER);
        btnSendMsg.addActionListener((ActionEvent e) -> {
            sendMessage();
        });
        msgInputField.addActionListener((ActionEvent e) -> {
            sendMessage();
        });
    // Настраиваем действие на закрытие окна
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                try {
                    repeater.interrupt();
                    synchronized (monitorOut) {
                        out.writeUTF("WARNING!!! Client closed!");
                    }
                    closeConnection();
                    System.out.println("WARNING!!! Client closed!");
                } catch (IOException exc) {
                    exc.printStackTrace();
                }
            }
        });
        setVisible(true);
    }
        
}
