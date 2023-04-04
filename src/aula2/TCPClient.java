package aula2;

import java.io.*;
import java.net.*;

public class TCPClient {

    public static void main(String args[]) throws Exception {
        Socket s = new Socket("localhost", 9000);

        PrintWriter serverWriter = new PrintWriter(s.getOutputStream(), true);
        BufferedReader serverReader = new BufferedReader(new InputStreamReader(s.getInputStream()));
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Informe seu nome:");
        String sentence;
        sentence = inFromUser.readLine();

        Thread serverReaderThread = new Thread(new ServerReaderThread(s));
        serverReaderThread.start();

        while (!sentence.equals("tchau")) {
            serverWriter.println(sentence);
            sentence = inFromUser.readLine();
        }
        // Envia um último tchau para evitar NullPointerException no Server e encerrar a conexão
        serverWriter.println("tchau" + "\n");
        s.close();
    }
}
