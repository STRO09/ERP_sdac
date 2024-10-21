package implementors;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import interfaces.Seller;
import jdbc.GetConnection;
import models.OrdersPojo;
import models.ProductsPojo;
import models.ReportedProductsPojo;
import models.SellerPojo;

public class SellerImplementor implements Seller{

	CallableStatement callableStatement = null;
	ResultSet resultSet = null;
	@Override
	public void RegisterSeller(SellerPojo sellerPojo) {
		// TODO Auto-generated method stub
		try {
			callableStatement = GetConnection.connect().prepareCall("CALL RegisterUser(?,?,?,?)");
			callableStatement.setInt(1, sellerPojo.getPort_id());
			callableStatement.setString(2, sellerPojo.getPassword());
			callableStatement.setString(3, null);
			callableStatement.setString(4, sellerPojo.getRole());
			callableStatement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public boolean LoginSeller(SellerPojo sellerPojo) {
		// TODO Auto-generated method stub
		boolean auth_status = false;
		int auth_int = 0;
		
		try {
			callableStatement = GetConnection.connect().prepareCall("CALL AuthenticateUser(?,?,?)");
			callableStatement.setInt(1, sellerPojo.getPort_id());
			callableStatement.setString(2, sellerPojo.getPassword());
			callableStatement.setString(3, sellerPojo.getRole());
			auth_int =callableStatement.executeUpdate();
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
	public void updateProductDetails(ProductsPojo productsPojo) {
		// TODO Auto-generated method stub
		try {
			callableStatement = GetConnection.connect().prepareCall("CALL UpdateProductDetails(?,?,?)");
			callableStatement.setInt(1, productsPojo.getProduct_id());
			callableStatement.setInt(2, productsPojo.getQuantity());
			callableStatement.setFloat(3, productsPojo.getPrice());
			callableStatement.execute();
			System.out.println("Product Details Updared Successfully");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	@Override
	public List<ProductsPojo> viewProducts(ProductsPojo productsPojo) {
		// TODO Auto-generated method stub

		List<ProductsPojo> productpojoList = new ArrayList<ProductsPojo>();
		try {
			callableStatement = GetConnection.connect().prepareCall("CALL ViewProductDetails()");
			resultSet = callableStatement.executeQuery();
			
			while(resultSet.next()) {
				ProductsPojo productsPojo2 = new ProductsPojo();
				productsPojo2.setProduct_id(resultSet.getInt("product_id"));
				productsPojo2.setProduct_name(resultSet.getString("product_name"));
				productsPojo2.setQuantity(resultSet.getInt("quantity"));
				productsPojo2.setPrice(resultSet.getFloat("price"));
				productpojoList.add(productsPojo2);
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
	public void updateOrderStatus(OrdersPojo ordersPojo) {
		// TODO Auto-generated method stub
		try {
			callableStatement = GetConnection.connect().prepareCall("CALL UpdateOrderStatus(?,?)");
			callableStatement.setInt(1, ordersPojo.getOrder_id());
			callableStatement.setString(2, ordersPojo.getNew_status());
			callableStatement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

//	@Override
//	public void resolveIssue(ReportedProductsPojo reportedProductsPojo) {
//		// TODO Auto-generated method stub
//		callableStatement.set
//		
//	}

	@Override
	public List<ReportedProductsPojo> viewIssues(ReportedProductsPojo reportedProductsPojo) {
		// TODO Auto-generated method stub
		List<ReportedProductsPojo> reportlist = new ArrayList<ReportedProductsPojo>();
		
		try {
			callableStatement = GetConnection.connect().prepareCall("CALL ViewIssues()");
			resultSet = callableStatement.executeQuery();
			
			while(resultSet.next()) {
				ReportedProductsPojo reportedProductsPojo2 = new ReportedProductsPojo();
				reportedProductsPojo2.setReport_id(resultSet.getInt("report_id"));
				reportedProductsPojo2.setConsumer_port_id(resultSet.getInt("consumer_port_id"));
				reportedProductsPojo2.setProduct_id(resultSet.getInt("product_id"));
				reportedProductsPojo2.setIssue_type(resultSet.getString("issue_type"));
				reportedProductsPojo2.setSolution(resultSet.getString("solution"));
				reportedProductsPojo2.setReport_date(resultSet.getDate("report_date"));
				reportlist.add(reportedProductsPojo2);
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
	public List<OrdersPojo> viewOrders(OrdersPojo ordersPojo) {

		List<OrdersPojo> ordersPojoslist = new ArrayList<OrdersPojo>();
		
		try {
			callableStatement = GetConnection.connect().prepareCall("CALL ViewOrders()");
			resultSet = callableStatement.executeQuery();
			
			while(resultSet.next()) {
				OrdersPojo ordersPojo2 = new OrdersPojo();
				ordersPojo2.setOrder_id(resultSet.getInt("order_id"));
				ordersPojo2.setProduct_id(resultSet.getInt("product_id"));
				ordersPojo2.setConsumer_port_id(resultSet.getInt("consumer_port_id"));
				ordersPojo2.setQuantity(resultSet.getInt("Quantity"));
				ordersPojo2.setOrder_date(resultSet.getDate("order_date"));
				ordersPojo2.setOrder_placed(resultSet.getBoolean("order_placed"));
				ordersPojo2.setShipped(resultSet.getBoolean("Shipped"));
				ordersPojo2.setOut_for_delivery(resultSet.getBoolean("Out for Delivery"));
				ordersPojo2.setDelivered(resultSet.getBoolean("Delivered"));
				ordersPojoslist.add(ordersPojo2);
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