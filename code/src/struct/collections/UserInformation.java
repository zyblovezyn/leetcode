package struct.collections;

import java.io.Serializable;

public class UserInformation implements Serializable {

    private static final long serialVersionUID = 7290518069235547324L;

    public String name;
    public transient String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
