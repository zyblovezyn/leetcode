package designzen.ThirdPart.factoryplusstrategy;

/**
 * @author Miles
 * @version 1.0.0
 * @Description TODO
 * <p>
 * @ClassName DebuctionContext.java
 * @createTime 2021-01-14 13:27:00
 */
public class DeductionContext {
    private IDeduction debuction = null;

    public DeductionContext(IDeduction debuction) {
        this.debuction = debuction;
    }

    public boolean exec(Card card, Trade trade) {
        return this.debuction.exec(card, trade);
    }
}
