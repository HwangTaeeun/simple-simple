package com.koitt.www.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.koitt.www.vo.GuestboardVO;

public class GuestboardDAO {
	@Autowired
	SqlSessionTemplate sqlSession;
	
	public int gbEnterProc(GuestboardVO vo) {
		int cnt= sqlSession.update("gSQL.gEnter", vo);
		
		return cnt;
	}
	
	public List showList(){
		return sqlSession.selectList("gsSQL.gSHOW");
	}
	
}
