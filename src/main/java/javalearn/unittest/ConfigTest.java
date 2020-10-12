package javalearn.unittest;

public class ConfigTest {
    public String getConfigFile(String filename) {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            return "C:\\" + filename;
        }
        if (os.contains("mac") || os.contains("linux")) {
            return "/user/local/" + filename;
        }
        throw new UnsupportedOperationException();
    }
}
