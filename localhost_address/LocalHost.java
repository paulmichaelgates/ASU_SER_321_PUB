import java.net.InetAddress;
import java.net.UnknownHostException;

public class LocalHost{
    
    public LocalHost(){

    } 

    public String getLocalHostName(){
        String addres_str = "";

        try{
            /* get the local host */
            addres_str = InetAddress.getLocalHost().getHostAddress();

        }catch(UnknownHostException e){
            e.printStackTrace();
        }
    
        return addres_str;

    }
    
    public static void main(String[] args){
        LocalHost app = new LocalHost();

        System.out.println("Local Host=" +  app.getLocalHostName());
    }
}