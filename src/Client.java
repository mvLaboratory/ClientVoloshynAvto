import javax.swing.*;
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

    }

    private static void sendData(Object obj) {
        try{
            output.flush();
            output.writeObject(obj);
        } catch (IOException e) {
            System.out.println("output fuck");  }
    }

    @Override
    public void run() {
        try {
            while (true) {
                connection = new Socket(InetAddress.getByName("localhost"), 5678);
                output = new ObjectOutputStream(connection.getOutputStream());
                intput = new ObjectInputStream(connection.getInputStream());
                JOptionPane.showMessageDialog(null, (String)intput.readObject());
            }
        } catch (Exception e) {
            System.out.println("fuck");}
    }
}
