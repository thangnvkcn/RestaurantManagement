/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.Ban;

/**
 *
 * @author Admin
 */
public class BanDAO extends DAO{
    public BanDAO(){
        getInstance();
    }
    public Ban getTableById(int id){
        Ban t = null;
        String sql = "SELECT * FROM tblTable WHERE id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                t = new Ban();
                t.setId(rs.getInt("id"));
                t.setName(rs.getString("name"));
                t.setType(rs.getString("type"));
                t.setDescription(rs.getString("description"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }
}
