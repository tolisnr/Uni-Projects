import java.io.*;

public class ClientProtocol {

	BufferedReader user = new BufferedReader(new InputStreamReader(System.in));

	public String prepareRequest() throws IOException {

		printMenu();
		String theOutput = user.readLine();
		return theOutput;
	}

	public void processReply(String theInput) throws IOException {
	
		System.out.println("Message received from server: " + theInput);
	}

	private void printMenu() {
		System.out.println("\nType the number, then with a comma and a space the message to send");
		System.out.println("1. Send simple message or type message CLOSE to exit");
		System.out.println("2. Send message that will be converted to Lowercase");
		System.out.println("3. Send message that will be converted to Uppercase");
		System.out.println("4. Send a message that will be encoded with Caesar cipher");
		System.out.println("5. Send a message that will be decoded with Caesar cipher");
		System.out.println("For options 4 and 5, add a comma and the offset to encode/decode");
	}
}
