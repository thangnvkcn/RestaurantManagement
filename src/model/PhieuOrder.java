/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Admin
 */
public class PhieuOrder implements Serializable{
    private int id;
    private ArrayList<UsedSelectionFood> usedSelectionFood;
    private Booking booking;
    private String thoigian;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<UsedSelectionFood> getUsedSelectionFood() {
        return usedSelectionFood;
    }

    public void setUsedSelectionFood(ArrayList<UsedSelectionFood> usedSelectionFood) {
        this.usedSelectionFood = usedSelectionFood;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public String getThoigian() {
        return thoigian;
    }

    public void setThoigian(String thoigian) {
        this.thoigian = thoigian;
    }
    
}
