package edu.zhangzl.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;

import edu.zhangzl.hibernate_entity.Administrator;
import edu.zhangzl.hibernate_entity.ExecutiveStaff;

public class LoginDao {

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}


	public boolean checkLogin(Administrator administrator) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Administrator.class);
		Example example = Example.create(administrator);
		example.enableLike(MatchMode.ANYWHERE);
		criteria.add(example);
		List list = criteria.list();
		if(list.size()!=0){
			return true; 
		}else{
			return false;
		}	
		
	}


	public boolean checkLoginOfExecutive_staff(ExecutiveStaff executiveStaff) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(ExecutiveStaff.class);
		Example example = Example.create(executiveStaff);
		example.enableLike(MatchMode.ANYWHERE);
		criteria.add(example);
		List list = criteria.list();
		if(list.size()!=0){
			return true; 
		}else{
			return false;
		}	
	}

}
