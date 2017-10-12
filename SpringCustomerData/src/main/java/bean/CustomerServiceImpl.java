package bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bean.CustomerDaoImpl;
@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	private CustomerDaoImpl customerdaoimpl;

	public void addCustomer(String CustId, String CustName, String age, String Address, String contactNumber,
			String country) {
		customerdaoimpl.addCustomer(CustId, CustName, age, Address, contactNumber, country);
	}

}
