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

import com.sldnfarchive.model.MenuVO;
import com.sldnfarchive.service.MenuService;

/**
 * @Class Name : MenuController.java
 * @Description : Menu Controller Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2026.02.10	 HHP          최초생성
 *
 * @author HHP
 * @since 2016.02.10
 * @version 1.0
 * @see
 *
 *  Copyright (C) by MOPAS All right reserved.
 */

@Controller
@RequestMapping("/menu")
public class MenuController {

	/** Validator */
	@Resource(name = "beanValidator")
	protected DefaultBeanValidator beanValidator;
	
	/** txManager */
	@Resource(name = "txManager")
	protected DataSourceTransactionManager txManager;
	
	/** MenuService */
	@Resource(name = "menuService")
	private MenuService menuService;
	
	
	/**
	 * 메뉴관리
	 * @return "menu/menuList"
	 * @exception Exception
	 */
	@RequestMapping(value = "/menuList.do")
	public String menuList() throws Exception {
		System.out.println("============================");
		System.out.println("Success - menuList.do");
		System.out.println("============================");
		
		return "menu/menuList";
	}
	
	/**
	 * 메뉴 목록
	 * @return "jsonView"
	 * @exception Exception
	 */
	@RequestMapping(value = "/selectMenuList.do")
	public String selectMenuList(@ModelAttribute("menuVO") MenuVO menuVO, ModelMap model) throws Exception {
		
		List<EgovMap> menuList = menuService.menuList(menuVO);
		
		System.out.println("============================");
		System.out.println("Success - selectMenuList.do");
		System.out.println("============================");
		
		model.addAttribute("menuList", menuList);
		
		return "jsonView";
	}
	
	/**
	 * 메뉴 상세정보
	 * @return "jsonView"
	 * @exception Exception
	 */
	@RequestMapping(value = "/selectMenu.do")
	public String selectMenu(@ModelAttribute("menuVO") MenuVO menuVO, ModelMap model) throws Exception {
		
		EgovMap selectMenu = menuService.selectMenu(menuVO);
		
		System.out.println("============================");
		System.out.println("Success - selectMenu.do");
		System.out.println("============================");
		
		model.addAttribute("selectMenu", selectMenu);
		
		return "jsonView";
	}
	
	/**
	 * 메뉴 추가
	 * @return "jsonView"
	 * @exception Exception
	 */
	@RequestMapping(value = "/insertMenu.do")
	public String insertMenu(@ModelAttribute("menuVO") MenuVO menuVO, ModelMap model) throws Exception {
		DefaultTransactionDefinition txDef = new DefaultTransactionDefinition();
		txDef.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		
		// txStatus
		TransactionStatus txStatus = txManager.getTransaction(txDef);
		
		System.out.println("============================");
		System.out.println("Success - insertMenu.do");
		System.out.println("============================");
		
		menuService.insertMenu(menuVO);
		txManager.commit(txStatus);
		
		return "jsonView";
	}
	
	/**
	 * 메뉴 정보 수정
	 * @return "jsonView"
	 * @exception Exception
	 */
	@RequestMapping(value = "/updateMenu.do")
	public String updateMenu(@ModelAttribute("menuVO") MenuVO menuVO, ModelMap model) throws Exception {
		DefaultTransactionDefinition txDef = new DefaultTransactionDefinition();
		txDef.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		
		// txStatus
		TransactionStatus txStatus = txManager.getTransaction(txDef);
		
		System.out.println("============================");
		System.out.println("Success - updateMenu.do");
		System.out.println("============================");
		
		menuService.updateMenu(menuVO);
		txManager.commit(txStatus);
		
		return "jsonView";
		
	}
	
	/**
	 * 메뉴 삭제
	 * @return "jsonView"
	 * @exception Exception
	 */
	@RequestMapping(value = "/deleteMenu.do")
	public String deleteMenu(@ModelAttribute("menuVO") MenuVO menuVO, ModelMap model) throws Exception {
		DefaultTransactionDefinition txDef = new DefaultTransactionDefinition();
		txDef.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		
		// txStatus
		TransactionStatus txStatus = txManager.getTransaction(txDef);
		
		System.out.println("============================");
		System.out.println("Success - deleteMenu.do");
		System.out.println("============================");
		
		menuService.deleteMenu(menuVO);
		txManager.commit(txStatus);
		
		return "jsonView";
		
	}

}
