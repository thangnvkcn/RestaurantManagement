/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import model.KhachHang;

/**
 *
 * @author Admin
 */
public class KhachDAO extends DAO {


    public KhachDAO() {
        getInstance();
    }
    public ArrayList<KhachHang> searchClient(String s){
        ArrayList<KhachHang> listClient = new ArrayList<KhachHang>();
        String sql = "SELECT * FROM tblClient WHERE name LIKE ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,"%"+s+"%");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                KhachHang c = new KhachHang();
                c.setId(rs.getInt("id"));
                c.setIdCard(rs.getInt("idCard"));
                c.setName(rs.getString("name"));
                c.setAddress(rs.getString("address"));
                c.setPhone(rs.getString("phone"));
                c.setEmail(rs.getString("email"));
                listClient.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listClient;
    }
    public void addClient(KhachHang c){
        int returnValue = 0;
        String sql = "INSERT INTO tblClient(idCard,name,address,phone,email) VALUES(?,?,?,?,?)";
        try {
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, c.getIdCard());
            ps.setString(2, c.getName());
            ps.setString(3, c.getAddress());
            ps.setString(4, c.getPhone());
            ps.setString(5, c.getEmail());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                c.setId(rs.getInt(1));
            }
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
    }
    public void editClient(KhachHang c){
        String sql = "UPDATE tblClient SET name=?, address=?, phone=?, email=? WHERE idCard=?";
        try{
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, c.getName());
            ps.setString(2, c.getAddress());
            ps.setString(3, c.getPhone());
            ps.setString(4, c.getEmail());
            ps.setInt(5, c.getIdCard());
            ps.executeUpdate();
            con.commit();
        }catch(Exception e){
            e.printStackTrace();
        }
        finally{
            try {
                con.rollback();
                con.setAutoCommit(true);
            } catch (Exception e) {
            }
            
            
        }
    }
    public void deleteClient(int idCard){
        String sql = "DELETE FROM tblClient WHERE idcard=?";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idCard);
            ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public ArrayList<KhachHang> getAllClient(){
        ArrayList<KhachHang> listClient = new ArrayList<KhachHang>();
        String sql = "SELECT * FROM tblClient";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                KhachHang c = new KhachHang();
                c.setId(rs.getInt("id"));
                c.setIdCard(rs.getInt("idCard"));
                c.setName(rs.getString("name"));
                c.setAddress(rs.getString("address"));
                c.setPhone(rs.getString("phone"));
                c.setEmail(rs.getString("email"));
                listClient.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listClient;
    }
    public KhachHang getClientById(int id){
        KhachHang c = null;
        String sql = "SELECT * FROM tblClient WHERE id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                c = new KhachHang();
                c.setId(rs.getInt("id"));
                c.setIdCard(rs.getInt("idCard"));
                c.setName(rs.getString("name"));
                c.setAddress(rs.getString("address"));
                c.setPhone(rs.getString("phone"));
                c.setEmail(rs.getString("email"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;
    }
    

}
