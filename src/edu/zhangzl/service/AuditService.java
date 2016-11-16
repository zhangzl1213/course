package edu.zhangzl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import edu.zhangzl.dao.AuditDao;
import edu.zhangzl.hibernate_entity.Administrator;

@Service("auditService")
public class AuditService {
	@Autowired
	@Qualifier("auditDao")
	public AuditDao auditDao;
	
	public List<Administrator> getAdministrator(String flag) {
		// TODO Auto-generated method stub
		return auditDao.getAdministrator(flag);
	}

	public void changeByChecked(String ids, String flag) {
		// TODO Auto-generated method stub
		auditDao.changeByChecked(ids,flag);
	}

	public void deleteOneByCheck(String id) {
		// TODO Auto-generated method stub
		auditDao.deleteOneByCheck(id);
	}

	public void allowOneByCheck(String id) {
		// TODO Auto-generated method stub
		auditDao.allowOneByCheck(id);
	}

}
