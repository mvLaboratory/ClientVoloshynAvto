import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Connector {
    Socket fromServer = null;
    BufferedReader serverIn;
    PrintWriter ServerOut;
    String serverIP;
    private Boolean connected;

    public Connector(String serverIP) throws IOException  {
        this.serverIP = serverIP;
        connect();
    }

    private void connect() throws IOException {
        try {
            fromServer = new Socket(serverIP, 4444);
            serverIn = new BufferedReader(new InputStreamReader(fromServer.getInputStream()));
            ServerOut = new PrintWriter(fromServer.getOutputStream(), true);

            connected = true;
        }
        catch (Exception e) {
            connected = false;  }
    }

    public void check() throws IOException  {
        ServerOut.println("check");
        if (!(ServerOut != null &!ServerOut.equals("catch"))) connected = false;
        else System.out.println("ping");
    }

    public void send(String message) {
        ServerOut.println(message);
    }

    public void dispose() throws IOException {
        ServerOut.close();
        serverIn.close();
        fromServer.close();

        connected = false;
    }

    public Boolean connected() {
        return connected;
    }
}
