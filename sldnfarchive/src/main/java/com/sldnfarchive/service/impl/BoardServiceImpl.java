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
package com.sldnfarchive.service.impl;

import java.util.List;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.egovframe.rte.fdl.idgnr.EgovIdGnrService;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sldnfarchive.model.BoardVO;
import com.sldnfarchive.service.BoardService;

/**
 * @Class Name : BoardServiceImpl.java
 * @Description : Board Service Implement Class
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

@Service("boardService")
public class BoardServiceImpl extends EgovAbstractServiceImpl implements BoardService {

	private static final Logger LOGGER = LoggerFactory.getLogger(BoardServiceImpl.class);
	
	@Resource(name="boardMapper")
	private BoardMapper boardMapper;
	
	/**
	 * 게시판목록 조회
	 * @param BoardVO
	 * @return boardMapper.boardList
	 * @exception Exception
	 */
	@Override
	public List<EgovMap> boardList(BoardVO boardVO) throws Exception {
		return boardMapper.boardList(boardVO);
	}
	
	/**
	 * 게시판목록 개수 조회
	 * @param BoardVO
	 * @return boardMapper.boardListCnt
	 * @exception Exception
	 */
	@Override
	public int boardListCnt(BoardVO boardVO) throws Exception {
		return boardMapper.boardListCnt(boardVO);
	}
	
	/**
	 * 게시판 상세정보 조회
	 * @param BoardVO
	 * @return boardMapper.selectBoard
	 * @exception
	 */
	@Override
	public EgovMap selectBoard(BoardVO boardVO) throws Exception {
		return boardMapper.selectBoard(boardVO);
	}
	
	/**
	 * 게시판 추가
	 * @param BoardVO
	 * @exception
	 */
	@Override
	public void insertBoard(BoardVO boardVO) throws Exception {
		boardMapper.insertBoard(boardVO);
	}
	
	/**
	 * 게시판 정보 수정
	 * @param BoardVO
	 * @exception
	 */
	public void updateBoard(BoardVO boardVO) throws Exception {
		boardMapper.updateBoard(boardVO);
	}
	
	/**
	 * 게시판 삭제
	 * @param BoardVO
	 * @exception
	 */
	public void deleteBoard(BoardVO boardVO) throws Exception {
		boardMapper.deleteBoard(boardVO);
	}

}
