package com.koitt.www.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.koitt.www.vo.MemberVO;

	
	
public class GalleryDAO {
	@Autowired
	SqlSessionTemplate sqlSession;
	
	public int galLoginProc(MemberVO vo) {
		int cnt=sqlSession.selectOne("mSQL.Login",vo);
		
		return cnt;
	}
}
