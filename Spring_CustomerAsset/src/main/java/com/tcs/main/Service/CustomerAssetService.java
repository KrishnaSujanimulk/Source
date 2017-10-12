package com.tcs.main.Service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcs.main.Domain.CustomerAssetBean;
import com.tcs.main.Repository.CustomerAssetRepository;

@Service
public class CustomerAssetService 
{

	@Autowired
	CustomerAssetRepository customerrepository;
	public CustomerAssetBean getAccount(String accountid) 
	{
		return customerrepository.findOne(accountid);
	}
	public Collection<CustomerAssetBean> getallAccounts() 
	{
		return customerrepository.findAll();
	}
	
}
