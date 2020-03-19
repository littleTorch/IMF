package com.lenovo.manufacture.Item8;

public class Line {

    /**
     * id : 2548
     * userWorkId : 1
     * stageId : 5
     * productionLineId : 2
     * type : 0
     * position : 1
     * isAI : 0
     */

    private int id;
    private int productionLineId;
    private int position;

    @Override
    public String toString() {
        return "Line{" +
                "id=" + id +
                ", productionLineId=" + productionLineId +
                ", position=" + position +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductionLineId() {
        return productionLineId;
    }

    public void setProductionLineId(int productionLineId) {
        this.productionLineId = productionLineId;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
