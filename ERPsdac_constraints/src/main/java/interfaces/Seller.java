package interfaces;

import java.util.List;


import models.OrdersPojo;
import models.ProductsPojo;
import models.ReportedProductsPojo;
import models.SellerPojo;


public interface Seller {
	void RegisterSeller(SellerPojo sellerPojo);

	public boolean LoginSeller(SellerPojo sellerPojo);
	
	void updateProductDetails(ProductsPojo productsPojo);

	public List<ProductsPojo> viewProducts(ProductsPojo productsPojo);

	void updateOrderStatus(OrdersPojo ordersPojo);

//	void resolveIssue(ReportedProductsPojo reportedProductsPojo);

	public List<ReportedProductsPojo> viewIssues(ReportedProductsPojo reportedProductsPojo);

	public List<OrdersPojo> viewOrders(OrdersPojo ordersPojo);

}