import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.io.*;
import java.math.BigInteger;

public class Client {

    public static void main(String[] args) {

        try {
            Socket soc = new Socket("127.0.0.1", 25);

            ObjectInputStream sin = new ObjectInputStream(soc.getInputStream());
            ObjectOutputStream sout = new ObjectOutputStream(soc.getOutputStream());
            
            
            double header= 1.0;
            String  data="RUET";
            int protocolID=15;
            boolean tailer;
            
            
           String headerToBinary=Long.toBinaryString(Double.doubleToRawLongBits(header));
           String dataToBinary = new BigInteger(data.getBytes()).toString(2);
           String protocolIdtoBinary= Integer.toBinaryString(protocolID);
           String allbits=headerToBinary+dataToBinary+protocolIdtoBinary;
           int numberOfOne=checkOne(allbits);
           
           if(numberOfOne%2==0)
               tailer=false;
           else
               tailer=true;
           
          
           
            

            Frame outFrame = new Frame(header,data,protocolID, tailer);


            System.out.println("Sending object to server...");
            sout.writeObject(outFrame);
            System.out.println("Header: " + outFrame.header + "\nData: " + outFrame.data + "\nProtocolID: " + outFrame.protocolID + "\nTailer: " + outFrame.tailer);
            System.out.println("Sent.");
            System.out.println();

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
