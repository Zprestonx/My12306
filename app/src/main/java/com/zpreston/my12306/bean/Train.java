package com.zpreston.my12306.bean;

/**
 * Created by preston on 2016/7/20.
 */
public class Train {
    private int id;
    private String trainNo;//车次
    private String startStationName;
    private String endStationName;
    private String startTime; //格式为 22:02
    private String arriveTime;
    private String startDate; //出发日期 2016-07-20
    private int seats;
    private double price;

    public Train()
    {}
    public Train(int id, String trainNo, String startStationName, String endStationName, String startTime, String arriveTime, String startDate, int seats, double price) {
        this.id = id;
        this.trainNo = trainNo;
        this.startStationName = startStationName;
        this.endStationName = endStationName;
        this.startTime = startTime;
        this.arriveTime = arriveTime;
        this.startDate = startDate;
        this.seats = seats;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTrainNo() {
        return trainNo;
    }

    public void setTrainNo(String trainNo) {
        this.trainNo = trainNo;
    }

    public String getStartStationName() {
        return startStationName;
    }

    public void setStartStationName(String startStationName) {
        this.startStationName = startStationName;
    }

    public String getEndStationName() {
        return endStationName;
    }

    public void setEndStationName(String endStationName) {
        this.endStationName = endStationName;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(String arriveTime) {
        this.arriveTime = arriveTime;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Train{" +
                "id=" + id +
                ", trainNo='" + trainNo + '\'' +
                ", startStationName='" + startStationName + '\'' +
                ", endStationName='" + endStationName + '\'' +
                ", startTime='" + startTime + '\'' +
                ", arriveTime='" + arriveTime + '\'' +
                ", startDate='" + startDate + '\'' +
                ", seats=" + seats +
                ", price=" + price +
                '}';
    }
}
