import java.net.*;
import java.io.*;

/**
 * MultithreadedEchoServerTCP: A concurrent TCP echo server using threads.
 * Accepts multiple client connections and handles them CONCURRENTLY by spawning
 * a separate ServerThread for each connected client. This allows serving multiple
 * clients simultaneously.
 */
public class MultithreadedEchoServerTCP {
	private static final int PORT = 1234;
	
	public static void main(String args[]) throws IOException {

		// Create server socket listening on PORT
		ServerSocket connectionSocket = new ServerSocket(PORT);
		
		// Infinite loop: accept each client and spawn a thread for it
		while (true) {

			System.out.println("Server is listening to port: " + PORT);

			// Accept a client connection
			Socket dataSocket = connectionSocket.accept();
			System.out.println("Received request from " + dataSocket.getInetAddress());

			// Create a new thread to handle this client (non-blocking)
			ServerThread sthread = new ServerThread(dataSocket);
			sthread.start(); // Start the thread to handle the client
		}
	}
}


