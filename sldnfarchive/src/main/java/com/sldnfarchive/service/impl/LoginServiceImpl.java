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
import com.sldnfarchive.service.LoginService;

/**
 * @Class Name : LoginServiceImpl.java
 * @Description : Login Service Implement Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2026.01.23		HHP           최초생성
 *
 * @author HHP
 * @since 2026.01.23
 * @version 1.0
 * @see
 *
 *  Copyright (C) by MOPAS All right reserved.
 */

@Service("loginService")
public class LoginServiceImpl extends EgovAbstractServiceImpl implements LoginService {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoginServiceImpl.class);
	
	@Resource(name="loginMapper")
	private LoginMapper loginMapper;
	
	/**
	 * 로그인된 계정 정보를 조회한다.
	 * @param UserVO
	 * @return loginInfo
	 * @exception Exception
	 */
	@Override
	public EgovMap loginCheckAdm(UserVO userVO) throws Exception {
		return loginMapper.loginCheckAdm(userVO);
	}
	
	/**
	 * 로그인된 계정 개수를 조회한다.
	 * @param UserVO
	 * @return 로그인한 계정과 일치하는 계정 개수
	 * @exception
	 */
	@Override
	public int loginCheckAdmCnt(UserVO userVO) {
		return loginMapper.loginCheckAdmCnt(userVO);
	}

}
