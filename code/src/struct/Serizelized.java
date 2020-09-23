package struct;

import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Serizelized {
    public static void main(String[] args) {
        Employee employee=new Employee();
        employee.setName("Reyan Ali");
        employee.setAddress("Phokka Kuan, Ambehta Peer");
        employee.setSSN(111222333);
        employee.setNumber(101);
        try {
            FileOutputStream fileOut = new FileOutputStream("/temp/emplyee.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(employee);
            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved in /temp/emplyee.ser");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
