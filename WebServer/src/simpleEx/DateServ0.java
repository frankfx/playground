package simpleEx;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class DateServ0 implements Runnable{
	Socket sock;
	ServerSocket server;
	
	//constructor for listening server
	public DateServ0() {
		server = null;
		
		try{
			//start server
			server = new ServerSocket(1037);
		}
		catch (Exception e){
			System.out.println("Error while starting the server");
			System.exit(1);
		}		
	}
	
	//constructor for clients
	public DateServ0(Socket sock){
		this.sock = sock;
	}
	
	@Override
	public void run(){
		BufferedReader in;
		PrintWriter out;
		String line;
	
		System.out.println("Request from " + sock.getInetAddress() + " Port: " + sock.getPort());
		try{
			out = new PrintWriter(sock.getOutputStream());
			in  = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			
			line = in.readLine();
			
			switch(line){
			case "DATE" 	  : out.println(new Date()); break;
			case "HELLO"   	  : out.println("Hi, how are you?"); break; 
			case "CONNECTION" : out.println(sock.getInetAddress() + ", " + sock.getPort()); break;
			}
			
			out.flush();
		
			sock.close();
		}
		catch(Exception e){System.out.println("Error: " + e.getMessage());}
	}
	
	public void start(){
		Socket client;
		DateServ0 clientserv;
		
		Thread thread;
		
		if (server == null) return;
		
		while(true){
			try{
				//wait for incoming connections
				client = server.accept();
				//connection established, create a new object therefore
				clientserv = new DateServ0(client);
				thread = new Thread(clientserv);
				thread.start();
			} catch(Exception e) {System.out.println("Error while connect to client");}
		}
	}
		
	
	public static void main(String[] args) {
		DateServ0 server = new DateServ0();
		server.start();
	}
}
