package ws;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

//Service Endpoint Interface
@WebService
@SOAPBinding(style = Style.RPC)
public interface MyMath {
	@WebMethod double sum (double a, double b); 
	@WebMethod double sub (double a, double b);
	@WebMethod double mult (double a, double b);
	@WebMethod double div (double a, double b);
	@WebMethod int fak (int a);
	@WebMethod int fib (int a);
	@WebMethod int ack (int a, int b);
}
