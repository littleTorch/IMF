package com.lenovo.manufacture.Item8;

public class LW {
    private String peopleName;
    private int status;
    private int hp;
    private int position;

    public LW(String peopleName, int status, int hp, int position) {
        this.peopleName = peopleName;
        this.status = status;
        this.hp = hp;
        this.position = position;
    }

    @Override
    public String toString() {
        return "LW{" +
                "peopleName='" + peopleName + '\'' +
                ", status=" + status +
                ", hp=" + hp +
                ", position=" + position +
                '}';
    }

    public String getPeopleName() {
        return peopleName;
    }

    public void setPeopleName(String peopleName) {
        this.peopleName = peopleName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
