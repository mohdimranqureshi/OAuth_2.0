package com.xavient.OAuth.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.brickred.socialauth.AuthProvider;
import org.brickred.socialauth.Profile;
import org.brickred.socialauth.SocialAuthManager;
import org.brickred.socialauth.spring.bean.SocialAuthTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SuccessController {

	private final Log LOG = LogFactory.getLog(getClass());

	@Autowired
	private SocialAuthTemplate socialAuthTemplate;

	@RequestMapping(value = "/authSuccess")
	public ModelAndView getRedirectURL(final HttpServletRequest request) throws Exception {
		ModelAndView model = new ModelAndView();

		SocialAuthManager manager = socialAuthTemplate.getSocialAuthManager();
		AuthProvider provider = manager.getCurrentAuthProvider();

		Profile profile = provider.getUserProfile();

		model.addObject("profile", profile);
		model.setViewName("authSuccess");

		return model;
	}

	@RequestMapping(value = "/home")
	public ModelAndView gotoSuccessPage(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("userName") String userName, @RequestParam("password") String password) throws Exception {

		LOG.debug("execute gotoApplicationHomePage() ");

		ModelAndView model = new ModelAndView();
		
			if ("admin".equals(userName) || "ADMIN".equals(userName) && "admin".equals(password)
					|| "ADMIN".equals(password)) {

				LOG.debug("get UserName and Password from loginVO " + userName + password);

				model.setViewName("success");
			} else {
				model.setViewName("home");
				model.addObject("error", "Invalid UserName and Password. Please Enter admin for both username and password.");
			}
		
		return model;
	}
	
	@RequestMapping(value = "/homePage.do")
	public ModelAndView gotoApplicationHomePage(HttpServletRequest request , HttpServletResponse response) throws Exception{
		
		ModelAndView model = new ModelAndView();
		model.setViewName("home");
		return model;
	}
	
	
	
	
	
	

}
