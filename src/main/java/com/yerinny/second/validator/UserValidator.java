package com.yerinny.second.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.yerinny.second.models.User;
import com.yerinny.second.services.UserIdeaService;

@Component
public class UserValidator implements Validator {
	private UserIdeaService uIS;
	
	public UserValidator(UserIdeaService uIS) {
		this.uIS = uIS;
	}
	
	@Override
    public boolean supports(Class<?> clazz) {
       return false;
    }
	
	@Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        
        if (!user.getPasswordConfirmation().equals(user.getPassword())) {
            errors.rejectValue("passwordConfirmation", "Match");
        }      
        
        if(uIS.findByEmail(user.getEmail()) != null) {
        		errors.rejectValue("email", "Taken");
        }
    }
}