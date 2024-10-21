package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import implementors.SellerImplementor;
import models.ConsumerPojo;
import models.SellerPojo;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ConsumerPojo consumerPojo = null;
	SellerPojo sellerPojo = null;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String role = request.getParameter("role");
		String portid = request.getParameter("portid");
		String pass = request.getParameter("password");

		
		
		if(role =="Consumer") {

			consumerPojo.setCon_port_id(Integer.parseInt(portid));
			consumerPojo.setCon_password(pass);
			consumerPojo.setRole(role);
			consumerPojo.LoginConsumer(consumerPojo);
		}
		
		
		else {
			
			sellerPojo.setPort_id(Integer.parseInt(portid));
			sellerPojo.setPassword(pass);
			sellerPojo.setRole(role);
			sellerPojo.LoginSeller(sellerPojo); 
		}
	}

}
