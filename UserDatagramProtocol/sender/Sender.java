import java.io.IOException;
import java.math.BigInteger;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Sender {

    /*
     * constants -- may need to change depending on if any of these are currently
     * in service
     */
    static final int RX_PORT_NUMBER = 2000; /* port that our sender socket lives on */
    static final int TX_PORT_NUMBER = 8000; /* port that we will be sending to */

    /* module variables */
    static DatagramSocket socket = null;

    public static void main(String[] args) {

        /*
         * get the data args (this is the data
         * that will be sent)
         */
        if (args.length < 2) {
            System.out.println("Sending data was not specified");
            System.exit(1);
        }

        /*
         * takes the first argument given during execution
         * and put into a byte array that will eventually
         * be stored in packet and send to receiver
         */
        byte[] data = args[0].getBytes();

        /* host should be the second argument */
        String host_name = args[1];

        /*
         * initialize the datagram packet and
         * datagram socket which will be used
         * for sending the data out to specified
         * InetAddress
         */
        DatagramPacket packet = new DatagramPacket(data, data.length);

        try {
            socket = new DatagramSocket(RX_PORT_NUMBER);
        } catch (SocketException e) {
            e.printStackTrace();
        }

        /* send the packet */
        int err = setUpDatagramPacket(packet, data, host_name);
        if (err != 0) {
            System.out.println("Data packet would not be sent. " +
                    "While setting up packet returned error of" + err);

            System.exit(1);
        }

        /* send the packet */
        sendPacket(packet);
    }

    /*
     * prepares the data to be sent
     * 
     * loads info such as:
     * - proper address and port info
     * - actual data
     */
    static int setUpDatagramPacket(DatagramPacket packet, byte[] buffer, String host_name) {

        /* set up data and size */
        packet.setLength(buffer.length);
        packet.setData(buffer);

        /* set up networking details */
        InetAddress remote_address = getHostName(host_name);

        if (host_name == null) {
            return 1;
        }

        packet.setAddress(remote_address);
        packet.setPort(TX_PORT_NUMBER);

        /*
         * indicate that there was no error setting up
         * the datagram packet
         */
        return 0;

    }

    /* helper to get the host name */
    static InetAddress getHostName(String host_name) {

        /* get the local host */
        InetAddress remote_address = null;
        try {
            remote_address = InetAddress.getByName(host_name);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        return remote_address;
    }

    /* send packet helper */
    static void sendPacket(DatagramPacket packet) {

        /* send the packet and catch any IO errors */
        try {
            socket.send(packet);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
