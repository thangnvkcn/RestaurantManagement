/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Combo;
import model.SelectionFood;
import model.Food;
import model.Ban;


/**
 *
 * @author Admin
 */
public class SelectionFoodDAO extends DAO{

    public SelectionFoodDAO() {
        getInstance();
    }
//    public ArrayList<SelectionFood> searchFoodByName(String name){
//        ArrayList<SelectionFood> listSelectionFood = new ArrayList<SelectionFood>();
//        String sql = "SELECT * FROM tblSelectionFood (WHERE idFood = (SELECT id FROM tblFood WhERE name LIKE ?) OR idFood = (SELECT id FROM tblCombo WhERE name LIKE ?)) ";
//        try {
//            PreparedStatement ps = con.prepareStatement(sql);
// 
//            ResultSet rs = ps.executeQuery();
//            while(rs.next()){
//                SelectionFood sfood = new SelectionFood();
//                sfood.setId(rs.getInt("id"));
//                Food food = new Food();
//                food =getFoodById(rs.getInt("idFood"));
//                sfood.setFood(food);
//                Table t = new Table();
//                TableDAO td = new TableDAO();
//                t = td.getTableById(rs.getInt("idTable"));
//                tmp.setTable(t);
//                tmp.setIsCheckin(rs.getBoolean("IsCheckIn"));
//                tmp.setDate(rs.getDate("date"));
//                tmp.setStartTime(rs.getTime("startTime"));
//                tmp.setEndTime(rs.getTime("endTime"));
//                if(t.getName().contains(nameTable)){
//                listBooking.add(tmp);
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return listBooking;
//    
//    
//    }   
   public ArrayList<Food> getFoodByName(String name){
        ArrayList<Food> listFood = new ArrayList<Food>();
        String sql = "SELECT * FROM tblFood WHERE name LIKE ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,"%"+name+"%");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Food f = new Food();
                f.setId(rs.getInt("id"));
                f.setName(rs.getString("name"));
                f.setType(rs.getString("type"));
                f.setPrice(rs.getDouble("price"));
                f.setDescription(rs.getString("des"));
                listFood.add(f);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listFood;
    }
   public ArrayList<Combo> getComboByName(String name){
        ArrayList<Combo> listCombo = new ArrayList<Combo>();
        String sql = "SELECT * FROM tblCombo WHERE name LIKE ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,"%"+name+"%");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Combo c = new Combo();
                c.setId(rs.getInt("id"));
                c.setName(rs.getString("name"));
                c.setType(rs.getString("type"));
                c.setPrice(rs.getDouble("price"));
                c.setDescription(rs.getString("des"));
                listCombo.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listCombo;
    }
}
