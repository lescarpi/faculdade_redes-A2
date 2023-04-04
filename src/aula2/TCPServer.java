package aula2;//
//  TCPServer.java
//
//  Kurose & Ross
//
import java.io.*;
import java.net.*;
import java.util.ArrayList;

class TCPServer {

    public static void main (String args[]) throws Exception {
		// throws Exception here because don't want to deal
		// with errors in the rest of the code for simplicity.
		// (Note: NOT a good practice).

		ArrayList<ServerThread> threadList = new ArrayList<>();

         //Welcome socket  ---- SOCKET 1
		 ServerSocket serverSocket = new ServerSocket(9000);
		 // waits for a new connection. Accepts connection from multiple clients
		 while (true) {
			 System.out.println("Waiting for connection at port 9000.");
             //Connection socket  --- SOCKET 2
			 Socket s = serverSocket.accept();
			 ServerThread serverThread = new ServerThread(s, threadList);
			 threadList.add(serverThread);
			 serverThread.start();

		 }
    }
}
