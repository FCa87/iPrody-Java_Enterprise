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
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class EchoServer extends JFrame{

    // Variables for Server inner work
    private final ArrayList<SocketChannel> clientChannels;
    private final int PORT = 7;
    private Thread serverWorks = null;
    private ServerSocketChannel serverChannel;
    private SocketChannel clientChannel;
    private Selector selector;
    
    // GUI variables
    private JTextField msgInputField;
    private JTextArea chatArea;
    private final int position_X;
    private final int position_Y;
    private final int width;
    private final int height;
    
    public EchoServer(int X, int Y, int width, int height){
        serverWorks = new Thread( () -> {
            serverLaunch();
        });
        serverWorks.start();    
        clientChannels = new ArrayList<>();
        this.position_X = X;
        this.position_Y = Y;
        this.width = width;
        this.height = height;
        prepareGUI();
    }
    
    private void serverLaunch(){
        try{
            serverChannel = ServerSocketChannel.open();
            selector = Selector.open();
            
            serverChannel.bind(new InetSocketAddress(this.PORT));
            serverChannel.configureBlocking(false);
            serverChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("Server has started, wating for connection...");
            
            Thread currentThread = Thread.currentThread();
            ByteBuffer buffer = ByteBuffer.allocate(128);
            while(!currentThread.isInterrupted()){
                if (selector.select() == 0){
                    continue;
                }
                Iterator<SelectionKey> keys = selector.selectedKeys().iterator();
                while (keys.hasNext()){
                    SelectionKey key = keys.next();
                    keys.remove();
                    if (key.isAcceptable()) {
                        System.out.println("Client tries to connect...");

                        ServerSocketChannel srvChannel = (ServerSocketChannel) key.channel();
                        clientChannel = srvChannel.accept();
                        clientChannel.configureBlocking(false);

                        clientChannel.register(selector, SelectionKey.OP_READ);
                        System.out.println("Client has connected succesfully.");
                        clientChannels.add(clientChannel);
                    } else if (key.isReadable()){
                        SocketChannel clientChannel = (SocketChannel) key.channel();
                        StringBuilder message = new StringBuilder();
                        int readedBytes = clientChannel.read(buffer);
                        if (readedBytes > 0) {
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
                        }
                    }
                }
            }   
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            try {
                serverChannel.close();
                selector.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public void closeConnection() {
        try{
            for (SocketChannel currentClientChannel : clientChannels){
                currentClientChannel.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage() {
        if (!msgInputField.getText().trim().isEmpty()) {
            try {
                for (SocketChannel currentClientChannel : this.clientChannels){
                    currentClientChannel.write(ByteBuffer.wrap(msgInputField.getText().getBytes()));
                }
                
                
            } catch (IOException ex) {
                System.getLogger(EchoServer.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
            msgInputField.setText("");
            msgInputField.grabFocus();
        }
    }
  
    private void prepareGUI() {
    // Параметры окна
        setBounds(this.position_X, this.position_Y, this.width, this.height);
        setTitle("Неблокирующий сервер");
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
                serverWorks.interrupt();
                for (SocketChannel currentClientChannel : clientChannels){
                    try {
                        currentClientChannel.write(ByteBuffer.wrap("WARNING!!! Server closed!".getBytes()));
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
                closeConnection();
                System.out.println("WARNING!!! Server closed!");
            }
        });
        setVisible(true);
    }
    
}
