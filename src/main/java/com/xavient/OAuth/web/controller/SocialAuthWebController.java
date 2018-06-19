package com.xavient.OAuth.web.controller;

import java.util.HashMap;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.brickred.socialauth.AuthProvider;
import org.brickred.socialauth.SocialAuthManager;
import org.brickred.socialauth.spring.bean.SocialAuthTemplate;
import org.brickred.socialauth.util.SocialAuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/*
 * @Author Imran
 * 
 */

@Controller
@RequestMapping("/socialauth")
public class SocialAuthWebController {

	private String baseCallbackUrl;
	private String successPageURL;
	private String accessDeniedPageURL;

	@Autowired
	private SocialAuthTemplate socialAuthTemplate;

	@Autowired
	private SocialAuthManager socialAuthManager;

	private final Log LOG = LogFactory.getLog(getClass());

	/**
	 * Constructs a SocialAuthWebController.
	 * 
	 * @param applicationUrl
	 *            the base URL for this application (with context e.g
	 *            http://localhost/OAuth, used to construct the callback URL
	 *            passed to the providers
	 * @param successPageURL
	 *            the URL of success page or controller, where you want to
	 *            access sign in user details like profile, contacts etc.
	 * @param accessDeniedPageURL
	 *            the URL of page where you want to redirect when user denied
	 *            the permission.
	 */
	@Inject
	public SocialAuthWebController(final String applicationUrl, final String successPageURL,
			final String accessDeniedPageURL) {
		this.baseCallbackUrl = applicationUrl;
		this.successPageURL = successPageURL;
		this.accessDeniedPageURL = accessDeniedPageURL;
	}

	/**
	 * Initiates the connection with required provider.It redirects the browser
	 * to an appropriate URL which will be used for authentication with the
	 * requested provider.
	 */
	@RequestMapping(params = "id")
	private String connect(@RequestParam("id") final String providerId, final HttpServletRequest request)
			throws Exception {
		LOG.debug("Getting Authentication URL for :" + providerId);
		String callbackURL = null;
		String url = null;
		try{
		 callbackURL = baseCallbackUrl + request.getServletPath();
		 url = socialAuthManager.getAuthenticationUrl(providerId, callbackURL);
		}catch(Exception e){
			e.getMessage();
			e.getStackTrace();
		}
		if (callbackURL.equals(url)) {
			url = successPageURL;
			socialAuthManager.connect(new HashMap<String, String>());
		}
		socialAuthTemplate.setSocialAuthManager(socialAuthManager);
		return "redirect:" + url;
	}

	@RequestMapping(params = "oauth_token")
	private String oauthCallback(final HttpServletRequest request) {
		callback(request);
		return "redirect:/" + successPageURL;
	}

	@RequestMapping(params = "code")
	private String oauth2Callback(final HttpServletRequest request) {
		callback(request);
		return "redirect:/" + successPageURL;
	}

	private void callback(final HttpServletRequest request) {
		SocialAuthManager m = socialAuthTemplate.getSocialAuthManager();
		if (m != null) {
			try {
				AuthProvider provider = m.connect(SocialAuthUtil.getRequestParametersMap(request));
				LOG.debug("Connected Provider : " + provider.getProviderId());
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			LOG.debug("Unable to connect provider because SocialAuthManager object is null.");
		}
	}

	@RequestMapping(params = "error_reason")
	private String fbCancel(@RequestParam("error_reason") final String error) {
		LOG.debug("Facebook send an error : " + error);
		if ("user_denied".equals(error)) {
			return "redirect:/" + accessDeniedPageURL;
		}
		return "redirect:/";
	}

	@RequestMapping(params = "openid.mode=cancel")
	private String googleCancel(@RequestParam("openid.mode") final String error) {
		LOG.debug("Google send an error : " + error);
		if ("cancel".equals(error)) {
			return "redirect:/" + accessDeniedPageURL;
		}
		return "redirect:/";
	}

}
