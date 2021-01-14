package designzen.ThirdPart.factoryplusstrategy;

/**
 * @author Miles
 * @version 1.0.0
 * @Description TODO
 * <p>
 * @ClassName DebuctionFacade.java
 * @createTime 2021-01-14 13:50:00
 */
public class DeductionFacade {
    public static Card deduct(Card card, Trade trade) {
        StrategyMan reg = getDebuctionType(trade);
        IDeduction deduction = StrategyFactory.getDebuction(reg);
        DeductionContext context = new DeductionContext(deduction);
        context.exec(card, trade);
        return card;
    }

    private static StrategyMan getDebuctionType(Trade trade) {
        if (trade.getTradeNo().contains("abc")) {
            return StrategyMan.FreeDebuction;
        } else {
            return StrategyMan.SteadyDebuction;
        }
    }
}
