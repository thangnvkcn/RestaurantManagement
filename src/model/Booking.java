/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
/**
 *
 * @author Admin
 */
public class Booking implements Serializable{
    int id;
    private KhachHang client;
    private Ban table;
    private Date date;
    private Time startTime,endTime;
    private boolean isCheckin;
    public Booking() {
    }

    public Booking(KhachHang client, Ban table, Date date, Time startTime, Time endTime, boolean isCheckin) {
        this.client = client;
        this.table = table;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isCheckin = isCheckin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public KhachHang getClient() {
        return client;
    }

    public void setClient(KhachHang client) {
        this.client = client;
    }

    public Ban getTable() {
        return table;
    }

    public void setTable(Ban table) {
        this.table = table;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public boolean isIsCheckin() {
        return isCheckin;
    }

    public void setIsCheckin(boolean isCheckin) {
        this.isCheckin = isCheckin;
    }

   
    public boolean equals(Object obj) {
        if (obj instanceof Booking) {
            Booking another = (Booking) obj;
            if (this.client.equals(another.client)&&this.table.equals(another.table)&&this.date.equals(another.date)
                &&this.startTime.equals(another.startTime)&&this.endTime.equals(another.endTime)&&this.isCheckin==another.isCheckin    ){
                return true;
            }
        }
        return false;
    }
    
}
//