import java.net.*;
import java.io.*;

public class ServerProtocol {

	private Pi piNum;

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
				double piValue = piNum.compute(n);
				outmsg = "Pi value for " + n + " iterations is: " + piValue;
			} else if (n <= 0) {
				outmsg = "Please enter a positive integer.";
			}
		} catch (NumberFormatException e) {
			return "Invalid input. Please enter a valid integer.";
		}
		return outmsg;
	}
}
