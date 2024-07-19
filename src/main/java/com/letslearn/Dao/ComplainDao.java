package com.letslearn.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.letslearn.Modal.Complaint;

public class ComplainDao {
	private Connection con;
	private String query;
    private PreparedStatement pst;
    private ResultSet rs;

	public ComplainDao(Connection con) {
		this.con = con;
	}
	
	public int addComplaint(Complaint complaint) {
		int result = 0;
  		try {
  			query="INSERT INTO complaint (name,email,telephone,about,nic,typeComp)\r\n"
  					+ "VALUES (?,?,?,?,?,?);";
  			pst = this.con.prepareStatement(query);
            pst.setString(1,  complaint.getName());
            pst.setString(2, complaint.getEmail());
            pst.setString(3, complaint.getTelephone());
            pst.setString(4, complaint.getAbout());
            pst.setString(5, complaint.getNic());
            pst.setString(6, complaint.getTypeComp());
            result=pst.executeUpdate();
  		}catch(Exception e) {
  			e.printStackTrace();
  		}
  		return result;
	}
	//get coupon id name
	public List<Complaint> getAllComplaints(){
  		List<Complaint> complaint = new ArrayList<Complaint>();
  		try {
  			query="select * from complaint";
  			pst=this.con.prepareStatement(query);
  			rs=pst.executeQuery();
  			while(rs.next()) {
  				Complaint cpn = new Complaint("","","");
  				cpn.setEmail(rs.getString("email"));
  				cpn.setName(rs.getString("name"));
  				cpn.setTelephone(rs.getString("telephone"));
  				cpn.setAbout(rs.getString("about"));
  				cpn.setNic(rs.getString("nic"));
  				cpn.setTypeComp(rs.getString("typeComp"));
  				cpn.setId(rs.getString("id"));
  				complaint.add(cpn);
  				
  			}
  		}catch(Exception e) {
  			e.printStackTrace();
  		}
  		return complaint;
  		
  	}
	
	public int deleteComplaint(String id) {
  		int result=0;
  		try {
  			query="DELETE FROM complaint WHERE id=?;";
  			pst = this.con.prepareStatement(query);
            pst.setString(1,id);
      
            result=pst.executeUpdate();
  		}catch(Exception e) {
  			e.printStackTrace();
  		}
  		return result;
  	}
	
	//updating coupons
  	public int updateComplaint(Complaint complaint) {
  		int result=0;
  		try {
  			System.out.println(complaint.getId());
  			query="UPDATE complaint SET name = ?, email= ? , telephone=?,nic=?,typeComp=?,about=? WHERE id = ?;";
  			pst = this.con.prepareStatement(query);
  			 pst.setString(1,  complaint.getName());
             pst.setString(2, complaint.getEmail());
             pst.setString(3, complaint.getTelephone());
             pst.setString(4, complaint.getNic());
             pst.setString(5, complaint.getTypeComp());
             pst.setString(6, complaint.getAbout());
             pst.setString(7, complaint.getId());
            result=pst.executeUpdate();
  		}catch(Exception e) {
  			e.printStackTrace();
  		}
  		return result;
  	}
}
