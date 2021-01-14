package designzen.ThirdPart.factoryplusstrategy;

/**
 * @author Miles
 * @version 1.0.0
 * @Description TODO
 * <p>
 * @ClassName StrategyFactory.java
 * @createTime 2021-01-14 13:45:00
 */
public class StrategyFactory {
    public static IDeduction getDebuction(StrategyMan strategyMan) {
        IDeduction debuction = null;
        try {
            debuction = (IDeduction) Class.forName(strategyMan.getValue()).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return debuction;
    }
}
