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

import org.egovframe.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : OAuthService.java
 * @Description : OAuthService Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2026.07.16		HHP           최초생성
 *
 * @author HHP
 * @since 2026.07.16
 * @version 1.0
 * @see
 *
 *  Copyright (C) by MOPAS All right reserved.
 */
public interface OAuthService {
	
	/**
	 * 인가 토큰 취득
	 * @param code
	 * @exception Exception
	 */
	String getAccessToken(String code) throws Exception;
	
	/**
	 * 유저 정보 취득
	 * @param code
	 * @exception Exception
	 */
	EgovMap getUserInfo(String accessToken) throws Exception;
	
}
