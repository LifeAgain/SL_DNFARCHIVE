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

import com.sldnfarchive.model.CodeVO;
import com.sldnfarchive.service.CodeService;

/**
 * @Class Name : CodeServiceImpl.java
 * @Description : Code Service Implement Class
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

@Service("codeService")
public class CodeServiceImpl extends EgovAbstractServiceImpl implements CodeService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CodeServiceImpl.class);
	
	@Resource(name="codeMapper")
	private CodeMapper codeMapper;
	
	/**
	 * 메뉴 리스트 조회
	 * @param CodeVO
	 * @return codeMapper.codeList
	 * @exception Exception
	 */
	@Override
	public List<EgovMap> codeList(CodeVO codeVO) throws Exception {
		return codeMapper.codeList(codeVO);
	}
	
	/**
	 * 코드 상세정보 조회
	 * @param CodeVO
	 * @return codeMapper.selectCode
	 * @exception
	 */
	@Override
	public EgovMap selectCode(CodeVO codeVO) throws Exception {
		return codeMapper.selectCode(codeVO);
	}
	
	/**
	 * 코드 추가
	 * @param CodeVO
	 * @exception
	 */
	@Override
	public void insertCode(CodeVO codeVO) throws Exception {
		codeMapper.insertCode(codeVO);
	}
	
	/**
	 * 코드 정보 수정
	 * @param CodeVO
	 * @exception
	 */
	public void updateCode(CodeVO codeVO) throws Exception {
		codeMapper.updateCode(codeVO);
	}
	
	/**
	 * 코드 삭제
	 * @param MenuVO
	 * @exception
	 */
	public void deleteCode(CodeVO codeVO) throws Exception {
		codeMapper.deleteCode(codeVO);
	}

}
