import java.net.*;
import java.io.*;

/**
 * EchoClientTCP: A TCP client that connects to an echo server.
 * This client connects to the server, reads user input, sends it to the server,
 * and displays the server's response. It continues until the user types "CLOSE".
 *
 * Server Connection:
 * - Connects to localhost:1234
 * - Terminates connection by sending "CLOSE" command
 */
public class EchoClientTCP {
	// Server connection parameters
	private static final String HOST = "localhost";      // Server hostname/IP address
	private static final int PORT = 1234;                // Server port number
	private static final String EXIT = "CLOSE";          // Termination command

	public static void main(String args[]) throws IOException {

		// Create a socket connection to the server at HOST:PORT
		Socket dataSocket = new Socket(HOST, PORT);
		
		// Set up input stream to receive messages from the server
		InputStream is = dataSocket.getInputStream();
		BufferedReader in = new BufferedReader(new InputStreamReader(is));

		// Set up output stream to send messages to the server
		OutputStream os = dataSocket.getOutputStream();
		PrintWriter out = new PrintWriter(os, true);  // true enables auto-flushing

		// Confirm successful connection to server
		System.out.println("Connection to " + HOST + " established");

		// Variables to store messages from user and server
		String inmsg, outmsg;

		// Create protocol handler for user interaction
		ClientProtocol app = new ClientProtocol();
		
		// Get first message from user
		outmsg = app.prepareRequest();

		// Loop: send user message, receive reply, display reply, repeat
		// Continue until user types "CLOSE"
		while(!outmsg.equals(EXIT)) {
			// Send user's message to the server
			out.println(outmsg);

			// Receive server's response
			inmsg = in.readLine();

			// Display server's response to the user
			app.processReply(inmsg);

			// Get next message from user
			outmsg = app.prepareRequest();
		}

		// Send the "CLOSE" termination command to the server
		out.println(outmsg);

		// Receive the server's closing response
		inmsg = in.readLine();
		app.processReply(inmsg);

		// Close the socket connection to the server
		dataSocket.close();
		System.out.println("Data Socket closed");

	}
}			

