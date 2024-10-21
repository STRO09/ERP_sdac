package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import implementors.ConsumerImplementor;
import models.ConsumerPojo;
import models.SellerPojo;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ConsumerPojo consumerPojo = null;
	SellerPojo sellerPojo = null;

    /**
     * Default constructor. 
     */
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String role = request.getParameter("role");
		String portid = request.getParameter("portid");
		String pass = request.getParameter("password");
		String location = request.getParameter("location");
		
		
		if(role =="Consumer") {

			consumerPojo.setCon_port_id(Integer.parseInt(portid));
			consumerPojo.setCon_password(pass);
			consumerPojo.setLocation(location);
			consumerPojo.setRole(role);
			consumerPojo.RegisterConsumer(consumerPojo);
		}
		
		
		else {
			
			sellerPojo.setPort_id(Integer.parseInt(portid));
			sellerPojo.setPassword(pass);
			sellerPojo.setRole(role);
			sellerPojo.RegisterSeller(sellerPojo);
		}
		
		

	}

}
