import java.net.*;
import java.io.*;
public class EchoClientTCP {
	private static final String HOST = "localhost";
	private static final int PORT = 1234;
	private static final String EXIT = "CLOSE";

	public static void main(String[] args) throws IOException {

		Socket dataSocket = new Socket(HOST, PORT);
		
		InputStream is = dataSocket.getInputStream();
		BufferedReader in = new BufferedReader(new InputStreamReader(is));
		OutputStream os = dataSocket.getOutputStream();
		PrintWriter out = new PrintWriter(os,true);
		       	
		System.out.println("Connection to " + HOST + " established");

		String inMsg, outMsg;
		ClientProtocol app = new ClientProtocol();
		
		outMsg = app.prepareRequest();
		while(!outMsg.equals(EXIT)) {
			out.println(outMsg);
			inMsg = in.readLine();
			app.processReply(inMsg);
			outMsg = app.prepareRequest();
		}
		out.println(outMsg);

		dataSocket.close();
		System.out.println("Data Socket closed");

	}
}			

