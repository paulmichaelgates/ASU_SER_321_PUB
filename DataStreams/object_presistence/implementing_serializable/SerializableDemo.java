package object_presistence.implementing_serializable;

/*
 * Author: Paul Gates, 2022
 * Materials from:
 *  Java Network Programming and Distributed Computing
 *  chapter 4
 */
import java.io.Serializable;

public class SerializableDemo implements Serializable{

    /* sometimes there are things which you do not want to be serialized
     * because ultimatley, they will be saved somewhere where they can
     * be read from without you knowing
     * 
     * in this case you can use the transient keyword
     * 
     * Note:
     *  static fields are also not used to read in data as these members
     *  are used for every object of the class and would otherwise be
     *  overwritten and put into the state of the read-in object, which
     *  may not make sense depending on the application
     */
    private transient String sensitive_data;  //! will not be serialized

    /* Default ctor is necessary for serialization of object by the JVM */
    public SerializableDemo(){}
}
