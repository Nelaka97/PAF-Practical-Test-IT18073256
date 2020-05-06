package com;

import model.Payment;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;
@Path("/Payment") 

//IT18073256 - Dilshan K.K.D.N.

public class PaymentService {
	Payment p1 = new Payment();
	   @GET
	   @Path("/")
	@Produces(MediaType.TEXT_HTML)
	 
	   public String readPayment()
	   {
	   return p1.readPayment();
	   }
	   @POST
	   @Path("/")
	   @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	   @Produces(MediaType.TEXT_PLAIN)
	   public String insertPayment(
	    @FormParam("payer_name") String payer_name,
	    @FormParam("reference_no") String reference_no,
	    @FormParam("payer_address") String payer_address,
	    @FormParam("payer_email") String payer_email
	 )
	   {
	    String output = p1.insertPayment(payer_name,reference_no ,payer_address , payer_email);
	   return output;
	   }
	   @PUT
	   @Path("/")
	   @Consumes(MediaType.APPLICATION_JSON)
	   @Produces(MediaType.TEXT_PLAIN)
	   public String updatePayment(String dData)
	   {
	   
	    JsonObject p2 = new JsonParser().parse(dData).getAsJsonObject();
	    String payment_id = p2.get("payment_id").getAsString();
	    String payer_name = p2.get("payer_name").getAsString();
	    String reference_no = p2.get("reference_no").getAsString();
	    String payer_address = p2.get("payer_address").getAsString();
	    String payer_email = p2.get("payer_email").getAsString();
	    
	   
	   String output = p1.updatePayment(payment_id, payer_name,reference_no,payer_address,payer_email);
	   return output;
	   }
	   @DELETE
	   @Path("/")
	   @Consumes(MediaType.APPLICATION_XML)
	   @Produces(MediaType.TEXT_PLAIN)
	   public String deletePayment(String dData)
	   {
	  
	    Document doc = Jsoup.parse(dData, "", Parser.xmlParser());

	 
	    String payment_id = doc.select("payment_id").text();
	    String output = p1.deletePayment(payment_id);
	   return output;
	   }
	   
//IT18073256 - Dilshan K.K.D.N.

}