package com.koitt.www.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.koitt.www.dao.GalleryDAO;
import com.koitt.www.vo.GalleryVO;
import com.koitt.www.vo.MemberVO;

@Controller
@RequestMapping("/gallery/")
public class Gallery {
	@Autowired
	GalleryDAO galDAO;

	//-------------------뷰 매핑----------------------
	@RequestMapping("gallery.van")
	public void galleryForm() {
		
	}
	
	@RequestMapping("galleryLogin.van")
	public void galleryLoginForm() {
	
	}
	
	@RequestMapping("galleryWriteBoard.van")
	public void galleryWriteBoardForm() {
		
	}
	//---------------------뷰매핑--------------------
	
	
	
	//-------------------처리함수--------------------
	//로그인
	@RequestMapping("galleryLoginProc.van")
	public ModelAndView galleryLoginProc(HttpSession session, RedirectView rv, ModelAndView mv,MemberVO vo) {
		int cnt = galDAO.galLoginProc(vo);
		
		if(cnt==1) {
			session.setAttribute("SID", vo.getId());
			rv.setUrl("/www/gallery/gallery.van");
			mv.setView(rv);
		}else {
			rv.setUrl("/www/gallery/galleryLogin.van");
		}
		return mv;
	}
	
	//로그아웃
	@RequestMapping("galleryLogout.van")
	public ModelAndView galleryLogout(ModelAndView mv, RedirectView rv, HttpSession session) {
		session.setAttribute("SID", "");
		rv.setUrl("/www/gallery/gallery.van");
		mv.setView(rv);
		
		return mv;
	}
	
	//글쓰기 등록
	@RequestMapping("galleryWrite.van")
	public ModelAndView gWrite(ModelAndView mv, RedirectView rv, MemberVO vo) {
		int cnt;
		
		return mv;
	}
}
