<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="model.Payment" %>
    <%@ page import="com.PaymentAPI" %>
    
<!DOCTYPE html>
<html>

<!--IT18073256 - Dilshan K.K.D.N.-->

<head>

<meta charset="ISO-8859-1">
<title>Payment Details</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
   <script type="text/javascript" src="./Components/Payment.js"></script>
  
  <style>
  
table {
  font-family: arial, sans-serif;
  border-collapse: collapse;
  width: 90%;
  margin-left: auto;
  margin-right: auto;
}

td, th {
  border: 1px solid #dddddd;
  text-align: left;
  padding: 8px;
}

tr:nth-child(even) {
  background-color: #dddddd;
}
.card{
	padding: 20px;
	border: 1px solid black;
	border-radius: 10px;
	margin-bottom: 20px;
	font-size: 15px;
	margin-top: 15px;
}
#btnSave{
	margin-top: 15px;
	font-size: px;
	width: 100%;
}
.alert{
	width: 80%;
	margin-left: auto;
	margin-right: auto;
	padding: 15px;
	text-align: center;
}

  </style>
  
</head>


<body style="background-color:#FCF3CF;">
		<br/><br/>
		<center><button type="button" class="btn btn-success btn-lg"><h1>-- Payment Details --</h1></button>
		<br/>IT18073256 - Dilshan K.K.D.N.</center>
		<br/>
	<div class="container">
		<div class="row"><div class="col-md-3"></div>
			<div class="col-md-6">
				<div class="card">
				
	<form  id="formPayment" name="formPayment" method="post" action="Payment_Details.jsp">
		Payer Name: 
		<input id="payer_name" name="payer_name" type="text"
			class="form-control form-control-sm" required> <br>
	    Reference No:
	     <input id="reference_no" name="reference_no" type="text" placeholder="xxxxxxxxxx" maxlength="10"
			 pattern="^\d{10}$" class="form-control form-control-sm"required > <br> 
		Payer Address: 
		<input id="payer_address" name="payer_address" type="text"
			class="form-control form-control-sm" required> <br> 
		Payer Email:
		 <input id="payer_email" name="payer_email" type="text"  
			class="form-control form-control-sm" required> <br> 
		<input id="btnSave" name="btnSave" type="button" value="Save" 
			class="btn btn-primary"  > 
		<input type="hidden" id="payment_idPaymentIDSave" name="payment_idPaymentIDSave" value="">
	
	</form>
	</div>
</div>
</div>
</div>

	
	<div id="alertSuccess" class="alert alert-success">
	
			
		
		</div>
		<div id="alertError" class="alert alert-danger"></div>
		
     <div id="divItemsGrid">
	<% 
	Payment p1= new Payment();
		out.print(p1.readPayment());
	     %>
	   </div>
	  
<!--IT18073256 - Dilshan K.K.D.N.-->
		
</body>
</html>