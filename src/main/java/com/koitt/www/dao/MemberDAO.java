package com.koitt.www.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.koitt.www.vo.MemberVO;

public class MemberDAO {
	
	@Autowired
	SqlSessionTemplate sqlSession;
	
	public int loginProc(MemberVO vo) {
		int cnt=sqlSession.selectOne("mSQL.Login",vo);
		
		return cnt;
	}
	
	public int join(MemberVO vo) {
		int cnt= sqlSession.update("mSQL.Join", vo);
		
		return cnt;
	}
	
	public int insertMemb(MemberVO mVO) {
		return sqlSession.insert("mSQL.addMember",mVO);
	}
	
	public String showName(MemberVO vo) {
		String sname = sqlSession.selectOne("mSQL.Show",vo);
		return sname;
	}
	
	public MemberVO idCheck(String id) {
		return sqlSession.selectOne("mSQL.idCount", id);
			
	}
	
	//회원정보보기
	public MemberVO membInfo(String id) {
		
		return sqlSession.selectOne("mSQL.membInfo", id);
	}
	
	/*
	 * public List<MemberVO> getIdList(){ return sqlSession.selectList(""); }
	 */
	
	public ArrayList membTest02(HashMap map) {
		
		return (ArrayList)sqlSession.selectList("mSQL.test02",map);
	}
}
