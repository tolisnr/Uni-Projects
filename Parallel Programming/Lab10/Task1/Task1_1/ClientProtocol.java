import java.net.*;
import java.io.*;

public class ClientProtocol {

	BufferedReader user = new BufferedReader(new InputStreamReader(System.in));

	public String prepareRequest() throws IOException {
		System.out.print("Enter an Integer number to compute the pi value: ");
		String theOutput = user.readLine();
		return theOutput;
	}
        
	public String prepareExit() throws IOException {
		return "EXIT";
	}

	public void processReply(String theInput) throws IOException {
	
		System.out.println(theInput);
	}
}
