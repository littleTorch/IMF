package com.lenovo.manufacture.Item8;

public class LineWorker {

    /**
     * id : 3912
     * userWorkId : 1
     * power : 52
     * peopleId : 8
     * userProductionLineId : 2546
     * workPostId : 4
     */

    private int id;
    private int power;
    private int peopleId;
    private int userProductionLineId;
    private int workPostId;

    @Override
    public String toString() {
        return "LineWorker{" +
                "id=" + id +
                ", power=" + power +
                ", peopleId=" + peopleId +
                ", userProductionLineId=" + userProductionLineId +
                ", workPostId=" + workPostId +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getWorkPostId() {
        return workPostId;
    }

    public void setWorkPostId(int workPostId) {
        this.workPostId = workPostId;
    }
}
