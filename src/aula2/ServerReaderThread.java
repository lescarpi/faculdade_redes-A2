package aula2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ServerReaderThread implements Runnable {

    private Socket socket;
    private BufferedReader serverReader;

    public ServerReaderThread(Socket socket) throws IOException {
        this.socket = socket;
        this.serverReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    @Override
    public void run() {
        try {
            String response;
            while ((response = serverReader.readLine()) != null) {
                System.out.println(response);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

}
