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

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
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
import com.sldnfarchive.service.UserService;

/**
 * @Class Name : UserController.java
 * @Description : User Controller Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2026.02.19	 HHP          최초생성
 *
 * @author HHP
 * @since 2016.02.19
 * @version 1.0
 * @see
 *
 *  Copyright (C) by MOPAS All right reserved.
 */

@Controller
@RequestMapping("/user")
public class UserController {

	/** Validator */
	@Resource(name = "beanValidator")
	protected DefaultBeanValidator beanValidator;
	
	/** txManager */
	@Resource(name = "txManager")
	protected DataSourceTransactionManager txManager;
	
	/** UserService */
	@Resource(name = "userService")
	private UserService userService;
	
	
	/**
	 * 회원관리
	 * @return "user/userList"
	 * @exception Exception
	 */
	@RequestMapping(value = "/userList.do")
	public String userList() throws Exception {
		System.out.println("============================");
		System.out.println("Success - userList.do");
		System.out.println("============================");
		
		return "user/userList";
	}
	
	/**
	 * 회원 목록
	 * @return "jsonView"
	 * @exception Exception
	 */
	@RequestMapping(value = "/selectUserList.do")
	public String selectUserList(@ModelAttribute("userVO") UserVO userVO, ModelMap model) throws Exception {
		
		List<EgovMap> userList = userService.userList(userVO);
		
		System.out.println("============================");
		System.out.println("Success - selectUserList.do");
		System.out.println("============================");
		
		model.addAttribute("userList", userList);
		
		return "jsonView";
	}
	
	/**
	 * 회원 상세정보
	 * @return "jsonView"
	 * @exception Exception
	 */
	@RequestMapping(value = "/selectUser.do")
	public String selectUser(@ModelAttribute("userVO") UserVO userVO, ModelMap model) throws Exception {
		
		EgovMap selectUser = userService.selectUser(userVO);
		
		System.out.println("============================");
		System.out.println("Success - selectUser.do");
		System.out.println("============================");
		
		model.addAttribute("selectUser", selectUser);
		
		return "jsonView";
	}
	
	/**
	 * 회원 추가
	 * @return "jsonView"
	 * @exception Exception
	 */
	@RequestMapping(value = "/insertUser.do")
	public String insertUser(@ModelAttribute("userVO") UserVO userVO, ModelMap model) throws Exception {
		DefaultTransactionDefinition txDef = new DefaultTransactionDefinition();
		txDef.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		
		// txStatus
		TransactionStatus txStatus = txManager.getTransaction(txDef);
		
		System.out.println("============================");
		System.out.println("Success - insertUser.do");
		System.out.println("============================");
		
		userService.insertUser(userVO);
		txManager.commit(txStatus);
		
		return "jsonView";
	}
	
	/**
	 * 회원 정보 수정
	 * @return "jsonView"
	 * @exception Exception
	 */
	@RequestMapping(value = "/updateUser.do")
	public String updateUser(@ModelAttribute("userVO") UserVO userVO, ModelMap model) throws Exception {
		DefaultTransactionDefinition txDef = new DefaultTransactionDefinition();
		txDef.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		
		// txStatus
		TransactionStatus txStatus = txManager.getTransaction(txDef);
		
		System.out.println("============================");
		System.out.println("Success - updateUser.do");
		System.out.println("============================");
		
		userService.updateUser(userVO);
		txManager.commit(txStatus);
		
		return "jsonView";
		
	}
	
	/**
	 * 회원 정보 삭제
	 * @return "jsonView"
	 * @exception Exception
	 */
	@RequestMapping(value = "/deleteUser.do")
	public String deleteUser(@ModelAttribute("userVO") UserVO userVO, ModelMap model) throws Exception {
		DefaultTransactionDefinition txDef = new DefaultTransactionDefinition();
		txDef.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		
		// txStatus
		TransactionStatus txStatus = txManager.getTransaction(txDef);
		
		System.out.println("============================");
		System.out.println("Success - deleteUser.do");
		System.out.println("============================");
		
		userService.deleteUser(userVO);
		txManager.commit(txStatus);
		
		return "jsonView";
		
	}

}
