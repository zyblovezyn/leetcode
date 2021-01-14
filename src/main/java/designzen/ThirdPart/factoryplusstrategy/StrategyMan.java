package designzen.ThirdPart.factoryplusstrategy;

/**
 * @author Miles
 * @version 1.0.0
 * @Description TODO
 * <p>
 * @ClassName StrategyMan.java
 * @createTime 2021-01-14 13:37:00
 */
public enum  StrategyMan {
    SteadyDebuction("designzen.ThirdPart.factoryplusstrategy.SteadyDebuction"),
    FreeDebuction("designzen.ThirdPart.factoryplusstrategy.FreeDebuction");

    String value = "";
    StrategyMan(String value) {
        this.value=value;
    }

    public String getValue() {
        return this.value;
    }
}
