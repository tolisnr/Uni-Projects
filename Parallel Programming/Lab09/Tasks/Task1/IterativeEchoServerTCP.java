import java.net.*;
import java.io.*;

/**
 * IterativeEchoServerTCP: An iterative TCP echo server.
 * Accepts multiple client connections but handles them sequentially (one at a time).
 * Each client monopolizes the server while connected. Runs indefinitely.
 */
public class IterativeEchoServerTCP {
	private static final int PORT = 1234;
	private static final String EXIT = "CLOSE";

	public static void main(String args[]) throws IOException {

		// Create server socket listening on PORT
		ServerSocket connectionSocket = new ServerSocket(PORT);
		
		// Loop indefinitely, accepting and handling clients one at a time
		while (true) {

			System.out.println("Server is listening to port: " + PORT);

			// Accept a client connection (blocks until connection arrives)
			Socket dataSocket = connectionSocket.accept();
			System.out.println("Received request from " + dataSocket.getInetAddress());

			// Set up input stream to receive messages from client
			InputStream is = dataSocket.getInputStream();
			BufferedReader in = new BufferedReader(new InputStreamReader(is));

			// Set up output stream to send messages to client
			OutputStream os = dataSocket.getOutputStream();
			PrintWriter out = new PrintWriter(os,true);
					
			// Read messages from client and echo them back
			String inmsg, outmsg;
			inmsg = in.readLine();
			ServerProtocol app = new ServerProtocol();
			outmsg = app.processRequest(inmsg);

			// Keep echoing while client doesn't send "CLOSE"
			while(!outmsg.equals(EXIT)) {
				out.println(outmsg);       // Send echoed message
				inmsg = in.readLine();     // Receive next message
				outmsg = app.processRequest(inmsg);
			}

			// Send the CLOSE response before closing the connection
			out.println(outmsg);           // Send "CLOSE" response to client

			// Close this client connection and loop back to accept next one
			dataSocket.close();
			System.out.println("Data socket closed");
		}
	}
}			

