import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class Client {

    private static final String SERVER_NAME = "localhost";
    private static final int SERVER_PORT = 5000;

    public static void log(String message) {
        System.out.println("[C]: " + message);
    }

    public static void main(String[] args) throws IOException {
        log("Starting");
        InetAddress serverAddress = InetAddress.getByName(SERVER_NAME);
        log("Server address: " + serverAddress.toString());
        log("Connecting to the server: " + serverAddress.toString());
        Socket clientSocket = new Socket(serverAddress, SERVER_PORT);
        log("Connected to the server: " + serverAddress.toString());

        InputStream is = clientSocket.getInputStream();
        OutputStream os = clientSocket.getOutputStream();
        InputStreamReader isr = new InputStreamReader(is);
        OutputStreamWriter osw = new OutputStreamWriter(os);
        BufferedReader br = new BufferedReader(isr);
        BufferedWriter bw = new BufferedWriter(osw);
        log("Stream collected");

        bw.write("LOGIN ALA");
        bw.newLine();
        bw.flush();

        String response = br.readLine();
        log("Response" + response);

        bw.write("password");
        bw.newLine();
        bw.flush();

        response = br.readLine();
        log("Response" + response);

        log("Client port closing");
        clientSocket.close();
        log("Client port closed");

        log("Ends");
    }
}
