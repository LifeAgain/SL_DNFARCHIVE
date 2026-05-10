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

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.egovframe.rte.fdl.property.EgovPropertyService;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.multipart.MultipartFile;
import org.springmodules.validation.commons.DefaultBeanValidator;

import com.sldnfarchive.model.BoardVO;
import com.sldnfarchive.model.PostVO;
import com.sldnfarchive.model.CodeVO;
import com.sldnfarchive.service.BoardService;
import com.sldnfarchive.service.PostService;
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
	
	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;
	
	/** txManager */
	@Resource(name = "txManager")
	protected DataSourceTransactionManager txManager;
	
	/** BoardService */
	@Resource(name = "boardService")
	private BoardService boardService;
	
	/** PostService */
	@Resource(name = "postService")
	private PostService postService;
	
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
		List<EgovMap> parentMenuList = boardService.parentMenuList();
		
		System.out.println("============================");
		System.out.println("Success - boardList.do");
		System.out.println("============================");
		
		model.addAttribute("codeList", codeList);
		model.addAttribute("parentMenuList", parentMenuList);
		
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
	
	/**
	 * 게시판 조회
	 * @return "board/postList"
	 * @exception Exception
	 */
	@RequestMapping(value = "/postList.do")
	public String postList(@ModelAttribute("postVO") PostVO postVO, HttpServletRequest req, ModelMap model) throws Exception {
		int boardNo = Integer.valueOf(req.getParameter("boardNo"));
		
		postVO.setBoardNo(boardNo);
		
		EgovMap postInfo = postService.postInfo(postVO);
		List<EgovMap> postList = postService.postList(postVO);
		int totalCnt = postService.postListCnt(postVO);
		
		/** EgovPropertyService.sample */
		postVO.setPageUnit(propertiesService.getInt("pageUnit"));
		postVO.setPageSize(propertiesService.getInt("pageSize"));

		/** paging setting */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setTotalRecordCount(totalCnt);
		paginationInfo.setCurrentPageNo(postVO.getCurPage());
		paginationInfo.setRecordCountPerPage(postVO.getPageUnit());
		paginationInfo.setPageSize(postVO.getPageSize());
		
		System.out.println("============================");
		System.out.println("Success - postList.do");
		System.out.println("============================");
		
		model.addAttribute("postInfo", postInfo);
		model.addAttribute("postList", postList);
		model.addAttribute("totalCnt", totalCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		
		return "board/postList";
	}
	
	/**
	 * 글쓰기 폼
	 * @return "board/postFrm"
	 * @exception Exception
	 */
	@RequestMapping(value = "/postFrm.do")
	public String postFrm(@ModelAttribute("postVO") PostVO postVO, HttpServletRequest req, ModelMap model) throws Exception {
		int postNo = Integer.valueOf(req.getParameter("postNo"));
		String flag = "";
		EgovMap postInfo = postService.postInfo(postVO);
		
		if(postNo > 0) {
			flag = "U";
			EgovMap selectPost = postService.selectPost(postVO);
			List<EgovMap> fileList = postService.fileList(postVO);
			
			model.addAttribute("selectPost", selectPost);
			model.addAttribute("fileList", fileList);
		} else {
			flag = "I";
		}
		
		System.out.println("============================");
		System.out.println("Success - postFrm.do");
		System.out.println("============================");
		
		model.addAttribute("postInfo", postInfo);
		model.addAttribute("flag", flag);
		
		return "board/postFrm";
	}
	
	/**
	 * 게시글 상세 조회
	 * @return "board/postDetail"
	 * @exception Exception
	 */
	@RequestMapping(value = "/selectPost.do")
	public String selectPost(@ModelAttribute("postVO") PostVO postVO, ModelMap model) throws Exception {
		EgovMap postInfo = postService.postInfo(postVO);
		EgovMap selectPost = postService.selectPost(postVO);
		List<EgovMap> fileList = postService.fileList(postVO);
		List<EgovMap> commentList = postService.commentList(postVO);
		int fileListCnt = postService.fileListCnt(postVO);
		int commentCnt = postService.commentListCnt(postVO);
		
		System.out.println("============================");
		System.out.println("Success - selectBoard.do");
		System.out.println("============================");
		
		model.addAttribute("postInfo", postInfo);
		model.addAttribute("selectPost", selectPost);
		model.addAttribute("fileList", fileList);
		model.addAttribute("fileListCnt", fileListCnt);
		model.addAttribute("commentList", commentList);
		model.addAttribute("commentCnt", commentCnt);
		
		return "board/postDetail";
	}
	
	/**
	 * 게시글 추가
	 * @return "jsonView"
	 * @exception Exception
	 */
	@RequestMapping(value = "/insertPost.do")
	public String insertPost(@ModelAttribute("postVO") PostVO postVO, @RequestParam("uploadFile1") MultipartFile uploadFile1, @RequestParam("uploadFile2") MultipartFile uploadFile2, ModelMap model) throws Exception {
		DefaultTransactionDefinition txDef = new DefaultTransactionDefinition();
		txDef.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		
		// txStatus
		TransactionStatus txStatus = txManager.getTransaction(txDef);
		
		String uploadPath = "";
		
		System.out.println("============================");
		System.out.println("Success - insertPost.do");
		System.out.println("============================");
		
		postVO.setRegNo(1);
		postVO.setUpNo(1);
		
		for(int i = 0; i < 2; i++) {
			MultipartFile uploadFile = null;
			
			if(i == 0) uploadFile = uploadFile1;
			else if(i == 1) uploadFile = uploadFile2;
			
			if(!uploadFile.isEmpty()) {
				String orgFileNm = uploadFile.getOriginalFilename();
				orgFileNm = orgFileNm.substring(orgFileNm.lastIndexOf("\\") + 1);
				
				String[] fileNmArr = orgFileNm.split("\\.");
				String fileNm = fileNmArr[0];
				String ext = fileNmArr[1];
				
				if(!(ext.equals("jpg") && ext.equals("gif") && ext.equals("png") && ext.equals("jpeg") && ext.equals("bmp") && ext.equals("tif"))) {
					uploadPath = "C:/Users/Samsung5/git/SL_DNFARCHIVE/sldnfarchive/src/main/webapp/images/upload";
				} else {
					uploadPath = "C:/Users/Samsung5/git/SL_DNFARCHIVE/sldnfarchive/src/main/webapp/upload";
				}
				
				Date now = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
				String nowTime = sdf.format(now);
				String uploadFileNm = fileNm + nowTime + "." + ext;
				
				File saveFolder = new File(uploadPath);
				File saveFile = new File(uploadPath, uploadFileNm);
				
				try {
					if(!saveFolder.exists()) {
						if(saveFolder.mkdirs()) System.out.println(saveFolder + " 폴더가 성공적으로 생성되었습니다.");
						else System.out.println("폴더 생성에 실패했습니다.");
					}
					
					uploadFile.transferTo(saveFile);
					
					postVO.setFileNm(orgFileNm);
					postVO.setUploadFileNm(uploadFileNm);
					postService.insertFile(postVO);
					postService.insertMapping(postVO);
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		postService.insertPost(postVO);
		txManager.commit(txStatus);
		
		return "jsonView";
	}
	
	/**
	 * 게시글 수정
	 * @return "jsonView"
	 * @exception Exception
	 */
	@RequestMapping(value = "/updatePost.do")
	public String updatePost(@ModelAttribute("postVO") PostVO postVO, @RequestParam("uploadFile1") MultipartFile uploadFile1, @RequestParam("uploadFile2") MultipartFile uploadFile2, HttpServletRequest req, ModelMap model) throws Exception {
		DefaultTransactionDefinition txDef = new DefaultTransactionDefinition();
		txDef.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		
		// txStatus
		TransactionStatus txStatus = txManager.getTransaction(txDef);
		
		String uploadPath = "";
		
		System.out.println("============================");
		System.out.println("Success - updatePost.do");
		System.out.println("============================");
		
		postVO.setRegNo(1);
		postVO.setUpNo(1);
		
		for(int i = 0; i < 2; i++) {
			MultipartFile uploadFile = null;
			int fileNo = 0;
			
			if(i == 0) {
				uploadFile = uploadFile1;
				fileNo = Integer.valueOf(req.getParameter("fileNo1"));
			} else if(i == 1) {
				uploadFile = uploadFile2;
				fileNo = Integer.valueOf(req.getParameter("fileNo2"));
			}
			
			if(!uploadFile.isEmpty()) {
				String orgFileNm = uploadFile.getOriginalFilename();
				orgFileNm = orgFileNm.substring(orgFileNm.lastIndexOf("\\") + 1);
				
				String[] fileNmArr = orgFileNm.split("\\.");
				String fileNm = fileNmArr[0];
				String ext = fileNmArr[1];
				
				if(!(ext.equals("jpg") && ext.equals("gif") && ext.equals("png") && ext.equals("jpeg") && ext.equals("bmp") && ext.equals("tif"))) {
					uploadPath = "C:/Users/Samsung5/git/SL_DNFARCHIVE/sldnfarchive/src/main/webapp/images/upload";
				} else {
					uploadPath = "C:/Users/Samsung5/git/SL_DNFARCHIVE/sldnfarchive/src/main/webapp/upload";
				}
				
				Date now = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
				String nowTime = sdf.format(now);
				String uploadFileNm = fileNm + nowTime + "." + ext;
				
				File saveFolder = new File(uploadPath);
				File saveFile = new File(uploadPath, uploadFileNm);
				
				try {
					if(!saveFolder.exists()) {
						if(saveFolder.mkdirs()) System.out.println(saveFolder + " 폴더가 성공적으로 생성되었습니다.");
						else System.out.println("폴더 생성에 실패했습니다.");
					}
					
					uploadFile.transferTo(saveFile);
					
					postVO.setFileNm(orgFileNm);
					postVO.setUploadFileNm(uploadFileNm);
					postService.insertFile(postVO);
					postVO.setFileNo(fileNo);
					postService.updateMapping(postVO);
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		postService.updatePost(postVO);
		txManager.commit(txStatus);
		
		return "jsonView";
		
	}
	
	/**
	 * 조회수 +1
	 * @return "jsonView"
	 * @exception Exception
	 */
	@RequestMapping(value = "/updateViewCnt.do")
	public String updateViewCnt(@ModelAttribute("postVO") PostVO postVO, ModelMap model) throws Exception {
		DefaultTransactionDefinition txDef = new DefaultTransactionDefinition();
		txDef.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		
		// txStatus
		TransactionStatus txStatus = txManager.getTransaction(txDef);
		
		System.out.println("============================");
		System.out.println("Success - updateViewCnt.do");
		System.out.println("============================");
		
		postVO.setUpNo(1);
		
		postService.updateViewCnt(postVO);
		txManager.commit(txStatus);
		
		return "jsonView";
		
	}
	
	/**
	 * 게시글 삭제
	 * @return "jsonView"
	 * @exception Exception
	 */
	@RequestMapping(value = "/deletePost.do")
	public String deletePost(@ModelAttribute("postVO") PostVO postVO, ModelMap model) throws Exception {
		int totalCnt = postService.commentListCnt(postVO);
		
		DefaultTransactionDefinition txDef = new DefaultTransactionDefinition();
		txDef.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		
		// txStatus
		TransactionStatus txStatus = txManager.getTransaction(txDef);
		
		System.out.println("============================");
		System.out.println("Success - deletePost.do");
		System.out.println("============================");
		
		if(totalCnt > 0) postService.deleteComment(postVO);
		
		postService.deletePost(postVO);
		txManager.commit(txStatus);
		
		return "jsonView";
	}
	
	/**
	 * 댓글 조회(리스트)
	 * @return "jsonView"
	 * @exception Exception
	 */
	@RequestMapping(value = "/commentList.do")
	public String commentList(@ModelAttribute("postVO") PostVO postVO, ModelMap model) throws Exception {
		
		List<EgovMap> commentList = postService.commentList(postVO);
		int totalCnt = postService.commentListCnt(postVO);
		
		System.out.println("============================");
		System.out.println("Success - commentList.do");
		System.out.println("============================");
		
		model.addAttribute("commentList", commentList);
		model.addAttribute("totalCnt", totalCnt);
		
		return "jsonView";
	}
	
	/**
	 * 게시글 추가
	 * @return "jsonView"
	 * @exception Exception
	 */
	@RequestMapping(value = "/insertComment.do")
	public String insertComment(@ModelAttribute("postVO") PostVO postVO, ModelMap model) throws Exception {
		DefaultTransactionDefinition txDef = new DefaultTransactionDefinition();
		txDef.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		
		// txStatus
		TransactionStatus txStatus = txManager.getTransaction(txDef);
		
		System.out.println("============================");
		System.out.println("Success - insertComment.do");
		System.out.println("============================");
		
		postVO.setRegNo(1);
		postVO.setUpNo(1);
		
		postService.insertComment(postVO);
		txManager.commit(txStatus);
		
		return "jsonView";
	}
	
	/**
	 * 게시판 정보 수정
	 * @return "jsonView"
	 * @exception Exception
	 */
	@RequestMapping(value = "/updateComment.do")
	public String updateComment(@ModelAttribute("postVO") PostVO postVO, ModelMap model) throws Exception {
		DefaultTransactionDefinition txDef = new DefaultTransactionDefinition();
		txDef.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		
		// txStatus
		TransactionStatus txStatus = txManager.getTransaction(txDef);
		
		System.out.println("============================");
		System.out.println("Success - updateComment.do");
		System.out.println("============================");
		
		postVO.setUpNo(1);
		
		postService.updateComment(postVO);
		txManager.commit(txStatus);
		
		return "jsonView";
		
	}
	
	/**
	 * 게시판 삭제
	 * @return "jsonView"
	 * @exception Exception
	 */
	@RequestMapping(value = "/deleteComment.do")
	public String deleteComment(@ModelAttribute("postVO") PostVO postVO, ModelMap model) throws Exception {
		DefaultTransactionDefinition txDef = new DefaultTransactionDefinition();
		txDef.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		
		// txStatus
		TransactionStatus txStatus = txManager.getTransaction(txDef);
		
		System.out.println("============================");
		System.out.println("Success - deleteComment.do");
		System.out.println("============================");
		
		postService.deleteComment(postVO);
		txManager.commit(txStatus);
		
		return "jsonView";
	}

}
