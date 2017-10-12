package com.tcs.main.Service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcs.main.Domain.ChangePasswordBean;
import com.tcs.main.Domain.NewUserBean;
import com.tcs.main.Repository.UserDao;

@Service
public class UserService 
{
	@Autowired
	UserDao userdao;

	public String loginUser(String uname, String passwd) 
	{
		return userdao.loginUser(uname,passwd);	
	}

	public String encryptMessage(String uname) 
	{
		return userdao.encryptMessage(uname);
	}

	public ArrayList decryptMessage(String encryptedToken) 
	{
		return userdao.decryptMessage(encryptedToken);
		
	}

	public String addUser(NewUserBean bean) 
	{
		return userdao.addUser(bean);
	}

	public String changePassword(ChangePasswordBean changebean) 
	{
		return userdao.changePassword(changebean);
	}

	public boolean tokenValidation(String token) 
	{
		return userdao.tokenValidation(token);
	}
}
