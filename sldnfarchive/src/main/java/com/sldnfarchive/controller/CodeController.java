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

import com.sldnfarchive.model.CodeVO;
import com.sldnfarchive.service.CodeService;

/**
 * @Class Name : CodeController.java
 * @Description : Code Controller Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2026.02.18	 HHP          최초생성
 *
 * @author HHP
 * @since 2016.02.18
 * @version 1.0
 * @see
 *
 *  Copyright (C) by MOPAS All right reserved.
 */

@Controller
@RequestMapping("/code")
public class CodeController {

	/** Validator */
	@Resource(name = "beanValidator")
	protected DefaultBeanValidator beanValidator;
	
	/** txManager */
	@Resource(name = "txManager")
	protected DataSourceTransactionManager txManager;
	
	/** MenuService */
	@Resource(name = "codeService")
	private CodeService codeService;
	
	
	/**
	 * 메뉴관리
	 * @return "code/codeList"
	 * @exception Exception
	 */
	@RequestMapping(value = "/codeList.do")
	public String codeList() throws Exception {
		System.out.println("============================");
		System.out.println("Success - codeList.do");
		System.out.println("============================");
		
		return "code/codeList";
	}
	
	/**
	 * 메뉴 목록
	 * @return "jsonView"
	 * @exception Exception
	 */
	@RequestMapping(value = "/selectCodeList.do")
	public String selectCodeList(@ModelAttribute("codeVO") CodeVO codeVO, ModelMap model) throws Exception {
		
		List<EgovMap> codeList = codeService.codeList(codeVO);
		
		System.out.println("============================");
		System.out.println("Success - selectCodeList.do");
		System.out.println("============================");
		
		model.addAttribute("codeList", codeList);
		
		return "jsonView";
	}
	
	/**
	 * 메뉴 상세정보
	 * @return "jsonView"
	 * @exception Exception
	 */
	@RequestMapping(value = "/selectCode.do")
	public String selectCode(@ModelAttribute("codeVO") CodeVO codeVO, ModelMap model) throws Exception {
		
		EgovMap selectCode = codeService.selectCode(codeVO);
		
		System.out.println("============================");
		System.out.println("Success - selectCode.do");
		System.out.println("============================");
		
		model.addAttribute("selectCode", selectCode);
		
		return "jsonView";
	}
	
	/**
	 * 메뉴 추가
	 * @return "jsonView"
	 * @exception Exception
	 */
	@RequestMapping(value = "/insertCode.do")
	public String insertCode(@ModelAttribute("codeVO") CodeVO codeVO, ModelMap model) throws Exception {
		DefaultTransactionDefinition txDef = new DefaultTransactionDefinition();
		txDef.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		
		// txStatus
		TransactionStatus txStatus = txManager.getTransaction(txDef);
		
		System.out.println("============================");
		System.out.println("Success - insertCode.do");
		System.out.println("============================");
		
		codeService.insertCode(codeVO);
		txManager.commit(txStatus);
		
		return "jsonView";
	}
	
	/**
	 * 메뉴 정보 수정
	 * @return "jsonView"
	 * @exception Exception
	 */
	@RequestMapping(value = "/updateCode.do")
	public String updateCode(@ModelAttribute("codeVO") CodeVO codeVO, ModelMap model) throws Exception {
		DefaultTransactionDefinition txDef = new DefaultTransactionDefinition();
		txDef.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		
		// txStatus
		TransactionStatus txStatus = txManager.getTransaction(txDef);
		
		System.out.println("============================");
		System.out.println("Success - updateCode.do");
		System.out.println("============================");
		
		codeService.updateCode(codeVO);
		txManager.commit(txStatus);
		
		return "jsonView";
		
	}
	
	/**
	 * 메뉴 삭제
	 * @return "jsonView"
	 * @exception Exception
	 */
	@RequestMapping(value = "/deleteCode.do")
	public String deleteCode(@ModelAttribute("codeVO") CodeVO codeVO, ModelMap model) throws Exception {
		DefaultTransactionDefinition txDef = new DefaultTransactionDefinition();
		txDef.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		
		// txStatus
		TransactionStatus txStatus = txManager.getTransaction(txDef);
		
		System.out.println("============================");
		System.out.println("Success - deleteCode.do");
		System.out.println("============================");
		
		codeService.deleteCode(codeVO);
		txManager.commit(txStatus);
		
		return "jsonView";
		
	}

}
