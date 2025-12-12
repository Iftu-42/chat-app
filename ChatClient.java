import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class ChatClient {

    private JFrame frame;
    private JTextArea messageArea;
    private JTextField inputField;
    private JButton sendButton, connectButton;

    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;

    public ChatClient() {
        guiSetup();
    }

    private void guiSetup() {
        frame = new JFrame("Olive Chat Client 🌿");
        frame.setSize(400, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(210, 218, 186));

        messageArea = new JTextArea();
        messageArea.setEditable(false);
        messageArea.setBackground(new Color(236, 240, 224));

        JScrollPane scrollPane = new JScrollPane(messageArea);

        inputField = new JTextField();
        inputField.setBackground(new Color(236, 240, 224));

        sendButton = new JButton("Send");
        sendButton.setBackground(new Color(120, 130, 90));
        sendButton.setForeground(Color.WHITE);

        connectButton = new JButton("Connect");
        connectButton.setBackground(new Color(93, 102, 70));
        connectButton.setForeground(Color.WHITE);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(new Color(210, 218, 186));
        bottomPanel.add(inputField, BorderLayout.CENTER);
        bottomPanel.add(sendButton, BorderLayout.EAST);

        frame.setLayout(new BorderLayout());
        frame.add(connectButton, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        frame.setVisible(true);

        connectButton.addActionListener(e -> connectToServer());
        sendButton.addActionListener(e -> sendMessage());

        inputField.addActionListener(e -> sendMessage());
    }

    private void connectToServer() {
        try {
            socket = new Socket("localhost", 1234);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);

            connectButton.setEnabled(false);
            messageArea.append("Connected to server.\n");

            new Thread(() -> {
                try {
                    String message;
                    while ((message = reader.readLine()) != null) {
                        messageArea.append(message + "\n");
                    }
                } catch (IOException ex) {
                    messageArea.append("Connection lost.\n");
                }
            }).start();

        } catch (IOException e) {
            messageArea.append("Failed to connect.\n");
        }
    }

    private void sendMessage() {
        String msg = inputField.getText();
        if (msg.trim().isEmpty()) return;

        writer.println(msg);
        inputField.setText("");
    }

    public static void main(String[] args) {
        new ChatClient();
    }
}
