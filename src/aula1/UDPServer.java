package aula1;// created on 29/09/2010 at 22:33
import java.net.*;
import java.util.ArrayList;
import java.util.List;

class UDPServer {
	public static void main(String args[]) throws Exception {
		List<String> blacklist = new ArrayList<>();
		blacklist.add("127.0.0.1");

		//Create server socket
		DatagramSocket serverSocket = new DatagramSocket(9876);
		while(true) {
			byte[] receiveData = new byte[1024];
			//block until packet is sent by client
			DatagramPacket receivedPacket = new DatagramPacket(receiveData, receiveData.length);
			serverSocket.receive(receivedPacket);
			//Get the information about the datagram of the client
			InetAddress IPAddress = receivedPacket.getAddress();
			String ip = IPAddress.getHostAddress();
			int port = receivedPacket.getPort();
			if(blacklist.contains(ip)) {
				System.out.println("O IP: " + ip + " está banido!");
				continue;
			}
			//Get the data of the packet
			String sentence = new String(receivedPacket.getData(), 0, receivedPacket.getLength());
			System.out.println("RECEIVED FROM CLIENT " + ip +": " + sentence);
			//Change the data to capital letters
			String capitalizedSentence = sentence.toUpperCase();
			byte[] sendData = new byte[sentence.length()];
			sendData = capitalizedSentence.getBytes();
			//Send back the response to the client
			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
			serverSocket.send(sendPacket);
		}
	}
}
