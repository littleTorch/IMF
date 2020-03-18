package com.lenovo.manufacture.Item7;

public class PutCar {

    /**
     * id : 20929
     * userFactoryId : 1
     * carTypeId : 2
     * price : 3000
     * time : 1584455569
     * num : 1
     * lastUpdateTime : null
     * carTypeName : MPV汽车
     */

    private int carTypeId;
    private int price;
    private int time;
    private int num;
    private String carTypeName;

    @Override
    public String toString() {
        return "PutCar{" +
                "carTypeId=" + carTypeId +
                ", price=" + price +
                ", time=" + time +
                ", num=" + num +
                ", carTypeName='" + carTypeName + '\'' +
                '}';
    }

    public int getCarTypeId() {
        return carTypeId;
    }

    public void setCarTypeId(int carTypeId) {
        this.carTypeId = carTypeId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getCarTypeName() {
        return carTypeName;
    }

    public void setCarTypeName(String carTypeName) {
        this.carTypeName = carTypeName;
    }
}
