package struct.generic.genericclass;

import struct.generic.genericinterface.ManGenerator;

public class Generic {
    public <T> T getObject(Class<T> tClass) throws IllegalAccessException, InstantiationException {
        T t=tClass.newInstance();
        return t;
    }

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        Generic generic=new Generic();
        generic.getObject(ManGenerator.class);
    }
}
