package edu.zhangzl.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import edu.zhangzl.hibernate_entity.Administrator;

public class RegisterDao {
	
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void addAdministor(Administrator administrator) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		administrator.setFlag(1);
		session.save(administrator);
		session.close();
		//sessionFactory.close();
	}

	public boolean checkCountOrName(Administrator administrator) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.openSession();
		String hql = null;
		Query query = null; 
		if(administrator.getName()!=null){
			hql = "from Administrator admin where admin.name like ?";
		    query = session.createQuery(hql);
		    query.setString(0, administrator.getName());
		    if(query.list().size()!=0){
		    	session.close();
		    	return true;
		    }
		}else{
			hql = "from Administrator admin where admin.count like ? ";
			query = session.createQuery(hql);
			query.setString(0, administrator.getCount());
			if(query.list().size()!=0){
				session.close();
				return true;
			}
		}
		session.close();
		return false;
	}
	
	
	
	
}
