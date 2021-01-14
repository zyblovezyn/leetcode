package designzen.ThirdPart.factoryplusstrategy;/**
 * @Description TODO
 *<p>
 * @ClassName FreeDebuction.java
 * @author Miles
 * @version 1.0.0
 * @createTime 2021-01-14 13:24:00
 */
public class FreeDeduction implements IDeduction {
    @Override
    public boolean exec(Card card, Trade trade) {
        card.setFreeMoney(card.getFreeMoney() - trade.getAmount());
        return true;
    }
}
