package com.lenovo.manufacture.Item1;

public class Line {

    /**
     * id : 2534
     * userWorkId : 1
     * stageId : 40
     * productionLineId : 1
     * type : 0
     * position : 2
     * isAI : 1
     */

    private int id;
    private int position;

    @Override
    public String toString() {
        return "Line{" +
                "id=" + id +
                ", position=" + position +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
