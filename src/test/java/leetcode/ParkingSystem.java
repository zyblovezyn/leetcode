package leetcode;

/**
 * @author Miles
 * @version 1.0.0
 * @Description TODO
 * <p>
 * @ClassName ParkingSystem.java
 * @createTime 2020-12-14 20:29:00
 */
public class ParkingSystem {
    private int big;
    private int medium;
    private int small;

    public int getBig() {
        return big;
    }

    public void setBig(int big) {
        this.big = big;
    }

    public int getMedium() {
        return medium;
    }

    public void setMedium(int medium) {
        this.medium = medium;
    }

    public int getSmall() {
        return small;
    }

    public void setSmall(int small) {
        this.small = small;
    }

    public ParkingSystem(int big, int medium, int small) {
        this.setBig(big);
        this.setMedium(medium);
        this.setSmall(small);
    }

    public boolean addCar(int carType) {
        boolean hasSpace = false;
        switch (carType) {
            case 1:
                hasSpace = this.getBig() > 0;
                if (hasSpace)
                    this.setBig(this.getBig() - 1);
                break;
            case 2:
                hasSpace = this.getMedium() > 0;
                if (hasSpace)
                    this.setMedium(this.getMedium() - 1);
                break;
            case 3:
                hasSpace = this.getSmall() > 0;
                if (hasSpace)
                    this.setSmall(this.getSmall() - 1);
                break;
        }

        return hasSpace;
    }


}
