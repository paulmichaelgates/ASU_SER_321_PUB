import java.io.DataInputStream;
import java.lang.IllegalArgumentException;

import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.SocketException;
import java.net.InetAddress;
import java.net.Socket;

import javax.sql.DataSource;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public class Receiver {

    static final int STD_DATA_SIZE = 256; /* 256 byte data limit */
    static final int PORT_NUMBER = 8000; /* port used for socket */

    /*
     * define socket which is the software interface to the networking
     * protocols responsible for the rx and tx of data packets
     */
    static DatagramSocket dataSocket = null;
    static DatagramPacket packet = null;

    public static void main(String[] args) {

        try {
            dataSocket = new DatagramSocket(PORT_NUMBER);
        } catch (SocketException e) {
            e.printStackTrace();
        }

        /*
         * create an array of bytes (buffer pointer) which we can use to store
         * the data we will send/receive
         */
        byte[] data = new byte[STD_DATA_SIZE];

        /*
         * create a datagram packet which will act as the container for all
         * of our sending and receiving data. i.e., we will use this same object
         * for when we get data from some other application (Sender.java) that we
         * will use to send data to.
         */
        packet = new DatagramPacket(data, STD_DATA_SIZE);

        rxPacketData();
    }

    /* method to recieve packet */
    static void rxPacketData() {
        /*
         * although it may seem like magic, receiving data is a blocking operation
         * which means that the program will hang until something allows it to continue
         * 
         * this is kind of like a busy loop ( e.g., while(1); ) or attempting to aquire
         * a lock for a semaphore or mutex
         */
        try {

            if (dataSocket != null) {
                dataSocket.receive(packet);
            } else {
                throw new IllegalArgumentException("dataSocket was not initialized correctly");
            }

        } catch (IOException e) {
            e.printStackTrace();

        } catch (IllegalArgumentException e) {
            e.printStackTrace();

        }

        System.out.println("packet was recieved on port " + PORT_NUMBER);

        /* open up the packet contents */
        parsePacketData();
    }

    /* parse out the data that was received */
    static void parsePacketData() {

        /*
         * get the address and put in a (sometimes) nicer string
         * format
         */
        InetAddress remote_address = packet.getAddress();
        String sender_address = remote_address.getHostAddress();

        System.out.println("Sender address: " + sender_address);
        System.out.println("Sent from port: " + packet.getPort());

        /*
         * wrap the data packet usin a ByteArrayInputStream which is
         * inherits from DataInputStream (inheriting from lower level
         * InputStream -- see DataStreams directory for this repo)
         * and is used to create an easy interface to reading byte streamed
         * data
         */
        ByteArrayInputStream byteIStream = new ByteArrayInputStream(packet.getData());

        /*
         * display the data
         * note that we only want to display the length of the packet
         * this value is effectively set by the sender, that is, it is
         * within the packet and then the java.net.DataSocket method
         * to recieve packets will set the length after parsing the packet
         */
        for (int i = 0; i < packet.getLength(); i++) {
            int data = byteIStream.read();

            if (data == -1) {
                break;
            }

            System.out.print((char) data);
        }

        dataSocket.close();
    }
}
