import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.io.*;
import java.math.BigInteger;

public class Client {

    public static void main(String[] args) {

        try {
        	
            Socket soc = new Socket("127.0.0.1", 2500);

            ObjectInputStream sin = new ObjectInputStream(soc.getInputStream());
            ObjectOutputStream sout = new ObjectOutputStream(soc.getOutputStream());
            
            
            
            String  data="RUET";
            int protocolID=15;
            boolean tailer;
            double acknowledgement = 0;
            
            
           
           String dataToBinary = new BigInteger(data.getBytes()).toString();
           String protocolIdtoBinary= Integer.toBinaryString(protocolID);

           
           

            System.out.println("Sending object to server...");
            System.out.print("\n\n");
            
            for(int i=1; i<=10; i++) {
            
            double header= i;
            String headerToBinary=Long.toBinaryString(Double.doubleToRawLongBits(header));
            String allbits=headerToBinary+dataToBinary+protocolIdtoBinary;
            int numberOfOne=checkOne(allbits);
            
            if(numberOfOne%2==0)

                tailer=false;
            else
                tailer=true;
            	
            	
            Frame outFrame = new Frame(i, data, protocolID, tailer, acknowledgement);
            sout.writeObject(outFrame);
            
            System.out.println("Header: " + outFrame.header + "\nData: " + outFrame.data + "\nProtocolID: " + outFrame.protocolID + "\nTailer: " + outFrame.tailer);
            System.out.println();
            System.out.println("Sent frame "+i+" successfully");
            System.out.println();
            System.out.println();
            
            System.out.println("Acknowledgement checking.............");
            Frame inFrame = (Frame) sin.readObject();
            double ac = inFrame.acknowledgement ;
            
            System.out.println("Received "+ ac +  " number Frame Successfully");
            System.out.println();
            System.out.println();
            
            
            }
            
            
            
            
            
            

            soc.close();
        } catch (Exception e) {
          System.out.println("Exception caught: " + e);
        }
    }

    static int checkOne(String str) {
        int count = 0;
        for (int i = 0; i < str.length();i++) {
            if(str.charAt(i)=='1')
                count++;
        }
        return count;
    }

}
