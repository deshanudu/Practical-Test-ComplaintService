// SAVE ============================================
$(document).on("click", "#btnSave", function(event) 
{ 
// Clear status msges-------------
 $("#alertSuccess").text(""); 
 $("#alertSuccess").hide(); 
 $("#alertError").text(""); 
 $("#alertError").hide(); 
// Form validation----------------
var status = validateComplaintForm(); 
// If not valid-------------------
if (status != true) 
 { 
 $("#alertError").text(status); 
 $("#alertError").show(); 
 return; 
 } 
// If valid----------------------- 
 // Generate the card and append
var Complaint = getComplaintCard($("#user").val(), 
 $("#title").val(), 
 $("#description").val()); 
 $("#colComplaint").append(Complaint); 
 
 $("#alertSuccess").text("Saved successfully."); 
 $("#alertSuccess").show(); 
 
 $("#formComplaint")[0].reset(); 
});

function validateComplaintForm() 
{ 
 //Validations 
 // USER
	if ($("#user").val().trim() == "") 
	{ 
	return "Insert complaint user."; 
	}
 // TITLE
	if ($("#title").val().trim() == "") 
	{ 
	return "Insert complaint title."; 
	}
 // DESCRIPTION
	if ($("#description").val().trim() == "") 
	{ 
	return "Insert complaint description."; 
	}
 return true; 
}

function getComplaintCard(user, title, description) 
{ 
var Complaint = ""; 
 //Generate card 
 var Complaint = ""; 
 Complaint += "<div class=\"Complaint card bg-light m-2\" 
 style=\"max-width: 10rem; float: left;\">"; 
 Complaint += "<div class=\"card-body\">"; 
 Complaint += title + " " + name + ","; 
 Complaint += "<br>"; 
 Complaint += yearNumber + " year"; 
 Complaint += "</div>"; 
 Complaint += "<input type=\"button\" value=\"Remove\" 
 class=\"btn btn-danger remove\">"; 
 Complaint += "</div>"; 

return Complaint; 
}




