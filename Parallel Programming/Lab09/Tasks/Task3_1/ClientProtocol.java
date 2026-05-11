import java.net.*;
import java.io.*;

/**
 * ClientProtocol: Encapsulates client-side user interaction logic.
 * Handles reading user input from console and displaying server replies.
 * Used by EchoClientTCP to manage the interaction between the user and the server.
 */
public class ClientProtocol {

	// BufferedReader to read user input from standard input (console/keyboard)
	BufferedReader user = new BufferedReader(new InputStreamReader(System.in));
	
	/**
	 * prepareRequest: Prompts the user to enter a message and reads it from console.
	 *
	 * @return The message entered by the user to be sent to the server.
	 * @throws IOException If an I/O error occurs while reading from console.
	 */
	public String prepareRequest() throws IOException {

		printMenu();
		String theOutput = user.readLine();
		return theOutput;
	}

	/**
	 * processReply: Displays the server's reply to the user on the console.
	 * @param theInput The message received from the server.
	 */
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
