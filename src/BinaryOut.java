import java.io.*;

public class BinaryOut {
    int buffer;
    int n;
    BufferedOutputStream out;

    BinaryOut(File f) {
        try {
            OutputStream os = new FileOutputStream(f);
            out = new BufferedOutputStream(os);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    void writeBit(boolean x) {
        buffer <<= 1;
        if (x) buffer |= 1;
        n++;
        if (n == 8) clearBuffer();
    }

    void writeBit(String s) {
        int n = s.length();
        boolean b;
        for (int i = 0; i < n; i++) {
                if(s.charAt(i)=='0')
                    b=false;
                else
                    b=true;
                writeBit(b);
        }
    }

    //clear the buffer
    void clearBuffer() {
        if (n == 0) return;
        if (n > 0) buffer <<= 8 - n;
        try {
            out.write(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        n = 0;
        buffer = 0;
    }

    //flush and close
    void flush(){
        clearBuffer();
        try {
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void close(){
        flush();
        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
