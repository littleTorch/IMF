package com.lenovo.manufacture.Item1;

import android.widget.ScrollView;

public class Worker {

    /**
     * id : 1
     * peopleName :
     * icon :
     * status :
     * talentMarketId :
     * gold :
     * hp :
     * content :
     */

    private int id;
    private String peopleName;
    private String icon;
    private String status;
    private String hp;

    @Override
    public String toString() {
        return "Worker{" +
                "id=" + id +
                ", peopleName='" + peopleName + '\'' +
                ", icon='" + icon + '\'' +
                ", status='" + status + '\'' +
                ", hp='" + hp + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPeopleName() {
        return peopleName;
    }

    public void setPeopleName(String peopleName) {
        this.peopleName = peopleName;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getHp() {
        return hp;
    }

    public void setHp(String hp) {
        this.hp = hp;
    }
    //0、工程师，1、工人，2、技术人员，3、检测人员
    public String getS(){
        switch (status){
            case "0":
                return "工程师";
            case "1":
                return "工人";
            case "2":
                return "技术人员";
            case "3":
                return "检测人员";
        }
        return status;
    }
}
