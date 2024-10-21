package implementors;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.ConsumerPojo;
import interfaces.Consumer;
import jdbc.GetConnection;
import models.OrdersPojo;
import models.ProductsPojo;
import models.ReportedProductsPojo;

public class ConsumerImplementor implements Consumer{

	CallableStatement callableStatement = null;
	ResultSet resultSet = null;
	@Override
	public void RegisterConsumer(ConsumerPojo consumerPojo) {
		// TODO Auto-generated method stub
		try {
			callableStatement = GetConnection.connect().prepareCall("CALL RegisterUser(?,?,?,?)");
			callableStatement.setInt(1, consumerPojo.getCon_port_id());
			callableStatement.setString(2, consumerPojo.getCon_password());
			callableStatement.setString(3, consumerPojo.getLocation());
			callableStatement.setString(4, consumerPojo.getRole());
			callableStatement.execute();
			System.out.println("Consumer registered successfully");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public boolean LoginConsumer(ConsumerPojo consumerPojo) {
		// TODO Auto-generated method stub
		boolean auth_status = false;
		int auth_int = 0;
		try {
			callableStatement = GetConnection.connect().prepareCall("CALL RegisterUser(?,?,?)");
			callableStatement.setInt(1, consumerPojo.getCon_port_id());
			callableStatement.setString(2, consumerPojo.getCon_password());
			callableStatement.setString(3, consumerPojo.getRole());
			auth_int = callableStatement.executeUpdate();
			System.out.println("Consumer authenticated");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(auth_int == 1) {
			auth_status = true;
		}

		return auth_status;
	}

	@Override
	public void updateProfile(ConsumerPojo consumerPojo) {
		// TODO Auto-generated method stub
		try {
			callableStatement = GetConnection.connect().prepareCall("CALL UpdateuserProfile(?,?,?)");
			callableStatement.setInt(1, consumerPojo.getCon_port_id());
			callableStatement.setString(2, consumerPojo.getCon_password());
			callableStatement.setString(3, consumerPojo.getLocation());
			callableStatement.execute();
			System.out.println("Consumer details updated successfully");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void reportProduct(ReportedProductsPojo productsPojo) {
		// TODO Auto-generated method stub
		try {
			callableStatement  = GetConnection.connect().prepareCall("CALL ReportIssue(?,?,?)");
			callableStatement.setInt(1, productsPojo.getConsumer_port_id());
			callableStatement.setInt(2, productsPojo.getProduct_id());
			callableStatement.setString(3, productsPojo.getIssue_type());
			callableStatement.execute();
			System.out.println("Product Issue Reported.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public List<ReportedProductsPojo> viewReports(ReportedProductsPojo reportedProductsPojo) {
		// TODO Auto-generated method stub
		List<ReportedProductsPojo> reportlist = new ArrayList<ReportedProductsPojo>();
		
		try {
			callableStatement = GetConnection.connect().prepareCall("CALL ViewIssues()");
			resultSet = callableStatement.executeQuery();
			
			while(resultSet.next()) {
				ReportedProductsPojo reportedProductsPojo3 = new ReportedProductsPojo();
				reportedProductsPojo3.setReport_id(resultSet.getInt("report_id"));
				reportedProductsPojo3.setConsumer_port_id(resultSet.getInt("consumer_port_id"));
				reportedProductsPojo3.setProduct_id(resultSet.getInt("product_id"));
				reportedProductsPojo3.setIssue_type(resultSet.getString("issue_type"));
				reportedProductsPojo3.setSolution(resultSet.getString("solution"));
				reportedProductsPojo3.setReport_date(resultSet.getDate("report_date"));
				reportlist.add(reportedProductsPojo3);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return reportlist;
	}

	@Override
	public List<ProductsPojo> viewProducts(ProductsPojo productsPojo) {
		// TODO Auto-generated method stub
		List<ProductsPojo> productpojoList = new ArrayList<ProductsPojo>();
		try {
			callableStatement = GetConnection.connect().prepareCall("CALL ViewProductDetails()");
			resultSet = callableStatement.executeQuery();
			
			while(resultSet.next()) {
				ProductsPojo productsPojo3 = new ProductsPojo();
				productsPojo3.setProduct_id(resultSet.getInt("product_id"));
				productsPojo3.setProduct_name(resultSet.getString("product_name"));
				productsPojo3.setQuantity(resultSet.getInt("quantity"));
				productsPojo3.setPrice(resultSet.getFloat("price"));
				productpojoList.add(productsPojo3);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return productpojoList;
	}

	@Override
	public void Placeorder(OrdersPojo ordersPojo) {
		// TODO Auto-generated method stub
		try {
			callableStatement = GetConnection.connect().prepareCall("CALL PlaceOrder(?,?,?)");
			callableStatement.setInt(1, ordersPojo.getConsumer_port_id());
			callableStatement.setInt(2, ordersPojo.getProduct_id());
			callableStatement.setInt(2, ordersPojo.getQuantity());
			callableStatement.execute();
			System.out.println("Order Placed Successfully");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

//	@Override
//	public void CancelOrder(OrdersPojo ordersPojo) {
//		// TODO Auto-generated method stub
//		
//		
//	}

	@Override
	public List<OrdersPojo> viewOrders(OrdersPojo ordersPojo) {
		// TODO Auto-generated method stub
		List<OrdersPojo> ordersPojoslist = new ArrayList<OrdersPojo>();
		
		try {
			callableStatement = GetConnection.connect().prepareCall("CALL ViewOrders()");
			resultSet = callableStatement.executeQuery();
			
			while(resultSet.next()) {
				OrdersPojo ordersPojo3 = new OrdersPojo();
				ordersPojo3.setOrder_id(resultSet.getInt("order_id"));
				ordersPojo3.setProduct_id(resultSet.getInt("product_id"));
				ordersPojo3.setConsumer_port_id(resultSet.getInt("consumer_port_id"));
				ordersPojo3.setQuantity(resultSet.getInt("Quantity"));
				ordersPojo3.setOrder_date(resultSet.getDate("order_date"));
				ordersPojo3.setOrder_placed(resultSet.getBoolean("order_placed"));
				ordersPojo3.setShipped(resultSet.getBoolean("Shipped"));
				ordersPojo3.setOut_for_delivery(resultSet.getBoolean("Out for Delivery"));
				ordersPojo3.setDelivered(resultSet.getBoolean("Delivered"));
				ordersPojoslist.add(ordersPojo3);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// TODO Auto-generated method stub
		return ordersPojoslist;
	}

}
