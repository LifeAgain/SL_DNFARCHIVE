/*
 * Copyright 2008-2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.sldnfarchive.controller;

import java.util.List;

import org.egovframe.rte.fdl.property.EgovPropertyService;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springmodules.validation.commons.DefaultBeanValidator;

import com.sldnfarchive.model.UserVO;
import com.sldnfarchive.service.LoginService;

/**
 * @Class Name : LoginController.java
 * @Description : Login Controller Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2026.01.16	 HHP          최초생성
 *
 * @author HHP
 * @since 2026.01.16
 * @version 1.0
 * @see
 *
 *  Copyright (C) by MOPAS All right reserved.
 */

@Controller
@RequestMapping("/login")
public class LoginController {

	/** LoginService */
	@Resource(name = "loginService")
	private LoginService loginService;

	/** Validator */
	@Resource(name = "beanValidator")
	protected DefaultBeanValidator beanValidator;

	/**
	 * 로그인 초기화면
	 * @return "login/login"
	 * @exception Exception
	 */
	@RequestMapping(value = "/login.do")
	public String login() throws Exception {
		System.out.println("============================");
		System.out.println("Success - login.do");
		System.out.println("============================");
		
		return "login/login";
	}
	
	/**
	 * 관리자 로그인
	 * @return "login/login"
	 * @exception Exception
	 */
	@RequestMapping(value = "/loginAdm.do")
	public String loginAdm() throws Exception {
		System.out.println("============================");
		System.out.println("Success - loginAdm.do");
		System.out.println("============================");
		
		return "login/loginAdm";
	}
	
	/**
	 * 관리자 로그인
	 * @param UserVO - 로그인 요청 정보가 담긴 VO
	 * @param model
	 * @return "jsonView"
	 * @exception Exception
	 */
	@RequestMapping(value = "/loginCheckAdm.do")
	public String loginCheckAdm(@ModelAttribute("userVO") UserVO userVO, HttpServletRequest req, ModelMap model) throws Exception {
		EgovMap loginInfo = loginService.loginCheckAdm(userVO);
		int loginCnt = loginService.loginCheckAdmCnt(userVO);
		
		System.out.println("============================");
		System.out.println("Success - loginCheckAdm.do");
		System.out.println("============================");
		
		if(loginInfo != null && loginInfo.get("userIdx") != null) {
			HttpSession oldSession = req.getSession(false);
			if(oldSession != null) oldSession.invalidate();
			
			HttpSession newSession = req.getSession();
			int idx = Integer.parseInt(loginInfo.get("userIdx").toString());
			String userNm = loginInfo.get("userNm").toString();
			
			newSession.setAttribute("userIdx", idx);
			newSession.setAttribute("userNm", userNm);
		} else {
			HttpSession session = req.getSession(false);
			
			if(session != null) {
				session.removeAttribute("userIdx");
				session.removeAttribute("userNm");
			}
		}
		
		model.addAttribute("loginInfo", loginInfo);
		model.addAttribute("loginCnt", loginCnt);
		
		return "jsonView";
	}
	
	/**
	 * 로그아웃
	 * @return "redirect:/"
	 * @exception Exception
	 */
	@RequestMapping(value = "/logout.do")
	public String logout(HttpServletRequest req, HttpServletResponse res) throws Exception {
		HttpSession session = req.getSession(false);
		
		System.out.println("============================");
		System.out.println("Success - logout.do");
		System.out.println("============================");
		
		if(session != null) session.invalidate();
		
		return "redirect:/";
	}

}
