package struct;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class DeserializedDemo {
    public static void main(String[] args) {
        Employee employee = null;
        try {
            FileInputStream fileInputStream = new FileInputStream("/temp/emplyee.ser");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            employee = (Employee) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Deserialized Employee...");
        System.out.println("Name: " + employee.name);
        System.out.println("Address: " + employee.address);
        System.out.println("SSN: " + employee.SSN);
        System.out.println("Number: " + employee.number);
    }
}
