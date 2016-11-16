package edu.zhangzl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import edu.zhangzl.dao.LoginDao;
import edu.zhangzl.hibernate_entity.Administrator;
import edu.zhangzl.hibernate_entity.ExecutiveStaff;

@Service("loginservice")
public class LoginService {
	
	
	@Autowired
	@Qualifier("loginDao")
	private LoginDao logindao;
	
	public boolean checkLogin(Administrator administrator) {
		// TODO Auto-generated method stub
		
		return logindao.checkLogin(administrator);
			
	}

	public boolean checkLoginOfExecutive_staff(ExecutiveStaff executiveStaff) {
		// TODO Auto-generated method stub
		
		return logindao.checkLoginOfExecutive_staff(executiveStaff);
	}

}
