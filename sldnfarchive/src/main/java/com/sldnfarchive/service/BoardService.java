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
package com.sldnfarchive.service;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;

import com.sldnfarchive.model.BoardVO;

/**
 * @Class Name : BoardService.java
 * @Description : BoardService Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2026.03.11		HHP           최초생성
 *
 * @author HHP
 * @since 2026.03.11
 * @version 1.0
 * @see
 *
 *  Copyright (C) by MOPAS All right reserved.
 */
public interface BoardService {

	/**
	 * 게시판 목록을 조회한다.
	 * @param BoardVO
	 * @exception Exception
	 */
	List<EgovMap> boardList(BoardVO boardVO) throws Exception;
	
	/**
	 * 게시판 목록 개수를 조회한다.
	 * @param BoardVO
	 * @exception Exception
	 */
	int boardListCnt(BoardVO boardVO) throws Exception;

	/**
	 * 게시판 상세정보 조회
	 * @param BoardVO
	 * @exception
	 */
	EgovMap selectBoard(BoardVO boardVO) throws Exception;
	
	/**
	 * 상위메뉴 조회
	 * @param BoardVO
	 * @exception
	 */
	List<EgovMap> parentMenuList() throws Exception;
	
	/**
	 * 게시판 추가
	 * @param BoardVO
	 * @exception
	 */
	void insertBoard(BoardVO boardVO) throws Exception;
	
	/**
	 * 게시판 정보 수정
	 * @param BoardVO
	 * @exception
	 */
	void updateBoard(BoardVO boardVO) throws Exception;
	
	/**
	 * 게시판 삭제
	 * @param BoardVO
	 * @exception
	 */
	void deleteBoard(BoardVO boardVO) throws Exception;

}
