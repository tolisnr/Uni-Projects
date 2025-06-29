import java.net.*;
import java.io.*;

public class EchoServerTCP {
	private static final int PORT = 1234;
	private static final String EXIT = "CLOSE";

	public static void main(String[] args) throws IOException {

		ServerSocket connectionSocket = new ServerSocket(PORT); //Create a new communication point
		System.out.println("Server is listening to port: " + PORT);

		Socket dataSocket = connectionSocket.accept(); //Block caller until a connection request arrives
		System.out.println("Received request from " + dataSocket.getInetAddress());

		InputStream is = dataSocket.getInputStream();
		BufferedReader in = new BufferedReader(new InputStreamReader(is));
		OutputStream os = dataSocket.getOutputStream();
		PrintWriter out = new PrintWriter(os,true);
		
		String inMsg, outMsg;
		inMsg = in.readLine();
		ServerProtocol app = new ServerProtocol();
		outMsg = app.processRequest(inMsg);
		while(!outMsg.equals(EXIT)) {
			out.println(outMsg);
			inMsg = in.readLine();
			outMsg = app.processRequest(inMsg);
		}

		dataSocket.close(); //Release the connection
		System.out.println("Data socket closed");	

		
	}
}			

