package web.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

import web.server.ServerOptions;


public class Connection implements Runnable{
	
	Socket sock;
	BufferedReader in;
	PrintWriter out;
	
	public Connection(Socket sock){
		this.sock = sock;
		try {
			in  = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			out = new PrintWriter(sock.getOutputStream());
		} catch (IOException e) {
			System.out.println("error while create connection");
		}
	}
	
	private void usage(PrintWriter out){
		System.out.println("CMD - Tool");
		String format = "%-20s%s%n";

		out.println("=====================================");
		out.printf(format, "hello", "print greeting message");		
		out.printf(format, "info" , "print the connection infos");	
		out.printf(format, "date" , "print the date");
		out.printf(format, "exit" , "cancel the connection");	
		out.printf(format, "cmd"  , "start a server command prompt");			
		out.printf(format, "pow <value>", "computes the square of a number");	
		out.println("=====================================");
	}

	synchronized String exec(String command){
		try{
			double a = Math.pow(Double.parseDouble(command), 2.0);
			return command+"^2 = " + a;
		}catch(Exception e){
			return "only doubles allowed";
		}
	}
	
	private void runCommandline(BufferedReader in, PrintWriter out) throws IOException{
		System.out.println("in run CommandLine");
		String cmd_buffer;
		String response;
		while (true){
			// show a simple prompt
			out.print("<BHP:#> ");
			out.flush();
			// now we receive until we see a line feed (enter key)
			cmd_buffer = in.readLine();
			// finish if the input was exit
			if (cmd_buffer.equalsIgnoreCase("exit"))
				break;
			// else compute data 
			response = runCommand(cmd_buffer);
			// send back the response
			out.println(response);	
			out.flush();
		}
	}
	
	
	private String runCommand(String com) {
		// result of command execution
		StringBuffer output = new StringBuffer();
		// terminal process
		Process p;
		// run the command and get the output back
		try {
			//p = Runtime.getRuntime().exec("/bin/bash " + command); 
			
			String[] command = { "/bin/bash", "-c", com };
			
			p = new ProcessBuilder(command).start();
			
			p.waitFor();
			BufferedReader reader = 
                            new BufferedReader(new InputStreamReader(p.getInputStream()));

            String line = "";			
			while ((line = reader.readLine())!= null) {
				output.append(line + "\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "Failed to execute command.\r\n";
		}
		// send the output back to the client
		return output.toString();
	}	
	
	
	
	@Override
	public void run() {
		String [] line; 
		String opt, arg;
		ServerOptions state;
		String temp;
		
		try{
			while(true){
				temp = in.readLine();

				System.out.println(temp);
				if (temp == null)
					break;
				
				line = temp.split(" ", 2);
				
				opt = line[0];
				arg = line.length > 1 ? line[1] : "";
				
				state = ServerOptions.RUNNING;
				
				switch(opt){
				case "hello"   	  : out.println("Hi, how are you?"); break; 
				case "info" 	  : out.println(sock.getInetAddress() + ", " + sock.getPort()); break;
				case "date" 	  : System.out.println("DATATAAT"); out.println(new Date()); break;
				case "exit"		  : state = ServerOptions.QUIT; break;
				case "cmd"	 	  : runCommandline(in, out); break;
				case "pow"		  : out.println(exec(arg)); break;
				case "marita"	  : out.println("I love Marita!!! :-*"); break;
				default 		  : usage(out);
				}				
				
				if (state.equals(ServerOptions.QUIT))
					break;
				out.flush();
			}
			System.out.println("close connection" + sock.getInetAddress());
			sock.close();
		}
		catch(Exception e){
			System.out.println("error while running thread");
			e.printStackTrace();
		}
	}
}
