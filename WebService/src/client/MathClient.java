package client;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import ws.MyMath;

// client for testing. we import MyMath directly without generation service class using wsimport

public class MathClient {
	public static void main(String[] args) throws MalformedURLException {
		URL url = new URL("http://localhost:9999/ws/marita?wsdl");
		
	    //1st argument service URI, refer to wsdl document above
		//2nd argument is service name, refer to wsdl document above
		QName qname = new QName("http://ws/", "MathImplService");
	
        Service service = Service.create(url, qname);

        MyMath math = service.getPort(MyMath.class);

        System.out.println(math.sum(3, 6));
        System.out.println(math.sub(3, 6));
        System.out.println(math.mult(3, 6));
        System.out.println(math.div(13, 6));
        System.out.println(math.fak(6));
        System.out.println(math.fib(6));        
        System.out.println(math.ack(3,2));
	}
}
