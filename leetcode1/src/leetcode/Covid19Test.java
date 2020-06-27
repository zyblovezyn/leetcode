package leetcode;

public abstract class Covid19Test {

    private Integer testNum;
    private Integer spendTimeForOne;
    private double rate;

    public Covid19Test() {
    }

    public Covid19Test(Integer testNum, Integer spendTimeForOne, double rate) {
        this.testNum = testNum;
        this.spendTimeForOne = spendTimeForOne;
        this.rate = rate;
    }

    public Integer getSpendTimeForAll() {
        Integer spendTimeForAll = 0;
        try {
            spendTimeForAll = spendTimeForOne * testNum;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return spendTimeForAll;
    }

    public Integer getEstimateCases() {
        Integer EstimateCases = 0;
        try {
            EstimateCases = spendTimeForOne * testNum;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return EstimateCases;
    }

    public Integer getTestNum() {
        return testNum;
    }

    public Integer getRealTestNum() {
        return 0;
    }

    private Integer getMinTestNum() {
        int testCount = 0;
        for (int i = 2; i < 100; i++) {
            int splitNum = i;

        }
        return testNum;
    }

    private Integer testNum(Integer splitNum) {
        int allcount = 0;
        return allcount;
    }

    public Integer getRealSpendTimeForAll() {
        Integer realTestNum = getRealTestNum();
        return realTestNum * spendTimeForOne;
    }
}
