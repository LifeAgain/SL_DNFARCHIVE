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

import com.sldnfarchive.model.PostVO;

/**
 * 게시판에 관한 데이터처리 매퍼 클래스
 *
 * @author HHP
 * @since 2026.04.09
 * @version 1.0
 * @see <pre>
 *  == 개정이력(Modification Information) ==
 *
 *          수정일          수정자           수정내용
 *  ----------------    ------------    ---------------------------
 *   2026.04.09        		HHP          최초 생성
 *
 * </pre>
 */
@Mapper("postMapper")
public interface PostMapper {
	
	/**
	 * 게시글 목록을 조회한다.
	 * @param PostVO
	 * @exception Exception
	 */
	List<EgovMap> postList(PostVO postVO) throws Exception;
	
	/**
	 * 게시글 개수를 조회한다.
	 * @param PostVO
	 * @exception Exception
	 */
	int postListCnt(PostVO postVO) throws Exception;

	/**
	 * 게시글 상세 조회
	 * @param PostVO
	 * @exception
	 */
	EgovMap selectPost(PostVO postVO) throws Exception;
	
	/**
	 * 게시글 추가
	 * @param PostVO
	 * @exception
	 */
	void insertPost(PostVO postVO) throws Exception;
	
	/**
	 * 게시글-파일 맵핑
	 * @param PostVO
	 * @exception
	 */
	void insertMapping(PostVO postVO) throws Exception;
	
	/**
	 * 게시글 수정
	 * @param PostVO
	 * @exception
	 */
	void updatePost(PostVO postVO) throws Exception;
	
	/**
	 * 게시글 삭제
	 * @param BoardVO
	 * @exception
	 */
	void deletePost(PostVO postVO) throws Exception;
	
	/**
	 * 댓글 목록을 조회한다.
	 * @param PostVO
	 * @exception Exception
	 */
	List<EgovMap> commentList(PostVO postVO) throws Exception;
	
	/**
	 * 댓글 개수를 조회한다.
	 * @param PostVO
	 * @exception Exception
	 */
	int commentListCnt(PostVO postVO) throws Exception;
	
	/**
	 * 댓글 추가
	 * @param PostVO
	 * @exception
	 */
	void insertComment(PostVO postVO) throws Exception;
	
	/**
	 * 댓글 수정
	 * @param PostVO
	 * @exception
	 */
	void updateComment(PostVO postVO) throws Exception;
	
	/**
	 * 댓글 삭제
	 * @param BoardVO
	 * @exception
	 */
	void deleteComment(PostVO postVO) throws Exception;

}
