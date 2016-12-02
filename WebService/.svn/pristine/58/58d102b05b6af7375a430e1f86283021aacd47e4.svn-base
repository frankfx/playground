package ws;

import javax.jws.WebService;

//Service Implementation
@WebService(endpointInterface = "ws.MyMath")
public class MathImpl implements MyMath {

	@Override
	public double sum(double a, double b) {
		return a+b;
	}

	@Override
	public double sub(double a, double b) {
		return a-b;
	}

	@Override
	public double mult(double a, double b) {
		return a*b;
	}

	@Override
	public double div(double a, double b) {
		return a/b;
	}

	@Override
	public int fak(int a) {
		if (a<=1) return 1;
		else return a * fak(a-1);
	}

	@Override
	public int fib(int a) {
		if(a<=2) return 1;
		else return fib(a-1) + fib(a-2);
	}

	@Override
	public int ack(int a, int b) {
	    if(a==0)
	    	return b+1;
	    else if(b==0)
	        return ack(a-1, 1);
	    else
	        return ack(a-1, ack(a, b-1));
	}
}
