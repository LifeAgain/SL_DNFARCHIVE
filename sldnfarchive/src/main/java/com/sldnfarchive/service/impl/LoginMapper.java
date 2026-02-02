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

import com.sldnfarchive.model.UserVO;

/**
 * USER에 관한 데이터처리 매퍼 클래스
 *
 * @author HHP
 * @since 2026.01.23
 * @version 1.0
 * @see <pre>
 *  == 개정이력(Modification Information) ==
 *
 *          수정일          수정자           수정내용
 *  ----------------    ------------    ---------------------------
 *   2026.01.23        		HHP          최초 생성
 *
 * </pre>
 */
@Mapper("loginMapper")
public interface LoginMapper {
	/**
	 * 로그인 정보를 조회한다.
	 * @param UserVO
	 * @return loginInfo
	 * @exception Exception
	 */
	EgovMap loginCheckAdm(UserVO userVO) throws Exception;

	/**
	 * 로그인된 계정 개수를 조회한다.
	 * @param UserVO
	 * @return 로그인한 계정과 일치하는 계정 개수
	 * @exception
	 */
	int loginCheckAdmCnt(UserVO userVO);

}
