import java.net.*;
import java.io.*;

/**
 * ServerProtocol: Encapsulates the server-side application logic.
 * Currently implements simple echo protocol: receives a message and returns it unchanged.
 * This class can be extended to implement custom protocol logic (transformations, commands, etc.).
 */
public class ServerProtocol {

	/**
	 * processRequest: Processes a client request and returns a response.
	 * Currently echoes back the received message.
	 *
	 * @param theInput The message received from the client.
	 * @return The response to send back to the client (echo of the input).
	 */
	public String processRequest(String theInput) {
		System.out.println("Received message from client: " + theInput);

		// Check for CLOSE command first to avoid parsing errors
		if(theInput.equals("CLOSE")) {
			return "CLOSE";
		}

		String theOutput = editMessage(theInput);  // Echo: return the same message
		System.out.println("Send message to client: " + theOutput);
		return theOutput;
	}

	private String editMessage(String input) {
		String[] parts = input.split(",");
		String message = parts[1].trim();
		int option = 0;
        int offset = 0; // For Caesar's Cipher options

        try {
			option = Integer.parseInt(parts[0]);
			if(option == 4 || option == 5) offset = Integer.parseInt(parts[2].trim());
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
        String result = "";

		switch (option) {
			case 1:
				result = message;
				break;
			case 2:
				result = message.toLowerCase();
				break;
			case 3:
				result = message.toUpperCase();
				break;
			case 4:
				result = caesarCipher(message, offset); // Simple Caesar's Cipher with shift of 3
				break;
			case 5:
				result = caesarCipher(message, (-1)*offset); // Encrypted Caesar's Cipher with shift of 5
				break;
			default:
				result = "Invalid option. Please select a valid option from the menu.";
		}
		return result;
	}

	private String caesarCipher(String message, int key) {
		int offset = key % 26; // Ensure the offset wraps around the alphabet
		StringBuilder result = new StringBuilder();

		for (char character : message.toCharArray()) {
			if (character != ' ') {
				int originalAlphabetPosition = character - 'a';
				int newAlphabetPosition = (originalAlphabetPosition + offset) % 26;
				char newCharacter = (char) ('a' + newAlphabetPosition);
				result.append(newCharacter);
			} else {
				result.append(character);
			}
		}
		return result.toString();
	}
}

