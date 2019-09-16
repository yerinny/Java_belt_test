package com.yerinny.second.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yerinny.second.models.Idea;
import com.yerinny.second.models.User;
import com.yerinny.second.services.UserIdeaService;
import com.yerinny.second.validator.UserValidator;

@Controller
public class UserIdeaController {
	
	private final UserIdeaService uIS;
	private final UserValidator uV;
	
	public UserIdeaController(UserIdeaService uIS, UserValidator uV) {
		this.uIS = uIS;
		this.uV = uV;
	}
	
	@GetMapping("/")
	public String registerForm(@ModelAttribute("user") User user, HttpSession session) {
		if(session.isNew()) {
			session.setAttribute("login", false);
		}
		return"main.jsp";
	}
	
	@PostMapping("/register")
	public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session, RedirectAttributes rA) {
        uV.validate(user, result);
        if(result.hasErrors()) {
            return "main.jsp";
        }else {
        	uIS.registerUser(user);
        	rA.addFlashAttribute("success","Registration is sucessful! You may login now");
        	return "redirect:/";
        }

    }
	
	@PostMapping("/login")
	public String loginUser(@RequestParam("email") String email, @RequestParam("password") String password, Model model, HttpSession session,RedirectAttributes rA) {
        if(uIS.checkAuthenticated(email, password)== false) {
        	rA.addFlashAttribute("error", "Invalid Credentials");
        	return "redirect:/";
        }else {
        	session.setAttribute("login", true);
        	session.setAttribute("user", uIS.findByEmail(email).getId());
        	return"redirect:/home";
        }
    }
	
	@GetMapping("/home")
	public String home(HttpSession session, Model model) {
		if((boolean)session.getAttribute("login") == true) {
			model.addAttribute("user", uIS.findUserById((Long)session.getAttribute("user")));
//			model.addAttribute("ideas", uIS.allIdeas());
			model.addAttribute("userIdea", uIS.getAllIdeasWithTheirUser() );
			return "home.jsp";
		}
		else {
			return"redirect:/";
		}
    }
	

	@GetMapping("/ideas/new")
	public String addIdea(@ModelAttribute("ideaObj") Idea idea, HttpSession session) {
		if((boolean)session.getAttribute("login")==true) {
			return "addIdea.jsp";
		}else {
		return "redirect:/";
	}
}

	@PostMapping("/ideas/new")
	public String processCourse(@Valid @ModelAttribute("ideaObj")Idea idea,BindingResult result, HttpSession session) {
		if(result.hasErrors()) {
			
			return"addIdea.jsp";
		}
		else {
			idea.setUser(uIS.findUserById((Long)session.getAttribute("user")));
			uIS.addIdea(idea);
			return"redirect:/home";
		}
	}

		@GetMapping("/ideas/{ideaId}")
		public String showCourse(HttpSession session,@PathVariable("ideaId")Long cId,Model model) {
			if((boolean)session.getAttribute("login") == true) {
				
				model.addAttribute("idea", uIS.findIdeaById(cId));
				model.addAttribute("userCheck", uIS.findUserById((Long)session.getAttribute("user")));
				model.addAttribute("userIdea", uIS.getAllIdeasWithTheirUser() );
				
				return "ideaInfo.jsp";
			}
			else {
				return"redirect:/";
			}
		}
		
		@DeleteMapping("/ideas/{ideaId}/delete")
		public String delete(@PathVariable("ideaId") Long id) {
			uIS.deleteIdeaById(id);
			return "redirect:/home";

		}	
		@GetMapping("/ideas/{ideaId}/edit")
		public String editIdea(@ModelAttribute("idea")Idea idea,@PathVariable("ideaId")Long iId,HttpSession session,Model model) {
			if((boolean)session.getAttribute("login") == true) {
				model.addAttribute("idea", uIS.findIdeaById(iId));
				return "editIdea.jsp";
			}
			else {
				return"redirect:/";
			}
		}
		
		@PutMapping("/ideas/{ideaId}/edit")
		public String update(@Valid @ModelAttribute("ideaObj")Idea idea,BindingResult result,@PathVariable("ideaId")Long iId, HttpSession session,Model model) {
			User user = uIS.findUserById((Long)session.getAttribute("user"));
			if(result.hasErrors()) {
				model.addAttribute("idea", uIS.findIdeaById(iId));
				return"editIdea.jsp";
			}
			else {
				idea.setId(iId);
				idea.setUser(user);
				uIS.addIdea(idea);
				return"redirect:/home";
			}
		}
		
		@GetMapping("/logout")
		public String logout(HttpSession session) {
			session.invalidate();
			return"redirect:/";
		}
		
}
	
