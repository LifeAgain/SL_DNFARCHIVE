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
 * 회원에 관한 데이터처리 매퍼 클래스
 *
 * @author HHP
 * @since 2026.02.19
 * @version 1.0
 * @see <pre>
 *  == 개정이력(Modification Information) ==
 *
 *          수정일          수정자           수정내용
 *  ----------------    ------------    ---------------------------
 *   2026.02.19        		HHP          최초 생성
 *
 * </pre>
 */
@Mapper("userMapper")
public interface UserMapper {
	/**
	 * 회원 목록을 조회한다.
	 * @param UserVO
	 * @exception Exception
	 */
	List<EgovMap> userList(UserVO userVO) throws Exception;

	/**
	 * 회원 상세정보 조회
	 * @param UserVO
	 * @exception
	 */
	EgovMap selectUser(UserVO userVO) throws Exception;
	
	/**
	 * 회원 추가
	 * @param UserVO
	 * @exception
	 */
	void insertUser(UserVO userVO) throws Exception;
	
	/**
	 * 회원 정보 수정
	 * @param UserVO
	 * @exception
	 */
	void updateUser(UserVO userVO) throws Exception;
	
	/**
	 * 회원 정보 삭제
	 * @param UserVO
	 * @exception
	 */
	void deleteUser(UserVO userVO) throws Exception;

}
