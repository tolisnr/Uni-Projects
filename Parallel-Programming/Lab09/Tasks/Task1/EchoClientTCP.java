import java.net.*;
import java.io.*;

/**
 * EchoClientTCP: A TCP client that connects to an echo server.
 * Sends user-typed messages to the server and displays the server's echo responses.
 * Terminates when the user types "CLOSE".
 */
public class EchoClientTCP {
	// Network configuration
	private static final String HOST = "localhost";
	private static final int PORT = 1234;
	private static final String EXIT = "CLOSE"; // Termination command

	public static void main(String args[]) throws IOException {

		// Create a TCP socket connection to the server
		Socket dataSocket = new Socket(HOST, PORT);
		
		// Set up input stream (receive data from server)
		InputStream is = dataSocket.getInputStream();
		BufferedReader in = new BufferedReader(new InputStreamReader(is));

		// Set up output stream (send data to server)
		OutputStream os = dataSocket.getOutputStream();
		PrintWriter out = new PrintWriter(os,true);
		       	
		System.out.println("Connection to " + HOST + " established");

		String inmsg, outmsg;
		ClientProtocol app = new ClientProtocol();
		
		// Get first message from user
		outmsg = app.prepareRequest();

		// Send messages until user types "CLOSE"
		while(!outmsg.equals(EXIT)) {
			out.println(outmsg);        // Send message to server
			inmsg = in.readLine();       // Receive echoed message from server
			app.processReply(inmsg);     // Display the reply to user
			outmsg = app.prepareRequest(); // Prompt for next message
		}
		// Send the EXIT signal to server
		out.println(outmsg);

		// Close connection
		dataSocket.close();
		System.out.println("Data Socket closed");

	}
}			

