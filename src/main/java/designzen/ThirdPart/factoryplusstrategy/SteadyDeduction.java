package designzen.ThirdPart.factoryplusstrategy;/**
 * @Description TODO
 *<p>
 * @ClassName SteadyDebuction.java
 * @author Miles
 * @version 1.0.0
 * @createTime 2021-01-14 13:12:00
 */
public class SteadyDeduction implements IDeduction {
    @Override
    public boolean exec(Card card, Trade trade) {
        int halfMoney= (int) Math.rint(trade.getAmount()/2.0);
        card.setFreeMoney(card.getFreeMoney() - halfMoney);
        card.setSteadyMoney(card.getSteadyMoney() - halfMoney);
        return true;
    }
}
