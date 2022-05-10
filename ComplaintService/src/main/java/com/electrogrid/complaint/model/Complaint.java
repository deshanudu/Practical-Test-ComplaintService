package com.electrogrid.complaint.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import com.electrogrid.complaint.utils.DBConnectionSingleton;


public class Complaint {

	private int compId;
	private int user;
	private java.sql.Timestamp dTime;
	private String title;
	private String description;

	public Complaint() {}

	public Complaint(int compId, int user, Timestamp dTime, String title, String description) {
		super();
		this.compId = compId;
		this.user = user;
		this.dTime = dTime;
		this.title = title;
		this.description = description;
	}

	static Connection con = DBConnectionSingleton.getConnection();
	
	
	//get recent complaints
		public String fetchRecentComplaints() {
			
			String output = "";
			try
			 {
				 if (con == null)
				 { 
					 return "Error while connecting to the database for reading."; 
						 } 
				 
				// Prepare the html table to be displayed
				 output = "<table border='1' style = 'table'><tr>"
						  + "<th>Complaint ID</th>"
						  + "<th>User</th>"
						  + "<th>Title</th>" 
						  + "<th> Description</th>" ;
			 
				 // create a statement and execute query
				 String query = "SELECT * FROM complaints;";
				 PreparedStatement spSt = con.prepareStatement(query);
				 //spSt.setInt(1,uid);
				 
				 ResultSet rs = spSt.executeQuery();
				 
				 while (rs.next())
			      {
			        Integer compId = rs.getInt("compId");
			        Integer user = rs.getInt("user");
			        java.sql.Timestamp dTime  = rs.getTimestamp("date");
			        String title = rs.getString("title");
			        String description = rs.getString("description");
			        
			        //create a list of objects from db rows
//			        Complaint comp = new Complaint(compId, user,dTime,title, description);
//			        compList.add(comp);
			        
			     // Add into the html table
			        output += "<tr><td>" + compId + "</td>";
					output += "<td>" + user + "</td>";
					output += "<td>" + title + "</td>";
					output += "<td>" + description + "</td>";
					
					
					//buttons
					output += "<td><input name='btnUpdate' type='button' value='Update' "
					+ "class='btnUpdate btn btn-secondary' data-itemid='" + compId + "'></td>"
					+ "<td><input name='btnRemove' type='button' value='Remove' "
					+ "class='btnRemove btn btn-danger' data-itemid='" + compId + "'></td></tr>"; 
					

			        
			      }
				 
				 output += "</table>";
			 }
			 catch (Exception e)
			 {
				 System.err.println(e.getMessage());
			 }
			return output; 	
		}
		
		
		
		//insert method
		public String insertItem(int user, String title, String des) {
			this.user = user;
			this.title = title;
			this.description = des;
			
			String output = this.save();
			return output;
			
		}
		
		public String save()
		 {
			String output = "";
		 try
		 {
			 if (con == null)
			 {return "Error while connecting to the database."; }
		 
			 // create a prepared statement
			 String query = " insert into complaints values (0,?, ?, ?, ?)";
			 PreparedStatement preparedSt = con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			 
			 //Prepare sql timestamp
			 final java.util.Date today = new java.util.Date();
		   	 final java.sql.Timestamp todaySQL = new java.sql.Timestamp(today.getTime());
			 this.dTime = todaySQL;
		   	 
			 // binding values
			 preparedSt.setInt(1, this.user);
			 preparedSt.setTimestamp(2, todaySQL); //dtime
			 preparedSt.setString(3, this.title); 
			 preparedSt.setString(4, this.description);
		 
			 // execute the statement
			 preparedSt.executeUpdate();
			 con.close(); 
			 
			 String newComplaint = fetchRecentComplaints(); 
			 output = "{\"status\":\"success\", \"data\": \"" + newComplaint + "\"}"; 
			
			
		 }
		 catch (Exception e)
		 {
			 output = "Error while inserting the item.";
			 System.err.println(e.getMessage());
		 }
		 	return output;
		 }
		
		
		//Get specific complaint using the id
		public String fetchById(int id) {
			String output = "";
			try
			 {
				 if (con == null)
				 {return "Processing Error!"; }
			 
				 // create a statement and execute query
				 String query = "SELECT * FROM complaints WHERE compId=?";
				 PreparedStatement pSt = con.prepareStatement(query);
				 pSt.setInt(1,id);
				 ResultSet rs = pSt.executeQuery();
				 
				 boolean read = false;
				 
				 while (rs.next())
			      {
					read=true;
					this.compId = rs.getInt("compId");
					this.user = rs.getInt("user");
					this.dTime  = rs.getTimestamp("date");
					this.title = rs.getString("title");
					this.description = rs.getString("description");
					
					 output += "<tr><td>" + compId + "</td>";
						output += "<td>" + user + "</td>";
						output += "<td>" + dTime + "</td>";
						output += "<td>" + title + "</td>";
						output += "<td>" + description + "</td>";
						
			         
			      }
				 
				 output += "</table>";
				 output = "Executed successfully";
			 }
			 catch (Exception e)
			 {
				 System.err.println(e.getMessage());
				 output = "Processing Error!";
			 } 	
			return output;
		}
		
		//Cancel a complaint using the id
		public String cancelById(int id) {
			String output = "";
			try
			 {
				 if (con == null)
				 {return "Processing Error!"; }
			 
				 // create a statement and execute query
				 String query = " delete from complaints where compId=?";
				 PreparedStatement pSt = con.prepareStatement(query);
				 pSt.setInt(1,id);
				 int state = pSt.executeUpdate();
				 //System.out.println(state);
				 String newItems = fetchRecentComplaints();
				 output = "{\"status\":\"success\", \"data\": \"" +
				 newItems + "\"}";
				 
				
			 }
			 catch (Exception e)
			 {
				 System.err.println(e.getMessage());
				 output = "Processing Error-2!";
			 } 	
			return output;
		}
		
		//update a complaint
		public String updateItem(int id,String newDesc)
		{
			String output = "";
			try
			 {
				 if (con == null)
				 {return null; }
			 
				 // create a statement and execute query
				 String query = "UPDATE complaints set description = ? WHERE compId= ?";
				 PreparedStatement pSt = con.prepareStatement(query);
				 pSt.setString(1,newDesc);
				 pSt.setInt(2,id);
				 pSt.executeUpdate();
				 
				 //get affected row
				 String squery = "SELECT * FROM complaints WHERE compId= ?";
				 PreparedStatement spSt = con.prepareStatement(squery);
				 spSt.setInt(1, id);
				 
				 ResultSet rs = spSt.executeQuery();
				 boolean read = false;
				 
					 while (rs.next())
				      {
						read=true;
				        this.compId = rs.getInt("compId");
				        this.dTime  = rs.getTimestamp("date");
				        this.user = rs.getInt("user");
				        this.title = rs.getString("title");
				        this.description = rs.getString("description"); 
				      }
					 
					 String newItems = fetchRecentComplaints();
					 output = "{\"status\":\"success\", \"data\": \"" +
					 newItems + "\"}";
				 
			 }
			 catch (Exception e)
			 {
				 System.err.println(e.getMessage());
			 } 
			return null;
		}

		//getters and setters
		public int getCompId() {
			return compId;
		}

		public void setCompId(int compId) {
			this.compId = compId;
		}

		public int getUser() {
			return user;
		}

		public void setUser(int user) {
			this.user = user;
		}

		public java.sql.Timestamp getdTime() {
			return dTime;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		

}
