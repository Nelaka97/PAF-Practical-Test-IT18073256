//IT18073256 - Dilshan K.K.D.N.

$(document).ready(function()
{
    if ($("#alertSuccess").text().trim() == "")
    {
        $("#alertSuccess").hide();
    }
    
    $("#alertError").hide();
});

//==========SAVE==========
$(document).on("click", "#btnSave", function(event)
{
    //==========Clear alerts==========
    $("#alertSuccess").text("");
    $("#alertSuccess").hide();
    $("#alertError").text("");
    $("#alertError").hide();
    
    //==========Form validation==========
    var status = validatePaymentForm();
    
    if (status != true)
    {
        $("#alertError").text(status);
        $("#alertError").show();
        return;
    }
    
    //==========If valid==========
    var method = ($("#payment_idPaymentIDSave").val() == "") ? "POST" : "PUT";
    
    $.ajax(
    {
        url : "PaymentAPI",
        type : method,
        data : $("#formPayment").serialize(),
        dataType : "text",
        complete : function(response, status)
        {
            onPaymentSaveComplete(response.responseText, status);
        }
    });
});

//==========UPDATE==========
$(document).on("click", ".btnUpdate", function(event)
{
    $("#payment_idPaymentIDSave").val($(this).closest("tr").find('#payment_idPaymentIDUpdate').val());
    $("#payer_name").val($(this).closest("tr").find('td:eq(0)').text());
    $("#reference_no").val($(this).closest("tr").find('td:eq(1)').text());
    $("#payer_address").val($(this).closest("tr").find('td:eq(2)').text());
    $("#payer_email").val($(this).closest("tr").find('td:eq(3)').text());
});

function onPaymentSaveComplete(response, status)
{
    if (status == "success")
    {
        var resultSet = JSON.parse(response);
        if (resultSet.status.trim() == "success")
        {
            $("#alertSuccess").text("Successfully saved.");
            $("#alertSuccess").show();
            $("#divItemsGrid").html(resultSet.data);
        } 
        else if (resultSet.status.trim() == "error")
        {
            $("#alertError").text(resultSet.data);
            $("#alertError").show();
        }
    } 
    else if (status == "error")
    {
        $("#alertError").text("Error while saving.");
        $("#alertError").show();
    } 
    else
    {
        $("#alertError").text("Unknown error while saving..");
        $("#alertError").show();
    }
    
    $("#payment_idPaymentIDSave").val("");
    $("#formPayment")[0].reset();
}

$(document).on("click", ".btnRemove", function(event)
{
    $.ajax(
    {
        url : "PaymentAPI",
        type : "DELETE",
        data : "payment_id=" + $(this).data("payment_id"),
        dataType : "text",
        complete : function(response, status)
        {
            onPaymentDeleteComplete(response.responseText, status);
        }
    });
});

function onPaymentDeleteComplete(response, status)
{
    if (status == "success")
    {
        var resultSet = JSON.parse(response);
        
        if (resultSet.status.trim() == "success")
        {
            $("#alertSuccess").text("Successfully deleted.");
            $("#alertSuccess").show();
            $("#divItemsGrid").html(resultSet.data);
        } 
        else if (resultSet.status.trim() == "error")
        {
            $("#alertError").text(resultSet.data);
            $("#alertError").show();
        }
    } 
    else if (status == "error")
    {
        $("#alertError").text("Error while deleting.");
        $("#alertError").show();
    } 
    else
    {
        $("#alertError").text("Unknown error while deleting..");
        $("#alertError").show();
    }
}


function validatePaymentForm()
{
	//==========Payer Name==========
    if ($("#payer_name").val().trim() == "")
    {
        return "Insert Payer Name.";
    }
    
    //==========Reference Number==========
    if ($("#reference_no").val().trim() == "")
    {
        return "Insert Reference Number.";
    }
    
    //====Check for numeric value=====
	var reference = $("#reference_no").val().trim();
	if (!$.isNumeric(reference)) {
		return "Insert a correct reference number (don't insert characters)";
	}
	
	//=====check for length=====
	var pattern = /^\d{10}$/;
	if (!pattern.test(reference)) {
		return "Reference number should have 10 numbers";
	}
    
    //=====Payer Address=====
    if ($("#payer_address").val().trim() == "")
    {
        return "Insert Payer Address.";
    }
    
    
    //=====Payer Email=====
    
    if ($("#payer_email").val().trim() == "") {
		return "Insert Email.";
	}

	var re = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	var email = $("#payer_email").val().trim();
	if (re.test(email) == false) {
		return "Please enter valid email address";
	}
    
    
    
    return true;
    
  //IT18073256 - Dilshan K.K.D.N.
}