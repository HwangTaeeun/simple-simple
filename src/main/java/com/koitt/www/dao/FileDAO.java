package com.koitt.www.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.koitt.www.vo.FileVO;

public class FileDAO {
	
	@Autowired
	SqlSessionTemplate sqlSession;
	
	//파일정보입력 전담 함수
	public int insertPhoto(FileVO fVO) {
		return sqlSession.insert("mSQL.addPic", fVO);
	}
}
