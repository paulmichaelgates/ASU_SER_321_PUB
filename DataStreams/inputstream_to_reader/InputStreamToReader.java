import java.io.BufferedReader;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/*
 * Author: Paul Gates, 2022
 * Materials from:
 *  Java Network Programming and Distributed Computing
 *  chapter 4
 * 
 * Note:
 *  the purpose of this demo is to highlight some imports steps that occur
 *  in basic IO that are often are over looked by developers first learning
 *  these concepts, as many of the things are either too complex, or not 
 *  important enough to get started with io. 
 */
public class InputStreamToReader {
    
    public static void main(String[] args)throws IOException{

        System.out.println("Enter some information:");

        /* an InputStream is a basic low level input class provided by
         * java.io lib (see imports above). It is an abstract class which 
         * represents an inputstream of bytes that can be used in several 
         * different ways.
         * 
         * an InputStream Object will have limited methods for reading the
         * data in the stream i.e., the read method will return integer values
         * of the byte data recieved one byte at a time. Calling the read 
         * method increments the reader pointer and returns the read byte
         * (just like a queue)
         */
        InputStream is = System.in; /* get user input from console */

        /* by wrapping the input stream in an input stream reader, we can
         * read the data from the input stream more easily.
         * 
         * this allows us the input stream with a buffered reader which
         * allows for complex data type reading (rather than just ints
         * i.e., bytes of data at a time)
         */
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader bfr = new BufferedReader(isr);

        /* prints the information given to the input stream
         * 
         * Note that the data was not given directly to the
         * BufferedReader Object, but rather, the readers, both
         * Buffered and Input Stream, are just conduits in which
         * the low level input stream can be read in a way easier
         * for developers :)
         */
        System.out.println("The information you entered:");
        String str;

        /* the readLine method takes care of reading the byte data for us and putting in one line
         * naturally, it will call the low level read() method on the input stream which will move
         * the reading pointer along
        */
        while( (str = bfr.readLine()) != null ){ 
            System.out.println(str);
        }


    }
}
