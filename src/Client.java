import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

    public class Client extends JFrame implements Runnable {
    private static Socket connection;
    private static ObjectOutputStream output;
    private static ObjectInputStream intput;

    public static void main(String[] args) {
        new Thread(new Client("Client Voloshyn-Avto")).start();
    }

    public Client(String name) {
        super(name);
        setLayout(new FlowLayout());
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);

        final JTextField txtFld = new JTextField(10);
        final JButton btnSend = new JButton("Send");
        btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if  (e.getSource() == btnSend) sendData(txtFld.getText());
            }
        });
        add(txtFld);
        add(btnSend);
    }


    private static void sendData(Object obj) {
        try{
            output.flush();
            output.writeObject(obj);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "server not found");
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println("run");
                connection = new Socket(InetAddress.getByName("localhost"), 5678);
                output = new ObjectOutputStream(connection.getOutputStream());
                intput = new ObjectInputStream(connection.getInputStream());
                JOptionPane.showMessageDialog(null, (String)intput.readObject());
            }
            catch (Exception e) {
                System.out.println("coonection failed");
            }
        }
    }
}
