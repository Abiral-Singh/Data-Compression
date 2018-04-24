import java.io.*;

public class Handler {
    BinaryOut bo;
    BinaryIn bi;
    Huffman huff;

    void start(File file) {
        // File file = new File("Text.txt");
        if (!file.exists())
            try {
                file.createNewFile();
                return;
            } catch (IOException e) {
                e.printStackTrace();
            }

        huff = new Huffman();  //huffman initialized
        huff.start(file);
        //encode and decode
        File cfile = new File(file.getParentFile().getAbsolutePath() + File.separator + file.getName().substring(0, file.getName().length() - 4) + "_compressed.txt");
        encode(file, cfile);
        /*File dfile = new File("Text_decoded.txt");
        decode(cfile,dfile);*/
    }

    //f=original file , ef = to be encoded into
    void encode(File f, File ef) {
        // create encoded file if not created
        File rFile = new File(ef.getParentFile().getAbsolutePath() + File.separator + ".decode_data");
        try {
            rFile.createNewFile();
            ObjectOutputStream obj = new ObjectOutputStream(new FileOutputStream(rFile));
            obj.writeObject(huff.root);
            obj.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            ef.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //read a char from f and then search in table and write to ef
        int ch;
        try {
            FileReader reader = new FileReader(f);
            bo = new BinaryOut(ef);
            ch = reader.read();
            while (ch != -1) {
                //find in the table
                bo.writeBit(search(ch));
                ch = reader.read();
            }
            bo.close();
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * search in the huffman table hT and return the code
     */
    String search(int n) {
        int r = huff.hT.length;
        for (int i = 0; i < r; i++) {
            if (n == huff.hT[i].ascii)
                return huff.hT[i].code;
        }
        return "";
    }

    //ef=encoded file , df = to be decoded into
    int decode(File ef, File df) {
        File rfile = new File(df.getParentFile().getAbsolutePath() + File.separator + ".decode_data");
        Node root = new Node();
        if (rfile.exists()) {
            try {
                ObjectInputStream obj = new ObjectInputStream(new FileInputStream(rfile));
                root = (Node) obj.readObject();
                obj.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            Node curr = root;
            boolean way = false;
            //create decoded file
            try {
                df.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            bi = new BinaryIn(ef);
            try {
                FileWriter writer = new FileWriter(df);
                // finding character from the file
                //System.out.println("Decoding");
                while (true) {
                    if (curr.right == null && curr.left == null) {
                        // System.out.println((char)curr.ascii);
                        writer.write(curr.ascii);
                        curr = root;

                    }
                    try {
                        way = bi.readBoolean();
                    } catch (Exception e) {
                        break;
                    }
                    if (way)
                        curr = curr.right;
                    else
                        curr = curr.left;

                }
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Decoding Data Doesn't exist");
            return -1;
        }
        return 0;
    }
    //
}
