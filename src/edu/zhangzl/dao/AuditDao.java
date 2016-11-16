package edu.zhangzl.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder.In;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import edu.zhangzl.hibernate_entity.Administrator;

public class AuditDao {
	
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public List<Administrator> getAdministrator(String flag) {
		// TODO Auto-generated method stub
		String hql = "";
		Session session = this.sessionFactory.openSession();
		if(flag.equals("1")){
			hql = "from Administrator admin where admin.flag=1";
		}else{
			hql = "from Administrator admin where admin.flag=0";
		}
		Query query = session.createQuery(hql);
		return query.list();
	}

	public void changeByChecked(String ids, String flag) {
		// TODO Auto-generated method stub
		if(flag.equals("1")){
			String [] id = ids.split(",");
			Session session = this.sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			for(int i=0;i<id.length;i++){
				Administrator administrator = (Administrator)session.get(Administrator.class,Integer.parseInt(id[i]));
				administrator.setFlag(0);
				session.update(administrator);
				//Transaction tx = null ;
				//try {
					//tx = session.beginTransaction();
					//session.update(administrator);
					//tx.commit();
				//} catch (Exception e) {
					// TODO: handle exception
					//tx.rollback();
				//}
			}
			
			try {
				tx.commit();
			} catch (Exception e) {
				// TODO: handle exception
				tx.rollback();
			}finally{
				session.close();
			}
		}else{
			if(flag.equals("2")){
				String [] id = ids.split(",");
				Session session = this.sessionFactory.openSession();
				Transaction tx = session.beginTransaction();
				String hql = "delete from Administrator where id=?";
				for(int i=0;i<id.length;i++){
					Query query = session.createQuery(hql);
					query.setInteger(0, Integer.parseInt(id[i]));
					query.executeUpdate();
				}
				
				try {
					tx.commit();
				} catch (Exception e) {
					// TODO: handle exception
					tx.rollback();
				}finally{
					session.close();
				}
			}else{
				String [] id = ids.split(",");
				Session session = this.sessionFactory.openSession();
				Transaction tx = session.beginTransaction();
				for(int i=0;i<id.length;i++){
					Administrator administrator = (Administrator)session.get(Administrator.class, Integer.parseInt(id[i]));
					administrator.setFlag(1);	
					session.update(administrator);
				}
				try {
					tx.commit();
				} catch (Exception e) {
					// TODO: handle exception
					tx.rollback();
				}finally{
					session.close();
				}
			}
			
		}
		
	}

	public void deleteOneByCheck(String id) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		String hql="delete from Administrator where id = ?";
		Query query = session.createQuery(hql);
		query.setInteger(0, Integer.parseInt(id));
		query.executeUpdate();
		try {
			tx.commit();
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
		}finally{
			session.close();
		}
	}

	public void allowOneByCheck(String id) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Administrator administrator = (Administrator)session.get(Administrator.class, Integer.parseInt(id));
		administrator.setFlag(0);
		session.update(administrator);
		try {
			tx.commit();
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
		}finally{
			session.close();
		}
	}
	
}
