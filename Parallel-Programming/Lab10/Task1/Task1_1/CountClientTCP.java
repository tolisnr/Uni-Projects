import java.net.*;
import java.io.*;

public class CountClientTCP {
	private static final String HOST = "localhost";
	private static final int PORT = 1234;
	
	public static void main(String[] args) throws IOException {

		Socket dataSocket = new Socket(HOST, PORT);
		
		InputStream is = dataSocket.getInputStream();
		BufferedReader in = new BufferedReader(new InputStreamReader(is));
		OutputStream os = dataSocket.getOutputStream();
		PrintWriter out = new PrintWriter(os,true);
		       	
		String inMsg, outMsg;
		ClientProtocol app = new ClientProtocol();

		outMsg = app.prepareRequest();
		out.println(outMsg);
		inMsg = in.readLine();
		app.processReply(inMsg);

		outMsg = app.prepareExit();
		out.println(outMsg);
		
		dataSocket.close();
	}
}			

