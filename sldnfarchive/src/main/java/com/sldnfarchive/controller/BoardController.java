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

import com.sldnfarchive.model.BoardVO;
import com.sldnfarchive.model.CodeVO;
import com.sldnfarchive.service.BoardService;
import com.sldnfarchive.service.CodeService;

/**
 * @Class Name : BoardController.java
 * @Description : Board Controller Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2026.03.11	 HHP          최초생성
 *
 * @author HHP
 * @since 2026.03.11
 * @version 1.0
 * @see
 *
 *  Copyright (C) by MOPAS All right reserved.
 */

@Controller
@RequestMapping("/board")
public class BoardController {

	/** Validator */
	@Resource(name = "beanValidator")
	protected DefaultBeanValidator beanValidator;
	
	/** txManager */
	@Resource(name = "txManager")
	protected DataSourceTransactionManager txManager;
	
	/** BoardService */
	@Resource(name = "boardService")
	private BoardService boardService;
	
	/** CodeService */
	@Resource(name = "codeService")
	private CodeService codeService;
	
	
	/**
	 * 게시판관리
	 * @return "board/boardList"
	 * @exception Exception
	 */
	@RequestMapping(value = "/boardList.do")
	public String boardList(@ModelAttribute("codeVO") CodeVO codeVO, ModelMap model) throws Exception {
		
		codeVO.setCodeLcd("A");
		
		List<EgovMap> codeList = codeService.outCodeList(codeVO);
		
		System.out.println("============================");
		System.out.println("Success - boardList.do");
		System.out.println("============================");
		
		model.addAttribute("codeList", codeList);
		
		return "board/boardList";
	}
	
	/**
	 * 게시판 목록
	 * @return "jsonView"
	 * @exception Exception
	 */
	@RequestMapping(value = "/selectBoardList.do")
	public String selectBoardList(@ModelAttribute("boardVO") BoardVO boardVO, ModelMap model) throws Exception {
		
		List<EgovMap> boardList = boardService.boardList(boardVO);
		int totalCnt = boardService.boardListCnt(boardVO);
		
		System.out.println("============================");
		System.out.println("Success - selectBoardList.do");
		System.out.println("============================");
		
		model.addAttribute("boardList", boardList);
		model.addAttribute("totalCnt", totalCnt);
		
		return "jsonView";
	}
	
	/**
	 * 게시판 상세정보
	 * @return "jsonView"
	 * @exception Exception
	 */
	@RequestMapping(value = "/selectBoard.do")
	public String selectBoard(@ModelAttribute("boardVO") BoardVO boardVO, ModelMap model) throws Exception {
		
		EgovMap selectBoard = boardService.selectBoard(boardVO);
		
		System.out.println("============================");
		System.out.println("Success - selectBoard.do");
		System.out.println("============================");
		
		model.addAttribute("selectBoard", selectBoard);
		
		return "jsonView";
	}
	
	/**
	 * 게시판 추가
	 * @return "jsonView"
	 * @exception Exception
	 */
	@RequestMapping(value = "/insertBoard.do")
	public String insertBoard(@ModelAttribute("boardVO") BoardVO boardVO, ModelMap model) throws Exception {
		DefaultTransactionDefinition txDef = new DefaultTransactionDefinition();
		txDef.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		
		// txStatus
		TransactionStatus txStatus = txManager.getTransaction(txDef);
		
		System.out.println("============================");
		System.out.println("Success - insertBoard.do");
		System.out.println("============================");
		
		boardService.insertBoard(boardVO);
		txManager.commit(txStatus);
		
		return "jsonView";
	}
	
	/**
	 * 게시판 정보 수정
	 * @return "jsonView"
	 * @exception Exception
	 */
	@RequestMapping(value = "/updateBoard.do")
	public String updateBoard(@ModelAttribute("boardVO") BoardVO boardVO, ModelMap model) throws Exception {
		DefaultTransactionDefinition txDef = new DefaultTransactionDefinition();
		txDef.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		
		// txStatus
		TransactionStatus txStatus = txManager.getTransaction(txDef);
		
		System.out.println("============================");
		System.out.println("Success - updateBoard.do");
		System.out.println("============================");
		
		boardService.updateBoard(boardVO);
		txManager.commit(txStatus);
		
		return "jsonView";
		
	}
	
	/**
	 * 게시판 삭제
	 * @return "jsonView"
	 * @exception Exception
	 */
	@RequestMapping(value = "/deleteBoard.do")
	public String deleteBoard(@ModelAttribute("boardVO") BoardVO boardVO, ModelMap model) throws Exception {
		DefaultTransactionDefinition txDef = new DefaultTransactionDefinition();
		txDef.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		
		// txStatus
		TransactionStatus txStatus = txManager.getTransaction(txDef);
		
		System.out.println("============================");
		System.out.println("Success - deleteBoard.do");
		System.out.println("============================");
		
		boardService.deleteBoard(boardVO);
		txManager.commit(txStatus);
		
		return "jsonView";
		
	}

}
