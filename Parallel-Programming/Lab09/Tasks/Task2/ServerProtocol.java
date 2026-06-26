import java.net.*;
import java.io.*;

/**
 * ServerProtocol: Encapsulates the server-side application logic for processing client requests.
 * This class defines what operation is performed on client input and what response is sent back.
 * Currently implements a simple echo protocol: receives a message and returns it unchanged.
 * This class can be extended to implement custom protocol logic (transformations, commands, etc.).
 */
public class ServerProtocol {

	/**
	 * processRequest: Processes a client request and returns a response.
	 * Currently echoes back the received message unchanged.
	 *
	 * @param theInput The message received from the client
	 * @return The response to send back to the client (echo of the input)
	 */
	public String processRequest(String theInput) {
		// Log the received message from the client
		System.out.println("Received message from client: " + theInput);
		if(theInput.equals("CLOSE")) {return "CLOSE";}

		// Echo: return the same message received from client
		String theOutput = calculate(theInput);

		// Log the message about to be sent to the client
		System.out.println("Send message to client: " + theOutput);

		// Return the response to be sent to the client
		return theOutput;
	}


	private String calculate(String theInput) {
		String result = "";
		String[] parts = theInput.split(" ");

		double operand1 = 0;
		double operand2 = 0;
		String operator = "";

		if(parts.length != 3) {
			return "Error: Invalid input";
		}

		try {
			operand1 = Double.parseDouble(parts[0]);
			operator = parts[1];
			operand2 = Double.parseDouble(parts[2]);
		} catch (NumberFormatException e) {
			return "Error: Invalid input";
		}

		switch(operator) {
			case "+":
				result = String.valueOf(operand1 + operand2);
				break;
			case "-":
				result = String.valueOf(operand1 - operand2);
				break;
			case "*":
				result = String.valueOf(operand1 * operand2);
				break;
			case "/":
				if(operand2 == 0) {
					result = "Error: Division by zero";
				} else {
					result = String.valueOf(operand1 / operand2);
				}
				break;
			default:
				result = "Error: Invalid operator";
		}

		return result;
	}
}