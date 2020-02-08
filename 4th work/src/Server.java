import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;
import java.math.BigInteger;

public class Server {
    

    public static void main (String[] args) {

        try {
            
            System.out.println("Waiting for client..............");
            ServerSocket ser = new ServerSocket(2500);
            Socket soc = ser.accept();
            System.out.println("Established");
            

            ObjectOutputStream out = new ObjectOutputStream(soc.getOutputStream()); 
            ObjectInputStream in = new ObjectInputStream(soc.getInputStream());

            System.out.println("Receiving object from client...");
            System.out.println();
            
            
            for(int i=1; i<=10;i++)
            {
                Frame inFrame = (Frame) in.readObject();
                double header=inFrame.header;
                String data=inFrame.data;
                int protocol=inFrame.protocolID;
                Boolean tailer=inFrame.tailer;
                
                
               String headerToBinary=Long.toBinaryString(Double.doubleToRawLongBits(header));
               String dataToBinary = new BigInteger(data.getBytes()).toString();
               String protocolIdtoBinary= Integer.toBinaryString(protocol);
               
               
               String allbits=headerToBinary+dataToBinary+protocolIdtoBinary;
               
               int numberOfOne=checkOne(allbits);
               boolean checktailer;
               
               if(numberOfOne%2==0)
               {
                   checktailer=false;
               }else
               {
                   checktailer=true;
               }
               
               
               System.out.println("Parity Checking for frame "+i+"...");
               
               if(checktailer==tailer)
               {
                   System.out.println("Okay");
               }else
               {
                   System.out.println("Not Okay");
               }
               
               
               
               System.out.println();
               
               System.out.println("Sending acknowledgement from server for frame "+i);
               System.out.println();
               Frame outFrame = new Frame(0, null,0 , null,i);
               out.writeObject(outFrame);
               
            }


            ser.close();
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
