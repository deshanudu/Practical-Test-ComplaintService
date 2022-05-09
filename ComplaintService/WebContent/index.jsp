<html>
<body>
<h2>Jersey RESTful Web Application!</h2>
<p><a href="webapi/myresource">Jersey resource</a>
<p>Visit <a href="http://jersey.java.net">Project Jersey website</a>
for more information on Jersey!
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/Complaint.js"></script>


<div class="container"> 
 <div class="row">
 <div class="col-8"> 
 <h1 class="m-3">Complaint details</h1> 
 <form id="formComplaint"> 
		<!-- USER -->
			<div class="input-group input-group-sm mb-3">
				<div class="input-group-prepend">
				 <span class="input-group-text" id="user">User: </span>
				</div>
			<input type="text" id="user" name="user">
			</div>
		<!-- TITLE -->
			<div class="input-group input-group-sm mb-3">
				<div class="input-group-prepend">
				 <span class="input-group-text" id="title">Title: </span>
				</div>
			<input type="text" id="title" name="title">
			</div>
		<!-- DESCRIPTION -->
			<div class="input-group input-group-sm mb-3">
				<div class="input-group-prepend">
				 <span class="input-group-text" id="desciption">Description: </span>
				</div>
		<input type="text" id="desciption" name="desciption">
		</div>
		
		<div id="alertSuccess" class="alert alert-success"></div>
		<div id="alertError" class="alert alert-danger"></div>
		<input type="button" id="btnSave" value="Save" class="btn btn-primary">
 	
 </form>
 </div>
 </div>
 
 <br>  
	<div class="row">
		<div class="col-12" id="colComplaint">
		 
		</div>
	</div>
 
</div>

</body>
</html>