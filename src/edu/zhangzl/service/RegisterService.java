package edu.zhangzl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import edu.zhangzl.dao.RegisterDao;
import edu.zhangzl.hibernate_entity.Administrator;


@Service("registerService")
public class RegisterService {

	@Autowired
	@Qualifier("registerDao")
	private RegisterDao registerDao;
	
	public void addAdministor(Administrator administrator) {
		// TODO Auto-generated method stub
		registerDao.addAdministor(administrator);
	}

	public boolean checkCountOrName(Administrator administrator) {
		// TODO Auto-generated method stub
		return this.registerDao.checkCountOrName(administrator);
		
	}
	
}
