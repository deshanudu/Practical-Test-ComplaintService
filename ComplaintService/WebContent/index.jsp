<%@page import="com.electrogrid.complaint.model.Complaint"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/Complaint.js"></script>
<body style="background-color:#B0C4DE;">
<div style="text-align: center;position:relative;left:250px;top:50px;" class="container">
		<div class="row">
			<div style="background-color:#DCDCDC;height:920px;border-radius:10px;" class="col-6">
				<h1>Complaint Dashboard</h1>
				<form id="formComplaint" name="formComplaint" action="<%=request.getContextPath()%>/ComplaintAPI">
					User: 
					<input id="user" name="user" type="text"class="form-control form-control-sm" placeholder="Enter User"> <br>
					Title:
					<input id="title" name="title" type="text" class="form-control form-control-sm" placeholder="Enter Title"> <br> 
					Description:
					 <input id="description" name="description" type="text" class="form-control form-control-sm" placeholder="Enter Description"> <br>
					
					 
					 <input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary"> 
					 <input type="hidden" id="hidIDSave" name="hidIDSave" value="">
				
				</form>
				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>
				<br>
				<div style="position:relative;right:20px;" id="divComplaintGrid">
					<%
					Complaint complaintObj = new Complaint();
					out.print(complaintObj.fetchRecentComplaints());
					%>
				</div>
				<br>
				
				
				
			</div>
		</div>
	</div>

</body> 
</html>