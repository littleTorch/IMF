package com.lenovo.manufacture.Item5;

public class SM {

    /**
     * id : 5
     * plStepName : MPV车型生产环节1
     * step : 1
     * power : 100
     * consume : 30
     * icon : line02_01
     */

    private int id;
    private String plStepName;
    private int step;
    private int power;
    private int consume;

    @Override
    public String toString() {
        return "SM{" +
                "id=" + id +
                ", plStepName='" + plStepName + '\'' +
                ", step=" + step +
                ", power=" + power +
                ", consume=" + consume +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlStepName() {
        return plStepName;
    }

    public void setPlStepName(String plStepName) {
        this.plStepName = plStepName;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getConsume() {
        return consume;
    }

    public void setConsume(int consume) {
        this.consume = consume;
    }
}
