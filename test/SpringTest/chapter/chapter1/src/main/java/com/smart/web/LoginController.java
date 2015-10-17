package com.smart.web;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.smart.domain.User;
import com.smart.service.UserService;

@Controller
@RequestMapping(value="/admin")
public class LoginController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/login.html")
	public String loginPage(){
		return "login";
	}
	
	@RequestMapping(value="/loginCheck.html")
	public ModelAndView loginCheck(HttpServletRequest request,LoginCommand loginCommand){
		boolean isValidUser = userService.hasMatchUser(loginCommand.getUserName(), loginCommand.getPassword());
		if(!isValidUser){
			return new ModelAndView("login","error","用户名或密码错误");
		}else{
			User user = userService.findUserByUserName(loginCommand.getUserName());
			user.setLastIp(request.getRemoteAddr());
			user.setLastVisit(new Date());
			userService.loginSucess(user);
			request.getSession().setAttribute("user", user);
			return new ModelAndView("main");
		}
	}
}
