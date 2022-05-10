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

// If valid------------------------
	var type = ($("#hidIDSave").val() == "") ? "POST" : "PUT";
	$.ajax({
		url : "ComplaintAPI",
		type : type,
		data : $("#formComplaint").serialize(),
		dataType : "text",
		complete : function(response, status) {
			onComplaintSaveComplete(response.responseText, status);
		}
	});
	
	

function onComplaintSaveComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();
			$("#divItemsGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error") {
		$("#alertError").text("Error while saving.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while saving..");
		$("#alertError").show();
	}
	$("#hidIDSave").val("");
	$("#formComplaint")[0].reset();
}

// Update ==================================

$(document).on("click", ".btnUpdate", function(event) {
			$("#hidIDSave").val( $(this).closest("tr").find('#hidIDUpdate').val());
			$("#user").val($(this).closest("tr").find('td:eq(0)').text());
			$("#title").val($(this).closest("tr").find('td:eq(2)').text());
			$("#description").val($(this).closest("tr").find('td:eq(3)').text());
			
		});

// Delete============================================
$(document).on("click", ".btnRemove", function(event) {
	$.ajax({
		url : "ComplaintAPI",
		type : "DELETE",
		data : "id=" + $(this).data("compId"),
		dataType : "text",
		complete : function(response, status) {
			onComplaintDeleteComplete(response.responseText, status);
		}
	});
});

function onComplaintDeleteComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully deleted.");
			$("#alertSuccess").show();
			$("#divItemsGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error") {
		$("#alertError").text("Error while deleting.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while deleting..");
		$("#alertError").show();
	}
}









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





