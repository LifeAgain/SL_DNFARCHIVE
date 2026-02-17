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

import com.sldnfarchive.model.CodeVO;

/**
 * @Class Name : CodeService.java
 * @Description : CodeService Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2026.02.18		HHP           최초생성
 *
 * @author HHP
 * @since 2026.02.18
 * @version 1.0
 * @see
 *
 *  Copyright (C) by MOPAS All right reserved.
 */
public interface CodeService {

	/**
	 * 공통코드 목록을 조회한다.
	 * @param CodeVO
	 * @exception Exception
	 */
	List<EgovMap> codeList(CodeVO codeVO) throws Exception;

	/**
	 * 코드 상세정보 조회
	 * @param CodeVO
	 * @exception
	 */
	EgovMap selectCode(CodeVO codeVO) throws Exception;
	
	/**
	 * 코드 추가
	 * @param CodeVO
	 * @exception
	 */
	void insertCode(CodeVO codeVO) throws Exception;
	
	/**
	 * 메뉴 정보 수정
	 * @param CodeVO
	 * @exception
	 */
	void updateCode(CodeVO codeVO) throws Exception;
	
	/**
	 * 메뉴 삭제
	 * @param CodeVO
	 * @exception
	 */
	void deleteCode(CodeVO codeVO) throws Exception;

}
