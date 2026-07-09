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

import com.sldnfarchive.model.VideoVO;
import com.sldnfarchive.service.VideoService;

/**
 * @Class Name : VideoServiceImpl.java
 * @Description : Video Service Implement Class
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

@Service("videoService")
public class VideoServiceImpl extends EgovAbstractServiceImpl implements VideoService {

	private static final Logger LOGGER = LoggerFactory.getLogger(VideoServiceImpl.class);
	
	@Resource(name="videoMapper")
	private VideoMapper videoMapper;
	
	/**
	 * 동영상목록 조회
	 * @param VideoVO
	 * @return videoMapper.videoList
	 * @exception Exception
	 */
	@Override
	public List<EgovMap> videoList(VideoVO videoVO) throws Exception {
		return videoMapper.videoList(videoVO);
	}
	
	/**
	 * 동영상목록 개수 조회
	 * @param VideoVO
	 * @return videoMapper.videoListCnt
	 * @exception Exception
	 */
	@Override
	public int videoListCnt(VideoVO videoVO) throws Exception {
		return videoMapper.videoListCnt(videoVO);
	}
	
	/**
	 * 최신 동영상 조회
	 * @param VideoVO
	 * @return videoMapper.curVideoList
	 * @exception Exception
	 */
	@Override
	public List<EgovMap> curVideoList() throws Exception {
		return videoMapper.curVideoList();
	}
	
	/**
	 * 동영상 정보 조회
	 * @param VideoVO
	 * @return videoMapper.selectVideo
	 * @exception Exception
	 */
	@Override
	public EgovMap selectVideo(VideoVO videoVO) throws Exception {
		return videoMapper.selectVideo(videoVO);
	}
	
	/**
	 * 동영상 추가
	 * @param VideoVO
	 * @exception
	 */
	@Override
	public void insertVideo(VideoVO videoVO) throws Exception {
		videoMapper.insertVideo(videoVO);
	}
	
	/**
	 * 동영상 정보 수정
	 * @param VideoVO
	 * @exception
	 */
	@Override
	public void updateVideo(VideoVO videoVO) throws Exception {
		videoMapper.updateVideo(videoVO);
	}
	
	/**
	 * 동영상 삭제
	 * @param VideoVO
	 * @exception
	 */
	@Override
	public void deleteVideo(VideoVO videoVO) throws Exception {
		videoMapper.deleteVideo(videoVO);
	}

}
