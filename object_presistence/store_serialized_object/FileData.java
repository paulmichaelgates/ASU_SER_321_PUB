import java.io.Serializable;

public class FileData implements Serializable{
    
    /* public members will be saved */
    public String data_field;

    /* transient members will not be saved */
    private transient String sensitive_data;

    public FileData(String data){
        data_field = data;
    }

    /* deafult ctor is required for serializable interface */
    public FileData(){}
}