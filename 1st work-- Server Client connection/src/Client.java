import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try
		{
			System.out.println("Client Started");
			Socket soc=new Socket("localhost",9900);
			BufferedReader userInput= new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Enter a message...");
			String str=userInput.readLine();
			PrintWriter out=new PrintWriter(soc.getOutputStream(),true);
			out.println(str);
			BufferedReader in=new BufferedReader(new InputStreamReader(soc.getInputStream()));
			System.out.println(in.readLine());
			
			
			soc.close();
			
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}

	}

}