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
import javax.servlet.http.HttpServletRequest;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springmodules.validation.commons.DefaultBeanValidator;

import com.sldnfarchive.model.VideoVO;
import com.sldnfarchive.service.VideoService;
/**
 * @Class Name : VideoController.java
 * @Description : Video Controller Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2026.06.08	 HHP          최초생성
 *
 * @author HHP
 * @since 2026.06.08
 * @version 1.0
 * @see
 *
 *  Copyright (C) by MOPAS All right reserved.
 */

@Controller
@RequestMapping("/video")
public class VideoController {

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
	@Resource(name = "videoService")
	private VideoService videoService;
	
	
	/**
	 * 동영상 목록
	 * @return "video/videoList"
	 * @exception Exception
	 */
	@RequestMapping(value = "/videoList.do")
	public String boardList(@ModelAttribute("videoVO") VideoVO videoVO, ModelMap model) throws Exception {
		
		List<EgovMap> videoList = videoService.videoList(videoVO);
		int totalCnt = videoService.videoListCnt(videoVO);
		
		/** EgovPropertyService.sample */
		videoVO.setPageUnit(propertiesService.getInt("pageUnit"));
		videoVO.setPageSize(propertiesService.getInt("pageSize"));

		/** paging setting */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setTotalRecordCount(totalCnt);
		paginationInfo.setCurrentPageNo(videoVO.getCurPage());
		paginationInfo.setRecordCountPerPage(videoVO.getPageUnit());
		paginationInfo.setPageSize(videoVO.getPageSize());
		
		System.out.println("============================");
		System.out.println("Success - videoList.do");
		System.out.println("============================");
		
		model.addAttribute("videoList", videoList);
		model.addAttribute("totalCnt", totalCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		
		return "video/videoList";
	}
	
	/**
	 * 글쓰기 폼
	 * @return "video/videoFrm"
	 * @exception Exception
	 */
	@RequestMapping(value = "/videoFrm.do")
	public String videoFrm(@ModelAttribute("videoVO") VideoVO videoVO, HttpServletRequest req, ModelMap model) throws Exception {
		int videoNo = Integer.valueOf(req.getParameter("videoNo"));
		String flag = "";
		
		if(videoNo > 0) {
			flag = "U";
			EgovMap selectVideo = videoService.selectVideo(videoVO);
			
			model.addAttribute("selectVideo", selectVideo);
		} else {
			flag = "I";
		}
		
		System.out.println("============================");
		System.out.println("Success - videoFrm.do");
		System.out.println("============================");
		
		model.addAttribute("flag", flag);
		
		return "video/videoFrm";
	}
	
	/**
	 * 동영상 추가
	 * @return "jsonView"
	 * @exception Exception
	 */
	@RequestMapping(value = "/insertVideo.do")
	public String insertVideo(@ModelAttribute("videoVO") VideoVO videoVO, ModelMap model) throws Exception {
		DefaultTransactionDefinition txDef = new DefaultTransactionDefinition();
		txDef.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		
		// txStatus
		TransactionStatus txStatus = txManager.getTransaction(txDef);
		
		System.out.println("============================");
		System.out.println("Success - insertVideo.do");
		System.out.println("============================");
		
		videoService.insertVideo(videoVO);
		txManager.commit(txStatus);
		
		return "jsonView";
	}
	
	/**
	 * 동영상 정보 수정
	 * @return "jsonView"
	 * @exception Exception
	 */
	@RequestMapping(value = "/updateVideo.do")
	public String updateVideo(@ModelAttribute("videoVO") VideoVO videoVO, ModelMap model) throws Exception {
		DefaultTransactionDefinition txDef = new DefaultTransactionDefinition();
		txDef.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		
		// txStatus
		TransactionStatus txStatus = txManager.getTransaction(txDef);
		
		System.out.println("============================");
		System.out.println("Success - updateBoard.do");
		System.out.println("============================");
		
		videoService.updateVideo(videoVO);
		txManager.commit(txStatus);
		
		return "jsonView";
		
	}
	
	/**
	 * 동영상 삭제
	 * @return "jsonView"
	 * @exception Exception
	 */
	@RequestMapping(value = "/deleteVideo.do")
	public String deleteVideo(@ModelAttribute("boardVO") VideoVO videoVO, ModelMap model) throws Exception {
		DefaultTransactionDefinition txDef = new DefaultTransactionDefinition();
		txDef.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		
		// txStatus
		TransactionStatus txStatus = txManager.getTransaction(txDef);
		
		System.out.println("============================");
		System.out.println("Success - deleteBoard.do");
		System.out.println("============================");
		
		videoService.deleteVideo(videoVO);
		txManager.commit(txStatus);
		
		return "jsonView";
		
	}

}
