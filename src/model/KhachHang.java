/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author Admin
 */
public class KhachHang implements Serializable{
    private int id;
    private int idCard;
    private String name;
    private String address;
    private String phone;
    private String email;

    public KhachHang() {
    }

    public KhachHang(int idCard, String name, String address, String phone, String email) {
        this.idCard = idCard;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCard() {
        return idCard;
    }

    public void setIdCard(int idCard) {
        this.idCard = idCard;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public boolean equals(Object obj) {
        if (obj instanceof KhachHang) {
            KhachHang another = (KhachHang) obj;
            if (this.idCard==another.idCard&&this.name.equals(another.name)&&this.address.equals(another.address)&&
                    this.phone.equals(another.phone)&&this.email.equals(another.email)) {
                return true;
            }
        }
        return false;
    }
}
