import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;



public class Server {
	
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try
		{
			String inputMessage="";
			String end_of_con="";
			System.out.println("Waiting for clients.........");
			ServerSocket ss=new ServerSocket(9901);
			Socket soc=ss.accept();
			System.out.println("Connection Established");
			System.out.println();
			while(!end_of_con.equals("bye")) {
			
				BufferedReader in=new BufferedReader(new InputStreamReader(soc.getInputStream()));
				end_of_con=in.readLine();
				System.out.println("Client says: "+end_of_con);
				System.out.println();
					
			    System.out.print("Enter a message to client: ");
			    inputMessage=sc.nextLine();
				PrintWriter out=new PrintWriter(soc.getOutputStream(),true);
				out.println(inputMessage);
				

				
			

			
		           }
			
			ss.close();
			
			
			
			
			
		}catch(Exception e)
		{
			e.printStackTrace();
			
		}

	}
}

