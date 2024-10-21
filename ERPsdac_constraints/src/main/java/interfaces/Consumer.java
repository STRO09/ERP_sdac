package interfaces;
import models.ConsumerPojo;
import models.OrdersPojo;
import models.ProductsPojo;
import models.ReportedProductsPojo;

import java.util.List;

public interface Consumer {

	void RegisterConsumer(ConsumerPojo consumerPojo);

	public boolean LoginConsumer(ConsumerPojo consumerPojo);

	void updateProfile(ConsumerPojo consumerPojo);


	void reportProduct(ReportedProductsPojo productsPojo);

	public List<ReportedProductsPojo> viewReports(ReportedProductsPojo reportedProductsPojo);

	public List<ProductsPojo> viewProducts(ProductsPojo productsPojo);

	public void Placeorder(OrdersPojo ordersPojo);

//	void CancelOrder(OrdersPojo ordersPojo);

	public List<OrdersPojo> viewOrders(OrdersPojo ordersPojo);

}