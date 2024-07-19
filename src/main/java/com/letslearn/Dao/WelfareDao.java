package com.letslearn.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.letslearn.Modal.Welfare;

public class WelfareDao {
	private Connection con;
	private String query;
    private PreparedStatement pst;
    private ResultSet rs;

	public WelfareDao(Connection con) {
		this.con = con;
	}
	
	public int addWelfare(Welfare welfare) {
		int result = 0;
  		try {
  			query="INSERT INTO welfare (company,nic,connection,email,telephone)\r\n"
  					+ "VALUES (?,?,?,?,?);";
  			pst = this.con.prepareStatement(query);
            pst.setString(1,  welfare.getCompany());
            pst.setString(2, welfare.getNic());
            pst.setString(3, welfare.getConnection());
            pst.setString(4, welfare.getEmail());
            pst.setString(5, welfare.getTelephone());
            result=pst.executeUpdate();
  		}catch(Exception e) {
  			e.printStackTrace();
  		}
  		return result;
	}
	//get coupon id name
	public List<Welfare> getAllWelfares(){
  		List<Welfare> welfare = new ArrayList<Welfare>();
  		try {
  			query="select * from welfare";
  			pst=this.con.prepareStatement(query);
  			rs=pst.executeQuery();
  			while(rs.next()) {
  				Welfare cpn = new Welfare("","","");
  				cpn.setId(rs.getString("id"));
  				cpn.setCompany(rs.getString("company"));
  				cpn.setNic(rs.getString("nic"));
  				cpn.setConnection(rs.getString("connection"));
  				cpn.setEmail(rs.getString("email"));
  				cpn.setTelephone(rs.getString("telephone"));
  				welfare.add(cpn);
  				
  			}
  		}catch(Exception e) {
  			e.printStackTrace();
  		}
  		return welfare;
  		
  	}
	
	public int deleteWelfare(String id) {
  		int result=0;
  		try {
  			query="DELETE FROM welfare WHERE id=?;";
  			pst = this.con.prepareStatement(query);
            pst.setString(1,id);
      
            result=pst.executeUpdate();
  		}catch(Exception e) {
  			e.printStackTrace();
  		}
  		return result;
  	}
	
	//updating coupons
  	public int updateWelfare(Welfare welfare) {
		System.out.print(welfare.getEmail());
  		int result=0;
  		try {
  			query="UPDATE welfare SET company = ?, nic= ? , connection=?,email=?,telephone=? WHERE id = ?;";
  			pst = this.con.prepareStatement(query);
  			 pst.setString(1,  welfare.getCompany());
             pst.setString(2, welfare.getNic());
             pst.setString(3, welfare.getConnection());
             pst.setString(4, welfare.getEmail());
             pst.setString(5, welfare.getTelephone());
             pst.setString(6, welfare.getId());
            result=pst.executeUpdate();
  		}catch(Exception e) {
  			e.printStackTrace();
  		}
  		return result;
  	}
}
