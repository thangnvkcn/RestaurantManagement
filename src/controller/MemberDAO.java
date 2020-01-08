/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Admin
 */
public class MemberDAO extends DAO{
    public MemberDAO(){
        getInstance();
    }
    public int login(String user,String pass,String role){
        int returnValue = 0;
        String sql ="SELECT* FROM tblUser WHERE username=? AND password =? AND role = ?";
        try {
            PreparedStatement ps=con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user);
            ps.setString(2, pass);  
            ps.setString(3, role);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                returnValue = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnValue;
    }
}
