import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/*
 * Author: Paul Gates, 2022
 * Materials from:
 *  Java Network Programming and Distributed Computing
 *  chapter 4
 * 
 * Description:
 *  example of how to store an instance of class that
 *  implements Serializable and retrieve it
 * 
 *  how it works:
 *      1.  the program will attempt to look for the object data in the binary file
 *          "filedata.out".
 *      2.  if it is able to find the file and successfully create the FileData object
 *          from said data, it will print out the previous data_field member that was in
 *          the file
 *      3.  if it does not find the file or cannot read the object in, then it will print
 *          a message notifying the user of such
 *      4. finally, it will prompt the user to overrite the object data 
 */
public class SerializedDemo {
    /**
     * @param args
     */
    public static void main(String[] args){

        /* wrap the ssystem input stream in an input reader and wrap that in buffer reader
         * for better performance
         * 
         * see buffered_reader/BufferedReaderDemo.java file for explanation
         */
        BufferedReader userIn = new BufferedReader(new InputStreamReader(System.in));

        SerializedDemo app = new SerializedDemo();

        boolean previousDataAvailable = false;

        FileData fileData = null;
        
        try{
            /* check to see if object is already stored */
            FileInputStream fileIn = new FileInputStream("filedata.out");

            /* connect an object input stream so if the file exists, then we 
             * can try and read the data using this object
            */
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            
            fileData = (FileData) objectIn.readObject();

            fileIn.close();

            /* data was found without exception */
            previousDataAvailable = true; 

        }catch(FileNotFoundException e){
            /* could not locate the object, go ahead and create one */
            fileData = new FileData();

        }catch(IOException e){
            /* could not read from the file */
            fileData = new FileData();

        }catch(ClassCastException e){
            /* cast from Object to FileData failed */
            fileData = new FileData();

        }catch(ClassNotFoundException e){
            /* JVM could not find a reference to the class */
            fileData = new FileData();
        }

        /* get the user input */
        if(previousDataAvailable){
            System.out.println("The previous file data was: " + fileData.data_field);
            System.out.println("Overrite data? [y, n]");
        }else{
            System.out.println("There was not reference to a previously stored object.");
            System.out.println("write new object? [y, n]");
        }

        String response = null;

        try{
            response = userIn.readLine();
        }catch(IOException e){
            e.printStackTrace();
        }

        if( (response == null) || ( response.toUpperCase().charAt(0) ) == 'N'){
            System.out.println("Have a nice day.");
            System.exit(0);
        }

        /* get the altered data from the user and write out the object */
        System.out.println("please enter data for object:");

        try{
            fileData.data_field = userIn.readLine();
            FileOutputStream fileOS = new FileOutputStream("filedata.out");
            ObjectOutputStream objectOS = new ObjectOutputStream((fileOS));
    
            objectOS.writeObject(fileData);
    
            System.out.println("Data has been written.");
            System.exit(0);

        }catch(Exception e){

            /* just catch any ol' exception and print stack trace */
            e.printStackTrace();
        }
        
    }
}
