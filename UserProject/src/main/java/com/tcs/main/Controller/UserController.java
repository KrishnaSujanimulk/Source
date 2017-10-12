package com.tcs.main.Controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.main.Domain.ChangePasswordBean;
import com.tcs.main.Domain.LoginUserBean;
import com.tcs.main.Domain.NewUserBean;
import com.tcs.main.Domain.PostloginUser;
import com.tcs.main.Service.UserService;

@RestController
public class UserController 
{
	@Autowired
	UserService userservice;
	@Autowired
	PostloginUser user;
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public LoginUserBean loginUser(@RequestBody LoginUserBean loginuser)
	{
		String uname = loginuser.getUsername();
		String passwd = loginuser.getPassword();
		

		String status = userservice.loginUser(uname,passwd);
		if (status.equals("active"))
		{
			String token = userservice.encryptMessage(uname);
			loginuser.setToken(token);
			loginuser.setRespCode("200");
			user.setStatus(status);
		}
		return loginuser;
	}
	
	@RequestMapping(value="/adduser", method = RequestMethod.POST)
	public String addUser(@RequestBody NewUserBean bean)
	{
		String adduser = userservice.addUser(bean);
		return adduser;
	}
	
	@RequestMapping(value="/changepassword" , method = RequestMethod.POST)
	public String changePassword(@RequestBody ChangePasswordBean changebean)
	{
		String message = userservice.changePassword(changebean);
		return message;
	}
	
	@RequestMapping(value="/tokenValidation" , method = RequestMethod.GET)
	public boolean tokenValidation(@RequestParam("token")String token)
	{
		boolean flag = userservice.tokenValidation(token);
		return flag;
	}
}
