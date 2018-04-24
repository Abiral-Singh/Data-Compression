import java.io.Serializable;

public class Node implements Serializable {
    int ascii;
    int freq;       // freq = count
    Node left;
    Node right;

    Node() {
        ascii = 0;
        freq = 0;
        left = null;
        right = null;
    }
}
