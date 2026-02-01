package iprody.nonblockmultisidereciever;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
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
    private SocketChannel socketChannel;
    private Thread repeater = null;
    private Selector selector;
    
    // GUI variables
    private final int number;
    private final int position_X;
    private final int position_Y;
    private final int width;
    private final int height;

    public EchoClient(int number, int X, int Y, int width, int height) {
        try {
            openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.number = number;
        this.position_X = X;
        this.position_Y = Y;
        this.width = width;
        this.height = height;
        prepareGUI();
        repeater = new Thread( () -> {
            ClientRepeater();
        });
        repeater.start();
    }

    private void openConnection() throws IOException {
        System.out.println("Client has started, wating for connection...");
        this.selector = Selector.open();
        this.socketChannel = SocketChannel.open();
        this.socketChannel.configureBlocking(false);
        this.socketChannel.register(selector, SelectionKey.OP_READ);
        if (!this.socketChannel.connect(new InetSocketAddress(SERVER_ADDR,  SERVER_PORT))){
            this.socketChannel.finishConnect();
        }
        System.out.println("Client connected to server.");
    }

    private void ClientRepeater(){
        try {
            Thread currentThread = Thread.currentThread();
            ByteBuffer buffer = ByteBuffer.allocate(128);
            while (!currentThread.isInterrupted()) {
                if (selector.select() == 0){
                    continue;
                }
                Iterator<SelectionKey> keys = selector.selectedKeys().iterator();
                while (keys.hasNext()){
                    SelectionKey key = keys.next();
                    keys.remove();
                    if (key.isReadable()){
                        try {
                            SocketChannel clientChannel = (SocketChannel) key.channel();
                            StringBuilder message = new StringBuilder();
                            int readedBytes = clientChannel.read(buffer);
                            if (readedBytes > 0){
                                do {
                                    buffer.flip();
                                    byte[] data = new byte[buffer.limit()];
                                    buffer.get(data);
                                    message.append(new String(data));
                                    buffer.clear();
                                } while (clientChannel.read(buffer) > 0);
                                chatArea.append(message.toString());
                                chatArea.append("\n");
                                if (!message.toString().startsWith("Echo: ")) {
                                    message.insert(0, "Echo: ");
                                    clientChannel.write(ByteBuffer.wrap(message.toString().getBytes()));
                                }
                            }/* else if (readedBytes == -1) {
                                clientChannel.close();
                            }*/
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                    
                }
            }    
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
        
    private void closeConnection() {
        try {
            this.socketChannel.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendMessage() {
        if (!msgInputField.getText().trim().isEmpty()) {
            try {
                ByteBuffer buffer = ByteBuffer.wrap(msgInputField.getText().getBytes());
                this.socketChannel.write(buffer);
                msgInputField.setText("");
                msgInputField.grabFocus();
            } catch (IOException ex ) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Ошибка отправки сообщения");
            }
        }
    }
  
    private void prepareGUI() {
    // Параметры окна
        setBounds(this.position_X, this.position_Y, this.width, this.height);
        setTitle("Неблокирующий клиент №" + this.number);
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
                repeater.interrupt();
                try {
                    socketChannel.write(ByteBuffer.wrap(("WARNING!!! Client №" + number + " closed!").getBytes()));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                closeConnection();
                System.out.println("WARNING!!! Client №" + number + " closed!");
            }
        });
        setVisible(true);
    }
        
}
