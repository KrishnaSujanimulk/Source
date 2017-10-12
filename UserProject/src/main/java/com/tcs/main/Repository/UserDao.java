package com.tcs.main.Repository;

import java.util.ArrayList;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tcs.main.Domain.ChangePasswordBean;
import com.tcs.main.Domain.LoginUserBean;
import com.tcs.main.Domain.NewUserBean;
import com.tcs.main.Domain.PostloginUser;

@Repository
@Transactional
public class UserDao 
{
	@Autowired
	@PersistenceContext
	private EntityManager entitymanager;
	
	@Autowired
	LoginUserBean userbean;
	@Autowired
	PostloginUser user;
	public String loginUser(String uname, String passwd) 
	{
		PostloginUser user=null;
		user = (PostloginUser) this.entitymanager.createQuery("from PostloginUser where username=:uname").setParameter("uname",uname).getSingleResult();
		String status = user.getStatus();
		String password = user.getPassword();
		if(password.equals(passwd))
		{
		return status;
		}
	return status;
	}
	
	public String encryptMessage(String userName)
	{
	
	String out = "";
	String rr1 = "";
	String rr2 = "";
	long currentTimeplusoffset = 0;
	
	int offsetTime;
	int r1 = 0;
	int r2 = 0;
	int sw = -1;
	int i = 0;
	
	try{
		offsetTime = 30;
		currentTimeplusoffset = System.currentTimeMillis() + offsetTime * 60 * 1000;
		userName = userName + "," + currentTimeplusoffset;
		r1 = 0;
		r2 = 0;
		sw = -1;
		i = 0;
		rr1 = "";
		rr2 = "";
		Random randomGenerator = new Random();
		r1 = randomGenerator.nextInt(100);
		r2 = randomGenerator.nextInt(100);
		for (i=0;i<userName.length();i++)
		{
			if (sw==-1)
			{
				out = out +(((255 - userName.charAt(i))) + r1 + 300);
			}
			else
			{
				out = out +(((255 - userName.charAt(i))) + r2 + 300);
			}
			sw = sw * -1;
		}
		rr1 = r1 + "";
		rr2 = r2 + "";
		out = rr1.length() + "" + rr2.length() + rr1 + rr2 + out;
		randomGenerator = null;
	}
	catch(Exception e){
		System.out.println("Exception :" + e.getMessage());
	}
    return out;
}

	public ArrayList decryptMessage(String encryptedToken) 
	{
		String inp = encryptedToken;
		String inpp = "";
		String out = "";
		Integer out1 = 0;
		char out2;
		int r1 = 0;
		int r2 = 0;
		int sw = -1;
		ArrayList outResult = new ArrayList();
		try
		{
			r1 = Integer.parseInt(inp.charAt(0) + "");
			r2 = Integer.parseInt(inp.charAt(1) + "");
			inp = inp.substring(2, inp.length());
			r1 = Integer.parseInt(inp.substring(0, r1));
			inp = inp.substring((r1 + "").toString().length(), inp.length());
			r2 = Integer.parseInt(inp.substring(0, r2));
			inp = inp.substring((r2 + "").toString().length(), inp.length());
			inpp = inp;
			
			for (int i = 0; i < inpp.length(); i += 3)
			{
				out1 = Integer.parseInt(inp.substring(0, 3));
				out1 = Integer.parseInt(inp.substring(0, 3)) - 300;
				if(sw == -1)
				{
					out1 = out1 - r1;
				}
				else
				{
					out1 = out1 - r2;
				}
				out1 = 255 - out1;
				out2 = (char) (out1 + 0);
				out = out + out2;
				inp = inp.substring(3, inp.length());
				sw = sw * -1;
			}
			String[] s= out.split(",");
			outResult.add(s[0]);
			outResult.add(s[1]);
		}
	catch(Exception e)
		{
			System.out.println("Exception : " + e.getMessage());
		}
		return outResult;
	}

	public String addUser(NewUserBean bean) 
	{
		String addSuccess = null;
		String token = bean.getToken();
		ArrayList list = new ArrayList();
		list = decryptMessage(token);
		String username = (String) list.get(0);
		long time = (Long.parseLong((String) list.get(1)));
		long currentTimePlusOffset = System.currentTimeMillis();
		if(currentTimePlusOffset-time>=0)
		{
			addSuccess = "Session Expired";
		}
		else
		{
			//PostloginUser user = null;
			user.setUsername(bean.getUsername());
			user.setPassword(bean.getPassword());
			user.setStatus("active");
			this.entitymanager.persist(user);
			addSuccess = bean.getUsername() + " " + "added successfully";
		}
		return addSuccess;
	}

	public String changePassword(ChangePasswordBean changebean) 
	{
		String changeSuccess = null;
		String token = changebean.getToken();
		String olddpassword = changebean.getOldPassword();
		String newpassword = changebean.getNewPassword();
		ArrayList list = new ArrayList();
		list = decryptMessage(token);
		String uname = (String) list.get(0);
		long time = (Long.parseLong((String) list.get(1)));
		long currentTimePlusOffset = System.currentTimeMillis();
		if(currentTimePlusOffset-time>=0)
		{
			changeSuccess = "Session Expired";
		}
		else
		{
			user.setUsername(uname);
			user = (PostloginUser) this.entitymanager.createQuery("from PostloginUser where username=:uname").setParameter("uname",uname).getSingleResult();
			if((user.getUsername()).equals(uname) && (user.getPassword()).equals(olddpassword))
					{
						int value = this.entitymanager.createQuery("UPDATE PostloginUser set password='"+newpassword+"'where username=:uname").setParameter("uname",uname).executeUpdate();
						System.out.println(value + " " + "records updated" );
						return "Password changed successfully for" + " " +uname;
					}
			else
			{
				return "wrong password provided";
			}
		}
		
		return changeSuccess;
	}

	public boolean tokenValidation(String token) 
	{
		String message = null;
		ArrayList list = new ArrayList();
		list = decryptMessage(token);
		String username = (String) list.get(0);
		PostloginUser user=null;
		user = (PostloginUser) this.entitymanager.createQuery("from PostloginUser where username=:uname").setParameter("uname",username).getSingleResult();
		if(null!=user)
		{
			if(username.equals(user.getUsername()))
			{
				long time = (Long.parseLong((String) list.get(1)));
				long currentTimePlusOffset = System.currentTimeMillis();
				if(currentTimePlusOffset-time>=0)
				{
					System.out.println("Session Expired");
					return false;
				}
				return true;
			}
		}
		return false;
	}
}
