package struct.collections;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class TransientTest {
    public static void main(String[] args) {
        UserInformation userInformation = new UserInformation();
        userInformation.setName("zhang");
        userInformation.setPassword("zyb123");

        System.out.println(userInformation.getPassword());

        try {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("D:/user.txt"));
            os.writeObject(userInformation);
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            ObjectInputStream oi = new ObjectInputStream(new FileInputStream("D:/user.txt"));
            userInformation = (UserInformation) oi.readObject();
            oi.close();
            System.out.println(userInformation.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


