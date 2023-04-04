package aula2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ServerThread extends Thread {
    private Socket socket;
    private ArrayList<ServerThread> threadList;
    private PrintWriter output;

    public ServerThread(Socket socket, ArrayList<ServerThread> threadList) {
        this.socket = socket;
        this.threadList = threadList;
    }

    @Override
    public void run() {
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            output = new PrintWriter(socket.getOutputStream(), true);

            String ip = socket.getInetAddress().getHostAddress();
            System.out.println("Connection established from: " + ip);

            while (true) {
                String outputString = input.readLine();

                if(outputString.equals("tchau")) {
                    break;
                }

                printToAllClients(outputString.toUpperCase());
                if (!outputString.isEmpty()) {
                    System.out.println("Server received " + outputString + " from: " + ip);
                }
            }
            printToAllClients(ip + " encerrou a conexão.");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void printToAllClients(String outputString) {
        for (ServerThread st : threadList) {
            st.output.println(outputString);
        }
    }
}
