import java.io.*;
import java.net.*;

/**
 * ServerThread: A worker thread that handles communication with a single connected client.
 * Used by MultithreadedEchoServerTCP to process multiple clients concurrently.
 * Each ServerThread instance runs in its own thread and independently handles
 * the client's echo requests until the client sends "CLOSE".
 */
class ServerThread extends Thread
{
	// Client socket and associated streams
	private Socket dataSocket;
	private InputStream is;
	private BufferedReader in;
	private OutputStream os;
	private PrintWriter out;
	private static final String EXIT = "CLOSE";

	/**
	 * Constructor: Initializes the thread with a connected client socket.
	 * Sets up input and output streams for communication.
	 *
	 * @param socket The connected client socket passed from the accepting server.
	 */
	public ServerThread(Socket socket)
	{
		dataSocket = socket;
		try {
			// Initialize streams for reading from and writing to the client
			is = dataSocket.getInputStream();
			in = new BufferedReader(new InputStreamReader(is));
			os = dataSocket.getOutputStream();
			out = new PrintWriter(os,true);
		}
		catch (IOException e)	{		
	 		System.out.println("I/O Error " + e);
		}
	}

	/**
	 * run: The main logic executed when the thread starts.
	 * Reads messages from the client, echoes them back, until client sends "CLOSE".
	 */
	public void run()
	{
		String inmsg, outmsg;

		try {
			// Read first message from client
			inmsg = in.readLine();
			ServerProtocol app = new ServerProtocol();
			outmsg = app.processRequest(inmsg);

		// Keep echoing messages until client sends "CLOSE"
		while(!outmsg.equals(EXIT)) {
			out.println(outmsg);        // Send echoed message back to client
			inmsg = in.readLine();      // Receive next message from client
			outmsg = app.processRequest(inmsg);
		}

		// Send the CLOSE response before closing the connection
		out.println(outmsg);            // Send "CLOSE" response to client

		// Close connection with this client and terminate thread
		dataSocket.close();
		System.out.println("Data socket closed");

		} catch (IOException e)	{		
	 		System.out.println("I/O Error " + e);
		}
	}	
}	
