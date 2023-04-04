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

            String nome = "";
            boolean primeiraMensagem = true;

            while (true) {
                if (primeiraMensagem) {
                    nome = input.readLine();
                    printToAllClients(nome + " entrou.");
                    primeiraMensagem = false;
                    continue;
                }
                String outputString = input.readLine();

                if (outputString.equals("tchau")) {
                    break;
                }

                if (!outputString.isEmpty()) {
                    printToAllClients(nome + ": " + outputString.toUpperCase());
                    System.out.println("Server received " + outputString + " from: " + ip);
                }
            }
            printToAllClients(nome + " encerrou a conexão.");
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
