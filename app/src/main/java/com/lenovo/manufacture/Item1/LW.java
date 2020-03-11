package com.lenovo.manufacture.Item1;

public class LW {
    private int power;
    private String peopleName;
    private String status;

    @Override
    public String toString() {
        return "LW{" +
                "power=" + power +
                ", peopleName='" + peopleName + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public LW() {
    }

    public LW(int power, String peopleName, String status) {
        this.power = power;
        this.peopleName = peopleName;
        this.status = status;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public String getPeopleName() {
        return peopleName;
    }

    public void setPeopleName(String peopleName) {
        this.peopleName = peopleName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
