package aula1;

import java.io.*;
import java.net.*;

class UDPClient {
	public static void main(String args[]) throws Exception {
		//Create datagram socket
		DatagramSocket clientSocket = new DatagramSocket();
		InetAddress IPAddress = InetAddress.getByName("localhost");
		//Read a sentence from the console
		String sentence = "sentence";
		while(!sentence.equals("")) {
			BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
			sentence = inFromUser.readLine();
			if(sentence.equals("")) {
				break;
			}
			//Allocate buffers
			byte[] sendData = new byte[sentence.length()];
			byte[] receiveData = new byte[sentence.length()];
			//Get the bytes of the sentence
			sendData = sentence.getBytes();
			//Send packet to the server
			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
			clientSocket.send(sendPacket);
			//Get the response from the server
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
			clientSocket.receive(receivePacket);
			//Print the received response
			String modifiedSentence = new String(receivePacket.getData());
			System.out.println("RECEIVED FROM SERVER: " + modifiedSentence);
		}
		//Close the socket
		clientSocket.close();
	}
}
