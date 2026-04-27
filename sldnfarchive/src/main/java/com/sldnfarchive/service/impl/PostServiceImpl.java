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

import com.sldnfarchive.model.PostVO;
import com.sldnfarchive.service.PostService;

/**
 * @Class Name : PostServiceImpl.java
 * @Description : Post Service Implement Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2026.04.09		HHP           최초생성
 *
 * @author HHP
 * @since 2026.04.09
 * @version 1.0
 * @see
 *
 *  Copyright (C) by MOPAS All right reserved.
 */

@Service("postService")
public class PostServiceImpl extends EgovAbstractServiceImpl implements PostService {

	private static final Logger LOGGER = LoggerFactory.getLogger(PostServiceImpl.class);
	
	@Resource(name="postMapper")
	private PostMapper postMapper;
	
	/**
	 * 게시글 조회
	 * @param PostVO
	 * @return postMapper.postList
	 * @exception Exception
	 */
	@Override
	public List<EgovMap> postList(PostVO postVO) throws Exception {
		return postMapper.postList(postVO);
	}
	
	/**
	 * 게시글 개수 조회
	 * @param PostVO
	 * @return postMapper.postListCnt
	 * @exception Exception
	 */
	@Override
	public int postListCnt(PostVO postVO) throws Exception {
		return postMapper.postListCnt(postVO);
	}
	
	/**
	 * 게시글 상세 조회
	 * @param PostVO
	 * @return postMapper.selectPost
	 * @exception
	 */
	@Override
	public EgovMap selectPost(PostVO postVO) throws Exception {
		return postMapper.selectPost(postVO);
	}
	
	/**
	 * 게시글 추가
	 * @param PostVO
	 * @exception
	 */
	@Override
	public void insertPost(PostVO postVO) throws Exception {
		postMapper.insertPost(postVO);
	}
	
	/**
	 * 게시글-파일 맵핑 추가
	 * @param PostVO
	 * @exception
	 */
	@Override
	public void insertMapping(PostVO postVO) throws Exception {
		postMapper.insertMapping(postVO);
	}
	
	/**
	 * 게시글 수정
	 * @param PostVO
	 * @exception
	 */
	public void updatePost(PostVO postVO) throws Exception {
		postMapper.updatePost(postVO);
	}
	
	/**
	 * 게시글 삭제
	 * @param PostVO
	 * @exception
	 */
	public void deletePost(PostVO postVO) throws Exception {
		postMapper.deletePost(postVO);
	}
	
	/**
	 * 댓글 조회
	 * @param PostVO
	 * @return postMapper.commentList
	 * @exception Exception
	 */
	@Override
	public List<EgovMap> commentList(PostVO postVO) throws Exception {
		return postMapper.commentList(postVO);
	}
	
	/**
	 * 댓글 개수 조회
	 * @param PostVO
	 * @return postMapper.commentListCnt
	 * @exception Exception
	 */
	@Override
	public int commentListCnt(PostVO postVO) throws Exception {
		return postMapper.commentListCnt(postVO);
	}
	
	/**
	 * 댓글 추가
	 * @param PostVO
	 * @exception
	 */
	@Override
	public void insertComment(PostVO postVO) throws Exception {
		postMapper.insertComment(postVO);
	}
	
	/**
	 * 댓글 수정
	 * @param PostVO
	 * @exception
	 */
	public void updateComment(PostVO postVO) throws Exception {
		postMapper.updateComment(postVO);
	}
	
	/**
	 * 댓글 삭제
	 * @param PostVO
	 * @exception
	 */
	public void deleteComment(PostVO postVO) throws Exception {
		postMapper.deleteComment(postVO);
	}

}
