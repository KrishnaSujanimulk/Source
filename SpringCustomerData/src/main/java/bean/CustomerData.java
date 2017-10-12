package bean;

import java.util.Collection;

import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import bean.CustomerDaoImpl;
import bean.CustomerServiceImpl;

@SpringBootApplication
@RestController
public class CustomerData {
	@Autowired
	private CustomerServiceImpl customerServiceImpl;

	public static void main(String[] args) {
		SpringApplication.run(CustomerData.class, args);
	}

	@ResponseBody
	@RequestMapping(value = "/addCustomer", method = RequestMethod.POST)
	public void addCustomer(@RequestBody String customer) {
		JSONParser parser = new JSONParser();
		org.json.simple.JSONObject object = null;

		try {
			object = (org.json.simple.JSONObject) parser.parse(customer);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String CustId = (String) object.get("CustId");
		String CustName = (String) object.get("custName");
		String age = (String) object.get("age");
		String address = (String) object.get("address");
		String contactNumber = (String) object.get("contactNumber");
		String country = (String) object.get("country");
		customerServiceImpl.addCustomer(CustId, CustName, age, address, contactNumber, country);
		
	}

	@ResponseBody
	@RequestMapping(value = "/getAllCustomers", method = RequestMethod.GET)
	public Collection getAllcustomers() {
		System.out.println(CustomerDaoImpl.getAllcustomers());
		return CustomerDaoImpl.getAllcustomers();
	}

	@ResponseBody
	@RequestMapping(value = "/deleteCustomer", method = RequestMethod.POST)
	public void deleteCustomer(@RequestBody String Id) {
		JSONParser parser = new JSONParser();
		org.json.simple.JSONObject object = null;
		try {
			object = (org.json.simple.JSONObject) parser.parse(Id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String removeId = (String) object.get("customer_id");
		CustomerDaoImpl.deleteCustomer(removeId);
	}

	@ResponseBody
	@RequestMapping(value = "/updateCustomer", method = RequestMethod.POST)
	public void updateCustomer(@RequestBody String id) {
		JSONParser parser = new JSONParser();
		org.json.simple.JSONObject object = null;
		try {
			object = (org.json.simple.JSONObject) parser.parse(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String updateId = (String) object.get("customer_id");
		String CustName = (String) object.get("CustName");
		String age = (String) object.get("age");
		String Address = (String) object.get("Address");
		String city = (String) object.get("city");
		String contactNumber = (String) object.get("contact_number");
		String country = (String) object.get("country");
		CustomerDaoImpl.updateCustomer(updateId, CustName, age, Address, contactNumber, country);
	}

	@ResponseBody
	@RequestMapping(value = "/retrieveCustomer", method = RequestMethod.POST)
	public String retrieveCustomer(@RequestBody String id) {
		JSONParser parser = new JSONParser();
		org.json.simple.JSONObject object = null;
		try {
			object = (org.json.simple.JSONObject) parser.parse(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String retrieveId=(String) object.get("customer_id");
		System.out.println(CustomerDaoImpl.retrieveCustomer(retrieveId));
		return CustomerDaoImpl.retrieveCustomer(retrieveId);

	}
}
