package web.client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

class Client {
	Socket socket;
	DataOutputStream outToServer;
	BufferedReader inFromServer;
	
	public Client(String host, int port){
		try {
			socket = new Socket(host, port);
			outToServer  = new DataOutputStream(socket.getOutputStream());
			inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (UnknownHostException e) {
			System.out.println("client connection error: unknow host");
		} catch (IOException e) {
			System.out.println("client connection error: io exception");
		}
	}
	
	public void request(String request){
		try {
			outToServer.writeBytes(request + '\n');
			outToServer.flush();
		} catch (IOException e) {
			System.out.println("error while send data to server");
		} 
	}
	
	private String response(){
		try {
			return inFromServer.readLine();
		} catch (IOException e) {
			System.out.println("error while read date from server");
			return null;
		} 
	}
	
	public void getResponse(){
		System.out.println("FROM SERVER: " + response());
	}
	
	public static void main(String argv[]) throws Exception {
		Client client = new Client("localhost", 1738);
		
		client.request("date");  
		client.getResponse();

		client.request("marita");  
		client.getResponse();
		
		client.request("info");  
		client.getResponse();		

		client.request("hello");  
		client.getResponse();		
	
		client.request("pow 2");  
		client.getResponse();	

		client.request("exit");  
		client.getResponse();			
		
		while(true){}
	}

}