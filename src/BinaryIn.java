import java.io.*;
import java.util.NoSuchElementException;

import static sun.nio.ch.IOStatus.EOF;

public class BinaryIn {
    BufferedInputStream in;
    int buffer;
    int n;

    BinaryIn(File f) {
        try {
            InputStream is = new FileInputStream(f);
            in = new BufferedInputStream(is);
            fillBuffer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //fill buffer
    void fillBuffer() {
        try {
            buffer = in.read();
            n = 8;
        } catch (IOException e) {
            // System.err.println("Eof");
            buffer = EOF;
            n = -1;
        }
    }

    boolean isEmpty() {
        return buffer == EOF;
    }

    boolean readBoolean() {
        if (isEmpty())
            throw new NoSuchElementException("Reading from empty input stream");
        n--;
        boolean bit =((buffer >> n)&1)==1;
        if (n==0) fillBuffer();
        return bit;

    }

}
