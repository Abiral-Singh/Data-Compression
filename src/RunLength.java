import java.io.*;

public class RunLength {
    void compress(File f) {
        System.out.println("\nLocation of file to be compressed : " + f.getAbsolutePath());
        System.out.println("Compressed file at : " + f.getAbsolutePath());
        File cf = new File(f.getParentFile().getAbsolutePath() + File.separator + "Compressed " + f.getName()); //Compressed File
        cf.getParentFile().mkdirs();
        if (!cf.exists())
            try {
                cf.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        //read the original file and write to compressed file
        try {
            FileInputStream reader = new FileInputStream(f);
            FileWriter writer = new FileWriter(cf);
            //compressing file
            int n = 0;
            byte flag;
            byte[] buffer = new byte[1];
            reader.read(buffer);
            writer.write((char) buffer[0]);
            flag = buffer[0];
            int result = 0;
            do {
                n++;
                while ((result = reader.read(buffer)) != -1 && flag == buffer[0]) {
                    n++;
                }
                writer.write(String.valueOf(n));
                n = 0;
                //flip the flag
                if (flag == '0')
                    flag = '1';
                else
                    flag = '0';
            } while (result != -1);
            reader.close();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
