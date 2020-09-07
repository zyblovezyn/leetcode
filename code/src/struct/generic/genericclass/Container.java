package struct.generic.genericclass;

public class Container<K, V> {
    private K key;
    private V value;

    public Container(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public static void main(String[] args) {
        Container<String, String> c = new Container<>("name", "sex");
        System.out.println(c.getKey()+" : "+c.getValue());
    }
}
