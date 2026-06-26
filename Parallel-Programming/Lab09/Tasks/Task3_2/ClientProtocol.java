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
	
		printPrompt();
		String theOutput = user.readLine();
		return theOutput;
	}

	/**
	 * processReply: Displays the message received from the server to the user.
	 *
	 * @param theInput The message received from the server to be displayed.
	 * @throws IOException If an I/O error occurs while writing to console.
	 */
	public void processReply(String theInput) throws IOException {
	
		System.out.println("Message received from server: " + theInput);
	}

	private void printPrompt() {
		System.out.println("\nCalculation app");
		System.out.println("Enter the format like this: operand1 operator operand2");
		System.out.println("Example: 2 + 3");
		System.out.println("By typing CLOSE the calculator closes.");
	}
}
