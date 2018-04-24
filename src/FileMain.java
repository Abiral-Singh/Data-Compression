//Main file is Test Main it is for testing runlength Code

import java.io.*;

public class FileMain {

    static void main(String[] args) {
        //create a file
        File dir = new File("Files of program");
        dir.mkdir();
        File f = new File(dir.getAbsolutePath() + File.separator + "Test File.txt");
        try {
            f.getParentFile().mkdirs();
            if (!f.exists())
                f.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //writing on a file int data type
        try {
            FileWriter writer = new FileWriter(f);
            for (int i = 0; i <= 100000; i++) {
                writer.write(String.valueOf(random()));
            }
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            FileInputStream read = new FileInputStream(f);
            BufferedInputStream reader = new BufferedInputStream(read);
            byte[] buffer = new byte[1];
            while ((reader.read(buffer)) != -1) {
                System.out.print((char) buffer[0]);
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        RunLength r = new RunLength();
        r.compress(f);
    }

    static int random() {
        double n = Math.random();
        /*byte[] b=new byte[1];*/
        if (n < 0.4) {
            return 0;
        } else {
            return 1;
        }
       /* b[0]=(byte) n;
        return b;*/
    }
}
