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

import com.sldnfarchive.model.UserVO;
import com.sldnfarchive.service.UserService;

/**
 * @Class Name : UserServiceImpl.java
 * @Description : User Service Implement Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2026.02.19		HHP           최초생성
 *
 * @author HHP
 * @since 2026.02.19
 * @version 1.0
 * @see
 *
 *  Copyright (C) by MOPAS All right reserved.
 */

@Service("userService")
public class UserServiceImpl extends EgovAbstractServiceImpl implements UserService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Resource(name="userMapper")
	private UserMapper userMapper;
	
	/**
	 * 회원목록 조회
	 * @param UserVO
	 * @return userMapper.userList
	 * @exception Exception
	 */
	@Override
	public List<EgovMap> userList(UserVO userVO) throws Exception {
		return userMapper.userList(userVO);
	}
	
	/**
	 * 회원 상세정보 조회
	 * @param UserVO
	 * @return userMapper.selectUser
	 * @exception
	 */
	@Override
	public EgovMap selectUser(UserVO userVO) throws Exception {
		return userMapper.selectUser(userVO);
	}
	
	/**
	 * 회원 추가
	 * @param UserVO
	 * @exception
	 */
	@Override
	public void insertUser(UserVO userVO) throws Exception {
		userMapper.insertUser(userVO);
	}
	
	/**
	 * 회원 정보 수정
	 * @param UserVO
	 * @exception
	 */
	public void updateUser(UserVO userVO) throws Exception {
		userMapper.updateUser(userVO);
	}
	
	/**
	 * 회원 정보 삭제
	 * @param UserVO
	 * @exception
	 */
	public void deleteUser(UserVO userVO) throws Exception {
		userMapper.deleteUser(userVO);
	}

}
