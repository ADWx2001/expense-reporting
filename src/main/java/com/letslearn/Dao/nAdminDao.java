package com.letslearn.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.letslearn.Modal.nAdmin;

public class nAdminDao {
	private Connection con;
	private String query;
    private PreparedStatement pst;
    private ResultSet rs;

	public nAdminDao(Connection con) {
		this.con = con;
	}
	
	public int addAdmins(nAdmin admin) {
		int result = 0;
  		try {
  			query="INSERT INTO nadmin (adminName,nic,department,email,telephone)\r\n"
  					+ "VALUES (?,?,?,?,?);";
  			pst = this.con.prepareStatement(query);
            pst.setString(1,  admin.getAdminName());
            pst.setString(2, admin.getNic());
            pst.setString(3, admin.getDepartment());
            pst.setString(4, admin.getEmail());
            pst.setString(5, admin.getTelephone());
            result=pst.executeUpdate();
  		}catch(Exception e) {
  			e.printStackTrace();
  		}
  		return result;
	}
	//get coupon id name
	public List<nAdmin> getAllAdmins(){
  		List<nAdmin> nadmin = new ArrayList<nAdmin>();
  		try {
  			query="select * from nadmin";
  			pst=this.con.prepareStatement(query);
  			rs=pst.executeQuery();
  			while(rs.next()) {
  				nAdmin cpn = new nAdmin("","","");
  				cpn.setId(rs.getString("id"));
  				cpn.setAdminName(rs.getString("adminName"));
  				cpn.setNic(rs.getString("nic"));
  				cpn.setDepartment(rs.getString("department"));
  				cpn.setEmail(rs.getString("email"));
  				cpn.setTelephone(rs.getString("telephone"));
  				nadmin.add(cpn);
  				
  			}
  		}catch(Exception e) {
  			e.printStackTrace();
  		}
  		return nadmin;
  		
  	}
	
	public int deleteAdmin(String id) {
  		int result=0;
  		try {
  			query="DELETE FROM nadmin WHERE id=?;";
  			pst = this.con.prepareStatement(query);
            pst.setString(1,id);
      
            result=pst.executeUpdate();
  		}catch(Exception e) {
  			e.printStackTrace();
  		}
  		return result;
  	}
	
	//updating coupons
  	public int updateAdmin(nAdmin admin) {
		System.out.print(admin.getEmail());
  		int result=0;
  		try {
  			query="UPDATE nadmin SET adminName = ?, nic= ? , department=?,email=?,telephone=? WHERE id = ?;";
  			pst = this.con.prepareStatement(query);
  			 pst.setString(1,  admin.getAdminName());
             pst.setString(2, admin.getNic());
             pst.setString(3, admin.getDepartment());
             pst.setString(4, admin.getEmail());
             pst.setString(5, admin.getTelephone());
             pst.setString(6, admin.getId());
            result=pst.executeUpdate();
  		}catch(Exception e) {
  			e.printStackTrace();
  		}
  		return result;
  	}
}
