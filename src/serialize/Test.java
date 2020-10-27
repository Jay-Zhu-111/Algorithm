package serialize;

import java.beans.DesignMode;
import java.io.*;

public class Test {

    public static void main(String[] args) {
        deserialize();
    }

    public static void deserialize(){
        Employee e;
        try {
            FileInputStream fileIn = new FileInputStream("employee.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            e = (Employee) in.readObject();
            in.close();
            fileIn.close();
        }catch (IOException ioException){
            ioException.printStackTrace();
            return;
        }catch (ClassNotFoundException classNotFoundException){
            System.out.println("class not found");
            classNotFoundException.printStackTrace();
            return;
        }

        System.out.println(e.name);
        System.out.println(e.address);
        System.out.println(e.SSN);
        System.out.println(e.number);
    }

    public static void serialize(){
        Employee e = new Employee();
        e.name = "ename";
        e.address = "111, Beijing";
        e.SSN = 11233;
        e.number = 101;

        try {
            FileOutputStream fileOut = new FileOutputStream("employee.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(e);
            out.close();
            fileOut.close();
        }catch (IOException ioException){
            ioException.printStackTrace();
        }
    }
}
