/*
 * Author: Paul Gates, 2022
 * Materials from:
 *  Java Network Programming and Distributed Computing
 *  chapter 4
 */

import java.net.InetAddress;
import java.net.UnknownHostException;

public class NetworkResolver {
    public static void main(String[] args){

        InetAddress address = null;

        if(args.length != 1){
            System.out.println("invalid arguments provided");

            System.exit(1);
        }

        try{
            /* try and open using the given hostname */
            address = InetAddress.getByName(args[0]);

        }catch(UnknownHostException e){
            e.printStackTrace();
        }finally{
            if(address != null){
                System.out.println("successfully able to find host " + address.toString());
            }else{
                System.out.println("was not able to find the host guven the name provided.");
            }
        }
    }
 }