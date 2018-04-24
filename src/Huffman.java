import java.io.*;

public class Huffman {

    int[][] freq;           // frequency array column 0=ascii,1=count
    Node[] q;               //priority queue
    HuffTable[] hT;        //huffman Table of codes 0=ascii,1=code
    Node root;              //Huffman tree root
    int x = 0;
    final int range=255;

    void start(File f) {
        frequency(f);
        root = hufftree();       //root is ready
        hT=new HuffTable[freq.length];
       // System.out.println("Tree");
       // show(root);
        createTable(root,"");
        System.out.println("Huffman table");
        show(hT);
    }

    //finding frequency of words
    void frequency(File f) {
        int ch;
        int[][] a = new int[range][1];         //range range as per ascii codes
        for (int i = 0; i < range; i++)        //initialise the array
            a[i][0] = 0;
        try {
            FileReader reader = new FileReader(f);
            ch = reader.read();
            int i;
            while (ch != -1) {
                //write code to make the table
                i = ch;
                a[i][0] += 1;
                ch = reader.read();
            }
            int n = 0;   //no of elements in the file
            //array a[][] is ready now making final array
            for (int j = 0; j < range; j++) {
                if (a[j][0] != 0)
                    n++;
            }

            freq = new int[n][2];
            //add only characters which occur in the file
            for (int j = 0, k = 0; j < range; j++) {
                if (a[j][0] != 0) {
                    freq[k][0] = j;
                    freq[k][1] = a[j][0];
                    k++;
                }
            }
            //final array is ready now just sort it
            //using insertion sort as we only have to sort expected 50 no.
            sort(freq, n);
            show(freq, n);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Sort where 'a[][]' is array 'r' is rows and 'c' columns
     **/

    void sort(int[][] a, int r) {
        //columns will always be 0,1 'c' column 1 stores value
        int key = 0;
        int ascii = 0;
        int i = 0;
        for (int j = 1; j < r; j++) {
            key = a[j][1];
            ascii = a[j][0];
            i = j - 1;
            while (i >= 0 && a[i][1] > key) {
                a[i + 1][0] = a[i][0];
                a[i + 1][1] = a[i][1];
                i = i - 1;
            }
            a[i + 1][0] = ascii;
            a[i + 1][1] = key;
        }
    }

    //show the Frequency table
    void show(int[][] a, int n) {
        for (int i = 0; i < n; i++) {
            System.out.println((char) a[i][0] + "\t" + a[i][1]);
        }
    }

    //show huffman table
    void show(HuffTable[] h){
        int n=h.length;
        for (int i = 0; i < n; i++) {
            System.out.println((char) h[i].ascii + "\t" + h[i].code);
        }
    }

    /**
     * Huffman tree this procedure returns the root node 0f the tree
     */
    Node hufftree() {
        HuffHeap h = new HuffHeap();   //make the priority queue
        q = h.minHeap(freq, freq.length);
        //make huffman tree
        int n = q.length;
        for (int i=0; i < n-1; i++) {
            Node z = new Node();
            Node x, y;
            z.left = x = h.extractMin(q);
            z.right = y = h.extractMin(q);
            z.freq = x.freq + y.freq;
            h.insert(q, z);
        }
        return h.extractMin(q);
    }

    //Note: Construct huffman table from the output tree root of huffTree()
    void createTable(Node curr, String s) {
        //code comes here
        if (curr == null) return;

        if (curr.left == null && curr.right == null) {
            hT[x]= new HuffTable();
            hT[x].ascii = curr.ascii;
            hT[x].code = s;
            x++;
        }

        s += '0';
        createTable(curr.left, s);

        s = s.substring(0, s.length() - 1);
        s += '1';
        createTable(curr.right, s);
    }

    void show(Node curr){
        if(curr!=null){
            show(curr.right);
            System.out.println((char)curr.ascii+"\t"+curr.freq);
            show(curr.left);
        }
    }
}
