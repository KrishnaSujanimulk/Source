package bean;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import bean.CustomerBean;
@Repository
public class CustomerDaoImpl implements CustomerDao {
	private static final Map<String, CustomerBean> customermap = new HashMap<String, CustomerBean>();

	public void addCustomer(String CustId, String CustName, String age, String Address, String contactNumber,
			String country) {

		CustomerBean bean = new CustomerBean(CustId, CustName, age, Address, contactNumber, country);
		customermap.put(CustId, bean);

	}

	public static Collection getAllcustomers() {
		System.out.println(customermap.values());
		return customermap.values();
	}

	public static void deleteCustomer(String removeId) {
		customermap.remove(removeId);
	}

	public static void updateCustomer(String updateId, String CustName, String age, String Address,
			String contactNumber, String country) {

		CustomerBean bean = new CustomerBean(updateId, CustName, age, Address, contactNumber, country);
		customermap.put(updateId, bean);
	}

	public static String retrieveCustomer(String retrieveId) {
		System.out.println(retrieveId);
		return customermap.get(retrieveId).toString();
	}

}
