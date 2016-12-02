package endpoint;

import javax.xml.ws.Endpoint;

import ws.MathImpl;

public class MathPublisher {
	public static void main(String[] args) {
		Endpoint.publish("http://localhost:9999/ws/marita", new MathImpl());
	}
}
