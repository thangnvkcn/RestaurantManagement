/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import model.PhieuOrder;


/**
 *
 * @author Admin
 */
public class PhieuOrderDAO extends DAO{
    public PhieuOrderDAO() {
        getInstance();
    }
    public int savePhieuOrder(PhieuOrder p){
        int returnValue = 0;
        String sql = "INSERT INTO tblPhieuOrder VALUES(?,?,?)";
        try {
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
         
            ps.setInt(1,p.getId());
            ps.setInt(2, p.getBooking().getId());
            ps.setString(3,p.getThoigian());
            
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                returnValue = rs.getInt(1);
                p.setId(returnValue);
            }
             saveUsedFood(p);
             saveUsedCombo(p);
             con.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            try {
                con.rollback();
                con.setAutoCommit(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return returnValue;
    }
    public int saveUsedFood(PhieuOrder p){
        int returnValue = 0;
        String sql = "INSERT INTO tblUsedFood(quantity,idFood,idPhieuOrder) VALUES(?,?,?)";
        try {
            
            
            for(int i=1;i<=p.getUsedSelectionFood().size();i++){
                if(p.getUsedSelectionFood().get(i-1).getSelectionFood().getFood()!=null){
                    con.setAutoCommit(false);
                    PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
                    ps.setInt(1, p.getUsedSelectionFood().get(i-1).getQuantity());
                    ps.setInt(2, p.getUsedSelectionFood().get(i-1).getSelectionFood().getFood().getId());
                    ps.setInt(3,p.getId());
                    ps.executeUpdate();
                    ResultSet rs = ps.getGeneratedKeys();
                    if(rs.next()){
                        returnValue = rs.getInt(1);
                    }
                    con.commit();
                }
            }
            
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            try {
                con.rollback();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return returnValue;
    }
    public int saveUsedCombo(PhieuOrder p){
        int returnValue = 0;
        String sql = "INSERT INTO tblUsedCombo(quantity,idCombo,idPhieuOrder) VALUES(?,?,?)";
        try {
            
            for(int i=1;i<=p.getUsedSelectionFood().size();i++){
                if(p.getUsedSelectionFood().get(i-1).getSelectionFood().getCombo()!=null){
                    con.setAutoCommit(false);
                    PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
                    ps.setInt(1, p.getUsedSelectionFood().get(i-1).getQuantity());
                    ps.setInt(2, p.getUsedSelectionFood().get(i-1).getSelectionFood().getCombo().getId());
                    ps.setInt(3,p.getId());
                    ps.executeUpdate();
                    ResultSet rs = ps.getGeneratedKeys();
                    if(rs.next()){
                        returnValue = rs.getInt(1);
                    }
                    con.commit();
                }
            }
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            try {
                con.rollback();
            
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return returnValue;
    }
   
}
