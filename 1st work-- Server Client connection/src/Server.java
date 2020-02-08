import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;



public class Server {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		try
		{
			System.out.println("Waiting for clients.........");
			ServerSocket ss=new ServerSocket(9900);
			Socket soc=ss.accept();
			System.out.println("Connection Established");
			BufferedReader in=new BufferedReader(new InputStreamReader(soc.getInputStream()));
			String str=in.readLine();
			PrintWriter out=new PrintWriter(soc.getOutputStream(),true);
			out.println("Server Says: "+str);
			ss.close();
			
		}catch(Exception e)
		{
			e.printStackTrace();
			
		}

	}
}