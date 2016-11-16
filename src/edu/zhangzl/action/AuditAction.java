package edu.zhangzl.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.zhangzl.hibernate_entity.Administrator;
import edu.zhangzl.service.AuditService;
import edu.zhangzl.tools.BasicAction;

@Controller
@RequestMapping("view/AuditAction")
public class AuditAction {
	@Autowired
	@Qualifier("basicAction")
	private BasicAction basicAction;
	
	@Autowired
	@Resource
	private AuditService auditService;
	
	@RequestMapping("/getAdministrator.do")
	public void getAdministrator(String flag,HttpServletRequest request,HttpServletResponse response){
		 List<Administrator> list = auditService.getAdministrator(flag);
	     synchronized (basicAction) {
	    	 basicAction.setRequestAndResponse(request, response);
	    	 basicAction.responseMsg(list);
	     }
	}
	
	@RequestMapping("/changeByChecked.do")
	public void changeByChecked(String ids,String flag,HttpServletRequest request,HttpServletResponse response){
		 auditService.changeByChecked(ids,flag);
		 synchronized (basicAction) {
	    	 basicAction.setRequestAndResponse(request, response);
	    	 basicAction.responseMsg(1);
	     }
	}
	
	
	@RequestMapping("/deleteOneByCheck.do")
	public void deleteOneByCheck(String id){
		auditService.deleteOneByCheck(id);
	}
	
	
	@RequestMapping("/allowOneByCheck.do")
	public void allowOneByCheck(String id){
		auditService.allowOneByCheck(id);
	}
}
