package designpattern.factory.SingleFactory.pizafactory;

public abstract class Pisa {
    protected String name;
    public abstract void prepare();

    public void bake() {
        System.out.println(name+"baking;");
    }
    public void cut() {
        System.out.println(name+"cut;");
    }
    public void box() {
        System.out.println(name+"box;");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
