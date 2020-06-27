package designzen.builder.test2;

import java.util.ArrayList;

public class BMWBuilder extends CarBuilder {
    private BMWModel bwmzModel = new BMWModel();

    @Override
    public void setSequence(ArrayList<String> sequence) {
        this.bwmzModel.setSequence(sequence);
    }

    @Override
    public CarModel getCarModel() {
        return bwmzModel;
    }
}
