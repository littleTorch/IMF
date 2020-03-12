package com.lenovo.manufacture.Item2;
import java.io.Serializable;

public class Shop implements Serializable {

    /**
     * id : 26
     * materialName : MPV方向盘
     * content : 新星汽车配件供货商
     * size : 1
     * icon : UI_fangxiangpan02
     * lastUpdateTime : 1568009900
     * supplyId : 1
     * materialId : 26
     * price : 1100
     * num : 2
     * costTime : 3
     * supplyName : 新星汽车配件
     * supplyListId : 1
     */

    private int id;
    private String materialName;
    private String content;
    private int price;
    private int num;

    @Override
    public String toString() {
        return "Shop{" +
                "id=" + id +
                ", materialName='" + materialName + '\'' +
                ", content='" + content + '\'' +
                ", price=" + price +
                ", num=" + num +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

}
