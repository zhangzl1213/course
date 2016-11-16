package edu.zhangzl.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.zhangzl.hibernate_entity.Administrator;
import edu.zhangzl.service.RegisterService;
import edu.zhangzl.tools.BasicAction;
import edu.zhangzl.tools.JsonFlag;
import edu.zhangzl.validator.Administrator_validator;

@Controller
@RequestMapping("view/registerAction")
public class RegisterAction {

	@Resource
	private RegisterService registerService;

	@Autowired
	@Qualifier("basicAction")
	private BasicAction basicAction;

	@InitBinder
	public void initBinder(DataBinder dataBinder){
		dataBinder.setValidator(new Administrator_validator());
	}
	
	@RequestMapping("/administrator_register.do")
	public void addAdministor(@Validated Administrator administrator,
			BindingResult bindingResult, Model model,
			HttpServletResponse response, HttpServletRequest request) {
//		Administrator_validator administrator_validator = new Administrator_validator();
//		administrator_validator.validate(administrator, bindingResult);
		if (bindingResult.hasErrors()) {
			model.addAttribute("allerror", bindingResult.getAllErrors());
			synchronized (basicAction) {
				basicAction.setRequestAndResponse(request, response);
				basicAction.responseMsg(new JsonFlag());
			}
		} else{
			registerService.addAdministor(administrator);
			synchronized (basicAction) {
				basicAction.setRequestAndResponse(request, response);
				JsonFlag f = new JsonFlag();
				f.setFlag(2);
				basicAction.responseMsg(f);
			}
		}
		
		
		
	}

	@RequestMapping("/checkCountOrName.do")
	public void checkCountOrName(Administrator administrator,
			HttpServletRequest request, HttpServletResponse response) {
		boolean flag = registerService.checkCountOrName(administrator);
		if (flag) {
			synchronized (basicAction) {
				basicAction.setRequestAndResponse(request, response);
				JsonFlag f = new JsonFlag();
				f.setFlag(2);
				basicAction.responseMsg(f);
//				if (administrator.getCount() != null) {
//					basicAction.responseMsg("该账号已被占用");
//				} else {
//					basicAction.responseMsg("该用户名已被占用");
//				}
			}
		} else {
			synchronized (basicAction) {
				basicAction.setRequestAndResponse(request, response);
				JsonFlag f = new JsonFlag();
				f.setFlag(1);
				basicAction.responseMsg(f);
			}
		}
	}
}
