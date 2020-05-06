package com;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import model.Payment;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PaymentAPI
 */

//IT18073256 - Dilshan K.K.D.N.

@WebServlet("/PaymentAPI")
public class PaymentAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Payment PaymentObj = new Payment();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PaymentAPI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("get method.............................");
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
       //		doGet(request, response);
		System.out.println("post method.............................");
		
		
		String output = PaymentObj.insertPayment(request.getParameter("payer_name"),
				                    request.getParameter("reference_no"),
				                    request.getParameter("payer_address"),
				                    request.getParameter("payer_email"));
		
		System.out.println(request.getParameter("payer_name")+" "+request.getParameter("reference_no")+" "+request.getParameter("payer_address")+" "+request.getParameter("payer_email"));
		response.getWriter().write(output);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("put method.............................");
		// TODO Auto-generated method stub
		
         Map paras = getParasMap(request);
         
         
		
		String output = PaymentObj.updatePayment(paras.get("payment_idPaymentIDSave").toString(),
										   paras.get("payer_name").toString(),
										   paras.get("reference_no").toString(),
										   paras.get("payer_address").toString(),
										   paras.get("payer_email").toString());
		
		response.getWriter().write(output);
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		          // TODO Auto-generated method stub
        Map paras = getParasMap(request);
        
      
		
		String output = PaymentObj.deletePayment(paras.get("payment_id").toString());
		
		response.getWriter().write(output);
	}
	
	
	
	  private static Map getParasMap(HttpServletRequest request) { Map<String,
	  String> map = new HashMap<String, String>();
	  
	  try { Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
	  String queryString = scanner.hasNext() ? scanner.useDelimiter("\\A").next() :
	  ""; scanner.close();
	  
	  String[] params = queryString.split("&");
	  
	  for (String param : params) { String[] p = param.split("="); map.put(p[0],
	  p[1]); } } catch (Exception e) { }
	  
	  return map; }
	 
	
	//IT18073256 - Dilshan K.K.D.N.	

	
}