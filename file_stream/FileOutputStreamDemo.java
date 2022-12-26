import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileOutputStreamDemo {
    public static void main(String[] args){

        FileOutputStream fileOut = null;
        FileInputStream fileIn = null;

        try{
            fileIn = new FileInputStream("demo.bin");
            fileOut = new FileOutputStream(new File("demo.bin"));

            /* can read in up to 100 bytes */
            int[] bytes = new int[100];
            int data = fileIn.read();

            bytes[0] = data;
        
            int counter = 1;

            while(data != -1){
                bytes[counter++] = data;

                data = (byte) fileIn.read();

                System.out.println(data);
            }

            /* write previous data */
            for(int i = 0; i < 100; i++)
                fileOut.write(bytes[i]);

            /* write some new data */
            fileOut.write(49);

            fileIn.close();
            fileOut.close();

        }catch(IOException e){
            e.printStackTrace();
        }

    }
}
