package file_stream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileInputStreamDemo {
    public static void main(String[] args){

        FileInputStream fileIn = null;

        try {
            fileIn = new FileInputStream(new File("demo.bin"));

            /* read the first byte of data */
            int data = fileIn.read();

            /* repeat until EOF */
            while(data != -1){
                System.out.println(data);
                data = fileIn.read();
            }

            fileIn.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }    
}
