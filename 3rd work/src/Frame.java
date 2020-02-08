
import java.io.Serializable;

public class Frame implements Serializable{
    
    double header;
    String data;
    int protocolID;
    Boolean tailer;

    Frame(double h, String d, int p, Boolean t) {
      this.header = h;
      this.data = d;
      this.protocolID = p;
      this.tailer = t;
    }
}
