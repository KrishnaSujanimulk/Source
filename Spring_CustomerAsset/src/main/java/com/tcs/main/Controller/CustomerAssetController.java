package com.tcs.main.Controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.main.Domain.CustomerAssetBean;
import com.tcs.main.Service.CustomerAssetService;

@RestController
public class CustomerAssetController 
{
	@Autowired
	CustomerAssetService customerservice;
	
	@RequestMapping(method = RequestMethod.GET , path = "/getaccount")
	public CustomerAssetBean getAccount(@RequestParam("accountid")String accountid)
	{
		return customerservice.getAccount(accountid);
		
	}
	@RequestMapping(method = RequestMethod.GET , path = "/getallaccounts")
	public Collection<CustomerAssetBean> getallAccounts()
	{
		return customerservice.getallAccounts();
	}

}
