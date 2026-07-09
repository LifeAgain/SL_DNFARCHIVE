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

import com.sldnfarchive.model.VideoVO;

/**
 * @Class Name : VideoService.java
 * @Description : VideoService Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2026.06.08		HHP           최초생성
 *
 * @author HHP
 * @since 2026.06.08
 * @version 1.0
 * @see
 *
 *  Copyright (C) by MOPAS All right reserved.
 */
public interface VideoService {

	/**
	 * 동영상 목록을 조회한다.
	 * @param VideoVO
	 * @exception Exception
	 */
	List<EgovMap> videoList(VideoVO videoVO) throws Exception;
	
	/**
	 * 동영상 목록 개수를 조회한다.
	 * @param VideoVO
	 * @exception Exception
	 */
	int videoListCnt(VideoVO videoVO) throws Exception;
	
	/**
	 * 최신 동영상 목록을 조회한다.
	 * @param VideoVO
	 * @exception Exception
	 */
	List<EgovMap> curVideoList() throws Exception;
	
	/**
	 * 동영상 정보를 조회한다.
	 * @param VideoVO
	 * @exception Exception
	 */
	EgovMap selectVideo(VideoVO videoVO) throws Exception;
	
	/**
	 * 동영상 추가
	 * @param VideoVO
	 * @exception
	 */
	void insertVideo(VideoVO videoVO) throws Exception;
	
	/**
	 * 동영상 정보 수정
	 * @param VideoVO
	 * @exception
	 */
	void updateVideo(VideoVO videoVO) throws Exception;
	
	/**
	 * 동영상 삭제
	 * @param VideoVO
	 * @exception
	 */
	void deleteVideo(VideoVO videoVO) throws Exception;

}
