package designzen.ThirdPart.factoryplusstrategy;

/**
 * @author Miles
 * @version 1.0.0
 * @Description TODO
 * <p>
 * @ClassName IDeduction.java
 * @createTime 2021-01-14 13:11:00
 */
public interface IDeduction {
    public boolean exec(Card card, Trade trade);
}
