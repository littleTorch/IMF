package com.lenovo.manufacture.Item1;

public class LineWorker {

    /**
     * id : 3856
     * userWorkId : 1
     * power : 100
     * peopleId : 8
     * userProductionLineId : 2534
     * workPostId :
     */

    private int power;
    private int peopleId;
    private int userProductionLineId;

    @Override
    public String toString() {
        return "LineWorker{" +
                "power=" + power +
                ", peopleId=" + peopleId +
                ", userProductionLineId=" + userProductionLineId +
                '}';
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getPeopleId() {
        return peopleId;
    }

    public void setPeopleId(int peopleId) {
        this.peopleId = peopleId;
    }

    public int getUserProductionLineId() {
        return userProductionLineId;
    }

    public void setUserProductionLineId(int userProductionLineId) {
        this.userProductionLineId = userProductionLineId;
    }
}
