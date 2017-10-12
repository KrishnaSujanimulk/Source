package bean;

public class CustomerBean {
	private String CustId;
	private String CustName;
	private String age;
	private String Address;
	private String contactNumber;
	private String country;

	public CustomerBean(String CustId, String CustName, String age, String Address, String contactNumber,
			String country) {
		super();
		this.CustId = CustId;
		this.CustName = CustName;
		this.age = age;
		this.Address = Address;
		this.contactNumber = contactNumber;
		this.country = country;

	}

	public String getCustId() {
		return CustId;
	}

	public void setCustId(String custId) {
		CustId = custId;
	}

	public String getCustName() {
		return CustName;
	}

	public void setCustName(String custName) {
		CustName = custName;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "CustomerBean [CustId=" + CustId + ", CustName=" + CustName + ", age=" + age + ", Address=" + Address
				+ ", contactNumber=" + contactNumber + ", country=" + country + "]";
	}
	

}
