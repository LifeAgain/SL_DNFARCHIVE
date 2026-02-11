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

import com.sldnfarchive.model.MenuVO;

/**
 * 메뉴에 관한 데이터처리 매퍼 클래스
 *
 * @author HHP
 * @since 2026.02.10
 * @version 1.0
 * @see <pre>
 *  == 개정이력(Modification Information) ==
 *
 *          수정일          수정자           수정내용
 *  ----------------    ------------    ---------------------------
 *   2026.02.10        		HHP          최초 생성
 *
 * </pre>
 */
@Mapper("menuMapper")
public interface MenuMapper {
	/**
	 * 메뉴 리스트를 조회한다.
	 * @param MenuVO
	 * @exception Exception
	 */
	List<EgovMap> menuList(MenuVO menuVO) throws Exception;

	/**
	 * 메뉴 상세정보 조회
	 * @param MenuVO
	 * @exception
	 */
	EgovMap selectMenu(MenuVO menuVO) throws Exception;
	
	/**
	 * 메뉴 추가
	 * @param MenuVO
	 * @exception
	 */
	void insertMenu(MenuVO menuVO) throws Exception;
	
	/**
	 * 메뉴 정보 수정
	 * @param MenuVO
	 * @exception
	 */
	void updateMenu(MenuVO menuVO) throws Exception;
	
	/**
	 * 메뉴 삭제
	 * @param MenuVO
	 * @exception
	 */
	void deleteMenu(MenuVO menuVO) throws Exception;

}
