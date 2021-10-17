import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private static final int SERVER_PORT = 5000;

    public static void log(String message) {
        System.out.println("[S]: " + message);
    }

    public static void main(String[] args) throws IOException {
        log("Server starting");
        log("Opening server socket");
        ServerSocket welcomeSocket = new ServerSocket(SERVER_PORT);
        log("Server socket opened");


        log("Server listening");
        Socket clientSocket = welcomeSocket.accept();
        String clientIP = clientSocket.getInetAddress().toString();
        int clientPort = clientSocket.getPort();
        log("Client connected from " + clientIP + ":" + clientPort);

        InputStream is = clientSocket.getInputStream();
        OutputStream os = clientSocket.getOutputStream();
        InputStreamReader isr = new InputStreamReader(is);
        OutputStreamWriter osw = new OutputStreamWriter(os);
        BufferedReader br = new BufferedReader(isr);
        BufferedWriter bw = new BufferedWriter(osw);
        log("Stream collected");


        String request = br.readLine();
        boolean login = false;
        log("Request: " + request);
        if("LOGIN ALA".equals(request)) {
            login = true;
            bw.write("ACK");
            bw.newLine();
            bw.flush();
        } else {
            bw.write("NAK");
            bw.newLine();
            bw.flush();
        }

        request = br.readLine();
        log("Request: " + request);

        if("password".equals(request) && login) {
            bw.write("ACK");
            bw.newLine();
            bw.flush();
        } else {
            bw.write("NAK");
            bw.newLine();
            bw.flush();
        }









        log("Closing client socket");
        clientSocket.close();
        log("Client socket closed");

        log("Closing server socket");
        welcomeSocket.close();
        log("Server socket closed");
        log("Server ends");
    }
}
