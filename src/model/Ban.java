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
public class Ban implements Serializable{
    private int id;
    private String name;
    private String type;
    private String description;

    public Ban() {
    }

    public Ban(String name, String type, String description) {
        this.name = name;
        this.type = type;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public boolean equals(Object obj) {
        if (obj instanceof Ban) {
            Ban another = (Ban) obj;
            if (this.name.equals(another.name)&&this.type.equals(another.type)&&this.description.equals(another.description)) {
                return true;
            }
        }
        return false;
    }
}
