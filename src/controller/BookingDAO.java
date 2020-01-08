/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import model.Booking;
import model.KhachHang;
import model.Ban;
/**
 *
 * @author Admin
 */
public class BookingDAO extends DAO{

    public BookingDAO() {
        getInstance();
    }
    public ArrayList<Ban> searchFreeTable(Date date,Time sTime, Time eTime){
        ArrayList<Ban> listTable = new ArrayList<Ban>();
        String sql = "SELECT * FROM tblTable WHERE id NOT IN (SELECT idTable from tblBooking_Bill WHERE (((startTime BETWEEN ? AND ?) AND date = ?  ) or ((endTime BETWEEN ? AND ? )AND date = ?)or((? BETWEEN startTime AND endTime )AND date = ?)))";
        
        try {
            PreparedStatement ps =  con.prepareStatement(sql);  
            ps.setTime(1,sTime);
            ps.setTime(2, eTime);
            ps.setDate(3, date);
            ps.setTime(4,sTime);
            ps.setTime(5, eTime);
            ps.setDate(6, date);
            ps.setTime(7,sTime);
            ps.setDate(8,date);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Ban t = new Ban();
                t.setId(rs.getInt("id"));
                t.setName(rs.getString("name"));
                t.setType(rs.getString("type"));
                t.setDescription(rs.getString("description"));
                listTable.add(t); 
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listTable;
    }
    public int saveBooking(Booking b){
        int returnValue = 0;
        String sql = "INSERT INTO tblBooking_Bill(idClient,idTable,date,startTime,endTime,isCheckin) VALUES(?,?,?,?,?,?)";
        try {
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            ps.setInt(1,b.getClient().getId());
            ps.setInt(2, b.getTable().getId());
            ps.setDate(3,Date.valueOf(sdf.format(b.getDate())));
            ps.setTime(4,b.getStartTime());
            ps.setTime(5, b.getEndTime());
            ps.setBoolean(6,b.isIsCheckin());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                returnValue = rs.getInt(1);
                b.setId(returnValue);
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
        return returnValue;
    }
    public ArrayList<Booking> searchBooking(Booking b){
        ArrayList<Booking> listBooking = new ArrayList<Booking>();
        String sql = "SELECT * FROM tblBooking_Bill WHERE (((date = ? AND startTime = ? AND endTime = ?) OR (date = ? AND (startTime BETWEEN ? AND ?) AND(endTime BETWEEN ? AND ?)))AND isCheckin=0)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            ps.setDate(1,Date.valueOf(sdf.format(b.getDate())));
            
            ps.setTime(2,b.getStartTime());
            ps.setTime(3,b.getEndTime());
            ps.setDate(4,Date.valueOf(sdf.format(b.getDate())));
            ps.setTime(5,b.getStartTime());
            ps.setTime(6,b.getEndTime());
            ps.setTime(7,b.getStartTime());
            ps.setTime(8,b.getEndTime());
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Booking tmp = new Booking();
                tmp.setId(rs.getInt("id"));
                KhachHang c = new KhachHang();
                KhachDAO cd = new KhachDAO();
                c = cd.getClientById(rs.getInt("idClient"));
                tmp.setClient(c);
                Ban t = new Ban();
                BanDAO td = new BanDAO();
                t = td.getTableById(rs.getInt("idTable"));
                tmp.setTable(t);
                tmp.setIsCheckin(rs.getBoolean("IsCheckIn"));
                tmp.setDate(rs.getDate("date"));
                tmp.setStartTime(rs.getTime("startTime"));
                tmp.setEndTime(rs.getTime("endTime"));
                listBooking.add(tmp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listBooking;
    }
    public ArrayList<Booking> searchTableToOrder(String nameTable){
        ArrayList<Booking> listBooking = new ArrayList<Booking>();
        String sql = "SELECT * FROM tblBooking_Bill WHERE isCheckin=1";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");   
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Booking tmp = new Booking();
                tmp.setId(rs.getInt("id"));
                KhachHang c = new KhachHang();
                KhachDAO cd = new KhachDAO();
                c = cd.getClientById(rs.getInt("idClient"));
                tmp.setClient(c);
                Ban t = new Ban();
                BanDAO td = new BanDAO();
                t = td.getTableById(rs.getInt("idTable"));
                tmp.setTable(t);
                tmp.setIsCheckin(rs.getBoolean("IsCheckIn"));
                tmp.setDate(rs.getDate("date"));
                tmp.setStartTime(rs.getTime("startTime"));
                tmp.setEndTime(rs.getTime("endTime"));
                if(t.getName().contains(nameTable)){
                listBooking.add(tmp);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listBooking;
    }
    public void updateBooking(Booking b){
        String sql = "UPDATE tblBooking_Bill SET isCheckIn = ? WHERE id = ?";
        try {
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setBoolean(1,b.isIsCheckin());
            ps.setInt(2,b.getId());
            ps.executeUpdate();
            b.setId(b.getId());
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
     public ArrayList<Booking> getAllBooking(){
        ArrayList<Booking> listBooking = new ArrayList<Booking>();
        String sql = "SELECT * FROM tblBooking_bill";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Booking tmp = new Booking();
                tmp.setId(rs.getInt("id"));
                KhachHang c = new KhachHang();
                KhachDAO cd = new KhachDAO();
                c = cd.getClientById(rs.getInt("idClient"));
                tmp.setClient(c);
                Ban t = new Ban();
                BanDAO td = new BanDAO();
                t = td.getTableById(rs.getInt("idTable"));
                tmp.setTable(t);
                tmp.setIsCheckin(rs.getBoolean("IsCheckIn"));
                tmp.setDate(rs.getDate("date"));
                tmp.setStartTime(rs.getTime("startTime"));
                tmp.setEndTime(rs.getTime("endTime"));
                listBooking.add(tmp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return listBooking;
    }
    public Booking getBookingById(int id){
        Booking tmp = null;
        String sql = "SELECT * FROM tblBooking_Bill WHERE id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                tmp = new Booking();
                tmp.setId(rs.getInt("id"));
                KhachHang c = new KhachHang();
                KhachDAO cd = new KhachDAO();
                c = cd.getClientById(rs.getInt("idClient"));
                tmp.setClient(c);
                Ban t = new Ban();
                BanDAO td = new BanDAO();
                t = td.getTableById(rs.getInt("idTable"));
                tmp.setTable(t);
                tmp.setIsCheckin(rs.getBoolean("isCheckin"));
                tmp.setDate(rs.getDate("date"));
                tmp.setStartTime(rs.getTime("startTime"));
                tmp.setEndTime(rs.getTime("endTime"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tmp;
   }


}
