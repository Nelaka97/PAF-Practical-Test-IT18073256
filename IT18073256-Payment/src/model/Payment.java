package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import database.dbconnect;

//IT18073256 - Dilshan K.K.D.N.

public class Payment {
	// This is a common method to connect our database
	dbconnect obj = new dbconnect();

	public String insertPayment(String pName, String referenceNo, String address, String email) {

		String output = "";
		try {
			Connection con = obj.connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			//Insert Payment
			// creating a prepared statement
			String query = " insert into payment(`payer_name`,`reference_no`,`payer_address`,`payer_email`)"
					+ " values (?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			preparedStmt.setString(1, pName);
			preparedStmt.setString(2, referenceNo);
			preparedStmt.setString(3, address);
			preparedStmt.setString(4, email);
			
			// Executing the statement
			preparedStmt.execute();
			con.close();
			String newPayments = readPayment();
			output = "{\"status\":\"success\", \"data\": \"" +newPayments+ "\"}";
			
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\": \"Error while inserting the Payment.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readPayment() {
		String output = "";
		try {
			Connection con = obj.connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			
			output = "<table border='1'><tr><th>Payer Name</th><th>Reference No</th><th>"
					+ "Payer Address</th><th>Payer Email</th><th>Update</th><th>Remove</th></tr>";
			String query = "select * from payment";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterating through the rows in our result set
			while (rs.next()) {
				String payment_id = Integer.toString(rs.getInt("payment_id"));
				String payer_name = rs.getString("payer_name");
				String reference_no = rs.getString("reference_no");
				String payer_address = rs.getString("payer_address");
				String payer_email = rs.getString("payer_email");
				
				//Replacing the spaces in Payment name
				payer_name=payer_name.replace('+', ' ');
				//Replacing spaces,commas and slashes in Payer address
				payer_address=payer_address.replace('+', ' ');
				payer_address=payer_address.replaceAll("%2C",",");
				payer_address=payer_address.replaceAll("%2F","/");
				//Replacing @ in Payer email
				payer_email=payer_email.replaceAll("%40","@");

				// Add into our html table
				output += "<tr><td><input id=\'payment_idPaymentIDUpdate\'name=\'payment_idPaymentIDUpdate\'type=\'hidden\' value=\'"
						+ payment_id + "\'>" + payer_name + "</td>";
				output += "<td>" + reference_no + "</td>";
				output += "<td>" + payer_address + "</td>";
				output += "<td>" + payer_email + "</td>";
				
				output += "<td><input name='btnUpdate'type='button' "
						+ "value='Update'class='btnUpdate btn btn-warning'></td>"
						+ "<td><input name='btnRemove'type='button' "
						+ "value='Remove'class='btnRemove btn btn-danger'data-payment_id='"+ payment_id + "'>" + "</td></tr>";
				
			}
			con.close();
			// Completing the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the Payments.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updatePayment(String payment_id, String pName, String referenceNo, String address, String email) {
		System.out.println(pName);
		String output = "";
		try {
			Connection con = obj.connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// Update Payments
			// Creating a prepared statement
			String query = "UPDATE payment SET payer_name=?,reference_no=?,payer_address=?,payer_email=? WHERE payment_id=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, pName);
			preparedStmt.setString(2, referenceNo);
			preparedStmt.setString(3, address);
			preparedStmt.setString(4, email);
			preparedStmt.setInt(5, Integer.parseInt(payment_id));
			// executing the statement
			preparedStmt.execute();
			con.close();
			String newPayments = readPayment();
			output = "{\"status\":\"success\", \"data\": \"" +newPayments+ "\"}";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\": \"Error while updating the Payments.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deletePayment(String payment_id) {
		String output = "";
		try {
			Connection con = obj.connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			//Delete Payment
			// Creating a prepared statement
			String query = "delete from payment where payment_id=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(payment_id));
			// executing the statement
			preparedStmt.execute();
			con.close();
			String newPayments = readPayment();
			output = "{\"status\":\"success\", \"data\": \"" +newPayments+ "\"}";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\": \"Error while deleting the Payments.\"}";
			System.err.println(e.toString());
		}
		return output;
	}
	
	//IT18073256 - Dilshan K.K.D.N.

}