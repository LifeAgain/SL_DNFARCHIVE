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

import com.sldnfarchive.model.MenuVO;
import com.sldnfarchive.service.MenuService;

/**
 * @Class Name : MenuServiceImpl.java
 * @Description : Menu Service Implement Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2026.02.10		HHP           최초생성
 *
 * @author HHP
 * @since 2026.02.10
 * @version 1.0
 * @see
 *
 *  Copyright (C) by MOPAS All right reserved.
 */

@Service("menuService")
public class MenuServiceImpl extends EgovAbstractServiceImpl implements MenuService {

	private static final Logger LOGGER = LoggerFactory.getLogger(MenuServiceImpl.class);
	
	@Resource(name="menuMapper")
	private MenuMapper menuMapper;
	
	/**
	 * 메뉴 리스트 조회
	 * @param MenuVO
	 * @return menuMapper.menuList
	 * @exception Exception
	 */
	@Override
	public List<EgovMap> menuList(MenuVO menuVO) throws Exception {
		return menuMapper.menuList(menuVO);
	}
	
	/**
	 * 메뉴 상세정보 조회
	 * @param MenuVO
	 * @return menuMapper.selectMenu
	 * @exception
	 */
	@Override
	public EgovMap selectMenu(MenuVO menuVO) throws Exception {
		return menuMapper.selectMenu(menuVO);
	}
	
	/**
	 * 메뉴 추가
	 * @param MenuVO
	 * @exception
	 */
	@Override
	public void insertMenu(MenuVO menuVO) throws Exception {
		menuMapper.insertMenu(menuVO);
	}
	
	/**
	 * 메뉴 정보 수정
	 * @param MenuVO
	 * @exception
	 */
	public void updateMenu(MenuVO menuVO) throws Exception {
		menuMapper.updateMenu(menuVO);
	}
	
	/**
	 * 메뉴 삭제
	 * @param MenuVO
	 * @exception
	 */
	public void deleteMenu(MenuVO menuVO) throws Exception {
		menuMapper.deleteMenu(menuVO);
	}

}
