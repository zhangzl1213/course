package edu.zhangzl.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import edu.zhangzl.hibernate_entity.Administrator;

public class Administrator_validator implements Validator{

	@Override
	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return Administrator.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		// TODO Auto-generated method stub
		Administrator addAdministrator = (Administrator)arg0;
		ValidationUtils.rejectIfEmpty(arg1, "name", "name.required");
		ValidationUtils.rejectIfEmpty(arg1, "count", "count.required");
		
	}

	
}
