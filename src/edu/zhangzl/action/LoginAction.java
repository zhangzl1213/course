package edu.zhangzl.action;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.zhangzl.hibernate_entity.Administrator;
import edu.zhangzl.hibernate_entity.ExecutiveStaff;
import edu.zhangzl.service.LoginService;
import edu.zhangzl.tools.BasicAction;


@Controller
@RequestMapping("view/loginAction")
public class LoginAction {
	
	@Autowired
	@Resource
	private LoginService loginservice;
	
	@Autowired
	@Qualifier("basicAction")
	private BasicAction basicAction;
	
	@RequestMapping("/administrator_login.do")
	public String checkLogin(Administrator administrator,HttpServletRequest request,HttpServletResponse response){
		boolean isChecked = loginservice.checkLogin(administrator);
		if(isChecked){
			return "success";
		}else{
			synchronized (basicAction) {
				basicAction.setRequestAndResponse(request,response);
				basicAction.responseMsg("账号或密码不正确，请确认!!");
			}
			return null;
		}
	}
	
	
	@RequestMapping("executive_staff_login.do")
	public String checkLoginOfExecutive_staff(ExecutiveStaff executiveStaff, HttpServletRequest request, HttpServletResponse response ){
		
		boolean isChecked = loginservice.checkLoginOfExecutive_staff(executiveStaff);
		if(isChecked){
			return "success";
		}else{
			synchronized (basicAction) {
				basicAction.setRequestAndResponse(request,response);
				basicAction.responseMsg("账号或密码不正确，请确认!!");
			}
			return null;
		}
	
	}
}
