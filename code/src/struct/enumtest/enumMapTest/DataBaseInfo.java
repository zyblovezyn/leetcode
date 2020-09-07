package struct.enumtest.enumMapTest;

import java.util.EnumMap;

enum DataBaseType {
    MYSQL, ORACLE, DB2, SQLSERVER
}

public class DataBaseInfo {
    private EnumMap<DataBaseType, String> urls = new EnumMap<DataBaseType, String>(DataBaseType.class);

    public DataBaseInfo() {
        urls.put(DataBaseType.DB2, "jdbc:db2://localhost:5000/sample");
        urls.put(DataBaseType.MYSQL, "jdbc:mysql://localhost/mydb");
        urls.put(DataBaseType.ORACLE, "jdbc:oracle:thin:@localhost:1521:sample");
        urls.put(DataBaseType.SQLSERVER, "jdbc:microsoft:sqlserver://sql:1433;Database=mydb");
    }

    public String getUrl(DataBaseType type) {
        return this.urls.get(type);
    }
}
