import java.io.*;
import java.util.concurrent.ConcurrentHashMap;

public class ServerProtocol {

	private Pi piNum;
	private ConcurrentHashMap <Integer, Double> map = new ConcurrentHashMap<>();
	// χρησιμοποιούμε ταυτόχρονη δομή ConcurrentHashMap για να είναι thread-safe

	public ServerProtocol(Pi pi) {
		piNum = pi;
	}

	public String processRequest(String inMsg) throws IOException {
		String outmsg = "error";

		try {
			int n = Integer.parseInt(inMsg);
			if(n == -1) {
				outmsg = "EXIT";
			} else if (n > 0) {
				if(!map.containsKey(n)) { // αν δεν υπάρχει, υπολόγισε και αποθήκευσε
					double piValue = piNum.compute(n);
					map.put(n, piValue);
				}
				// αν υπάρχει, απλά πάρε την τιμή
				outmsg = "Pi value for " + n + " iterations is: " + map.get(n);
			} else if (n <= 0) {
				outmsg = "Please enter a positive integer.";
			}
		} catch (NumberFormatException e) {
			return "Invalid input. Please enter a valid integer.";
		}
		return outmsg;
	}
}
