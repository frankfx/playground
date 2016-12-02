package web.server;
import java.net.ServerSocket;
import java.net.Socket;

import web.client.Connection;

public class BookServer {
	
	ServerSocket server;
	
	public BookServer(){
		try{
			server = new ServerSocket(1738);
		}
		catch(Exception e){
			System.out.println("error while starting the server");
			System.exit(1);
		}
	}
	
	private void printIncommingConnections(Socket client){
		System.out.println(client.getRemoteSocketAddress());
	}
	
	public void start(){
		Socket client;
		Thread thread;
		
		while(true){
			try{
				client = server.accept();
				printIncommingConnections(client);
				Connection conn = new Connection(client);
				thread = new Thread(conn);
				thread.start();
			}
			catch(Exception e){
				System.out.println("connection error");
			}
		}
	}
	
	public static void main(String[] args) {
		BookServer server = new BookServer();
		server.start();
	}
}
