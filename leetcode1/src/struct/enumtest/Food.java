package struct.enumtest;

public interface Food {
    enum Coffe implements Food {
        BLACK_COFFEE,DECAF_COFFEE,LATTE,CAPPUCCINO
    }

    enum Dessert implements Food {
        FRUIT, CAKE, GELATO
    }
}
