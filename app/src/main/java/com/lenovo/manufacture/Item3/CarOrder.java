package com.lenovo.manufacture.Item3;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CarOrder {

    /**
     * id : 1
     * userWorkId :
     * userAppointmentName :
     * content :
     * type :
     * carId :
     * time :
     * num :
     * gold :
     * engine :
     * speed :
     * wheel :
     * control :
     * brake :
     * hang :
     * color :
     */

    private int id;
    private int carId;
    private long time;
    private int num;
    private int engine;
    private int speed;
    private int wheel;
    private int control;
    private int brake;
    private int hang;

    @Override
    public String toString() {
        return "CarOrder{" +
                "id=" + id +
                ", carId='" + carId + '\'' +
                ", time='" + time + '\'' +
                ", num='" + num + '\'' +
                ", engine='" + engine + '\'' +
                ", speed='" + speed + '\'' +
                ", wheel='" + wheel + '\'' +
                ", control='" + control + '\'' +
                ", brake='" + brake + '\'' +
                ", hang='" + hang + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCarId() {
        switch (carId){
            case 1:
                return "轿车汽车";
            case 2:
                return "MPV汽车";
            case 3:
                return "SUV汽车";
        }
        return carId+"";
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public String getTime() {
        return new SimpleDateFormat("yyyy/MM/dd").format(new Date(time));
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getEngine() {
        switch (engine){
            case 0:
                return "1.0";
            case 1:
                return "1.5";
            case 2:
                return "2.0";
            case 3:
                return "2.5";
            case 4:
                return "3.0";
            case 5:
                return "4.0";
        }
        return engine+"";
    }

    public void setEngine(int engine) {
        this.engine = engine;
    }

    public String getSpeed() {
        switch (speed){
            case 0:
                return "自动";
            case 1:
                return "手动";
        }
        return speed+"";
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public String getWheel() {
        switch (wheel){
            case 0:
                return "烤漆";
            case 1:
                return "电镀";
        }
        return wheel+"";
    }

    public void setWheel(int wheel) {
        this.wheel = wheel;
    }

    public String getControl() {
        switch (control){
            case 0:
                return "低配";
            case 1:
                return "高配";
        }
        return control+"";
    }

    public void setControl(int control) {
        this.control = control;
    }

    public String getBrake() {
        switch (brake){
            case 0:
                return "鼓式制动器";
            case 1:
                return "盘式制动器";
        }
        return brake+"";
    }

    public void setBrake(int brake) {
        this.brake = brake;
    }

    public String getHang() {
        switch (hang){
            case 0:
                return "独立悬挂系统";
            case 1:
                return "主动悬挂系统";
            case 2:
                return "横臂式悬挂系统";
            case 3:
                return "纵臂式悬挂系统";
            case 4:
                return "烛式悬挂系统";
            case 5:
                return "多连杆式悬挂系统";
            case 6:
                return "麦弗逊式悬挂系统";
        }
        return hang+"";
    }

    public void setHang(int hang) {
        this.hang = hang;
    }
}
