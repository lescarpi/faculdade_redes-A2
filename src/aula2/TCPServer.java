package aula2;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

class TCPServer {

    public static void main(String args[]) throws Exception {

        ArrayList<ServerThread> threadList = new ArrayList<>();

        ServerSocket serverSocket = new ServerSocket(9000);

        while (true) {
            System.out.println("Waiting for connection at port 9000.");

            Socket s = serverSocket.accept();
            ServerThread serverThread = new ServerThread(s, threadList);
            threadList.add(serverThread);
            serverThread.start();

        }
    }
}
