import java.io.*;
import java.net.*;

class ServerThread extends Thread
{
	private Socket dataSocket;
	private InputStream is;
   	private BufferedReader in;
	private OutputStream os;
   	private PrintWriter out;
	private Pi pi;

   	public ServerThread(Socket socket, Pi pi)
   	{
		dataSocket = socket;
		try {
			is = dataSocket.getInputStream();
			in = new BufferedReader(new InputStreamReader(is));
			os = dataSocket.getOutputStream();
			out = new PrintWriter(os,true);
			this.pi = pi;
		}
		catch (IOException e)	{		
			System.out.println("I/O Error " + e);
		}
	}
	
	public void run()
	{
   		String inMsg, outMsg;
		
		try {
			inMsg = in.readLine();
			ServerProtocol app = new ServerProtocol(pi);
			outMsg = app.processRequest(inMsg);
			while (/*!outMsg.equals("EXIT")*/true) {
				out.println(outMsg);
				inMsg = in.readLine();
				outMsg = app.processRequest(inMsg);
				if (outMsg.equals("EXIT")) {
					out.println(outMsg);
					break;
				}
			}	
			dataSocket.close();	

		} catch (IOException e)	{		
	 		System.out.println("I/O Error " + e);
		}
	}	
}	
			
		
