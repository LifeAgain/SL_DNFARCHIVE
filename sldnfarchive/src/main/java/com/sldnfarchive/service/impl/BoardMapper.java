/*
 * Copyright 2011 MOPAS(Ministry of Public Administration and Security).
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

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;

import com.sldnfarchive.model.BoardVO;

/**
 * 게시판에 관한 데이터처리 매퍼 클래스
 *
 * @author HHP
 * @since 2026.03.11
 * @version 1.0
 * @see <pre>
 *  == 개정이력(Modification Information) ==
 *
 *          수정일          수정자           수정내용
 *  ----------------    ------------    ---------------------------
 *   2026.03.11        		HHP          최초 생성
 *
 * </pre>
 */
@Mapper("boardMapper")
public interface BoardMapper {
	
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
