package struct.enumtest;

public class Client {
    public static void main(String[] args) {
        for (Food.Dessert dessert : Food.Dessert.values()) {
            System.out.println("dessert = " + dessert);
        }
        System.out.println();
        for (Food.Coffe coffe : Food.Coffe.values()) {
            System.out.println("coffe = " + coffe);
        }

        Food food = Food.Dessert.CAKE;
        System.out.println("food = " + food);
        food = Food.Coffe.BLACK_COFFEE;
        System.out.println("food = " + food);
    }
}
