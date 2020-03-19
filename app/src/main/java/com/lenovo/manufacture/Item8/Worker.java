package com.lenovo.manufacture.Item8;

public class Worker {

    /**
     * id : 1
     * peopleName : 李刚
     * icon : null
     * status : 0
     * talentMarketId : 1
     * gold : 200
     * hp : 100
     * content : 汽车工程师
     */

    private int id;
    private String peopleName;
    private int status;
    private int hp;
    private String content;

    @Override
    public String toString() {
        return "Worker{" +
                "id=" + id +
                ", peopleName='" + peopleName + '\'' +
                ", status=" + status +
                ", hp=" + hp +
                ", content='" + content + '\'' +
                '}';
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
}
