package DataStreams.buffered_reader;

/*
 * Author: Paul Gates, 2022
 * Materials from:
 *  Java Network Programming and Distributed Computing
 *  chapter 4
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class BufferedReaderDemo {

    public static void main(String[] args)throws IOException{

        /* probably the most important thing, using a Buffered reader to wrap a file reader for 
         * faster reading
         */
        BufferedReader br = new BufferedReader(new FileReader(new File("demo.txt")));

        String str;

        /* read the file line by line */
        System.out.println("\nReading the file line by line:");
        while( (str = br.readLine()) != null ){ /* can also do read() to read a single char */
            System.out.println("\t" + str);

            /* can also mark specific parts of the stream for use later */
            if(str.equals("second line of text")){
                br.mark(100);
            }
        }

        System.out.println("\nGoing back to marked portion...");
        /* go back to the marked text using reset() and then proceed to print out remainder */
        br.reset();
        while( (str = br.readLine()) != null ){ /* can also do read() to read a single char */
            System.out.println("\t" + str);
        }

        System.out.println();
    }


}
