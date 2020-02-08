import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	
	static Scanner sc = new Scanner(System.in);
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try
		{
			String end_of_con="";
			System.out.println("Client Started");
			Socket soc=new Socket("localhost",9901);
			
			//BufferedReader userInput= new BufferedReader(new InputStreamReader(System.in));
			//String str=userInput.readLine();
			
			System.out.println();
			System.out.println("Enter a message to server: ");
			String str=sc.nextLine();
			PrintWriter out=new PrintWriter(soc.getOutputStream(),true);
			out.println(str);
			
			
			while(!end_of_con.equals("bye")) {
				
		    
			BufferedReader in=new BufferedReader(new InputStreamReader(soc.getInputStream()));
			end_of_con=in.readLine();
			System.out.println("Server says: "+end_of_con);
			System.out.println();
						
			
			System.out.print("Enter a message to server: ");
			str=sc.nextLine();
			PrintWriter out_new=new PrintWriter(soc.getOutputStream(),true);
			out_new.println(str);
			
			
			}
			
			soc.close();
		
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}

	}

}
