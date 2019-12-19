package com.koitt.www.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.koitt.www.dao.FileDAO;
import com.koitt.www.dao.GuestboardDAO;
import com.koitt.www.dao.MemberDAO;
import com.koitt.www.services.FileService;
import com.koitt.www.vo.GuestboardVO;
import com.koitt.www.vo.MemberVO;

@Controller
@RequestMapping("/member/")
public class Member {
	@Autowired
	MemberDAO mDAO;

	@Autowired
	GuestboardDAO gsDAO;
	@Autowired
	FileDAO fDAO;
	@Autowired
	FileService fileSrvc;

	@RequestMapping("login.van")
	public ModelAndView loginForm(ModelAndView mv) {

		mv.setViewName("member/login");

		return mv;
	}

	@RequestMapping("join.van")
	public ModelAndView joinform(ModelAndView mv) {
		mv.setViewName("member/join");

		return mv;
	}

	/*-------------------방명록--------------------*/

	// 방명록리스트 매핑

	@RequestMapping("guestboard.van") // ==> /member/guestboard.van
	public ModelAndView guestboard(ModelAndView mv) {

		//3.질의명령 보내고 결과받고
		List list = gsDAO.showList();
		//4. 데이터 전달하고
		mv.addObject("LIST", list);
		System.out.println("asfd");
		//5.뷰를 부른다.
		mv.setViewName("ggggg_board/guestBoard");
		return mv;
	}

	// 방명록 글쓰기 페이지 매핑
	@RequestMapping("gboard.van")
	public ModelAndView gboardWrite(ModelAndView mv) {
		mv.setViewName("ggggg_board/gboardWrite");

		return mv;
	}

	// 글등록처리 함수 매핑

	@RequestMapping("gWriteProc.van")
	public ModelAndView gWriteProc(RedirectView rv, ModelAndView mv, GuestboardVO vo) {

		System.out.println("dd");
		int cnt = gsDAO.gbEnterProc(vo);

		if (cnt == 1) {
			System.out.println("성공");
			rv.setUrl("/www/member/guestboard.van");
		} else {
			System.out.println("실패");
			rv.setUrl("/www/member/login.van");
		}
		return mv;
	}
	

	/*-------------------------------------------*/

	// 회원정보보기
	@RequestMapping("membInfo.van")
	@ResponseBody
	public MemberVO membInfo(String id) {
		MemberVO vo = mDAO.membInfo(id);

		return vo;
	}

	@RequestMapping("show.van")
	public ModelAndView showform(ModelAndView mv) {
		mv.setViewName("member/show");
		return mv;
	}

	@RequestMapping("showw.van")
	public ModelAndView showformm(ModelAndView mv) {
		mv.setViewName("member/showw");
		return mv;
	}

	// 로그인 처리
	@RequestMapping("loginProc.van")
	public ModelAndView loginProc(HttpSession session, RedirectView rv, ModelAndView mv, MemberVO vo) {
		int cnt = mDAO.loginProc(vo);
		if (cnt == 1) {

			// 이 경우는 아이디와 비밀번화가 일치하는 회원이 한명있다는 경우임
			// 로그인 처리 처리를 해주면 되는데
			// 로그인 처리는 세션에 아이디를 입력해주기로 하자.

			session.setAttribute("SID", vo.getId());
			rv.setUrl("/www/member/login.van");
		} else {
			// 로그인이 실패한 경우
			rv.setUrl("/www/member/login.van");
		}
		mv.setView(rv);
		return mv;
	}

	// 로그아웃
	@RequestMapping("logout.van")
	public ModelAndView logout(HttpSession session, RedirectView rv, ModelAndView mv) {

		session.setAttribute("SID", null);

		rv.setUrl("/www");
		mv.setView(rv);
		return mv;
	}

	// 회원가입처리

	@RequestMapping("joinProc.van")
	public ModelAndView joinProc(HttpSession session, RedirectView rv, ModelAndView mv, MemberVO mVO) {
		int cnt = mDAO.join(mVO);
		if (cnt == 1) {
			session.setAttribute("SID", mVO.getId()); // 회원가입이 성공했으므로 로그인 처리를 해준다.
			fileSrvc.setDAO(fDAO);
			fileSrvc.membAddProc(session, mVO);
			rv.setUrl("/www/member/login.van");
		} else {
			rv.setUrl("/www/member/join.van");
		}
		mv.setView(rv);
		return mv;
	}

	/*
	 * @RequestMapping("joinProc.van") public ModelAndView joinProc(ModelAndView mv,
	 * HttpSession session, RedirectView rv) {
	 * 
	 * }
	 */

	@RequestMapping("showProc.van")
	public ModelAndView showProc(RedirectView rv, ModelAndView mv, MemberVO vo) {
		String ssname = mDAO.showName(vo);
		System.out.println(ssname);
		rv.setUrl("/www/member/showw.van");
		mv.setView(rv);
		return mv;
	}

	/*
	 * @RequestMapping("showId.van") public ModelAndView showId(ModelAndView mv) {
	 * List<MemberVO> list =mDAO.getIdList();
	 * 
	 * mv.addObject("LIST", list); mv.setViewName("member/idList");
	 * 
	 * return mv;; }
	 */

	@RequestMapping("idCheck.van")
	@ResponseBody // @RequestParam은
	public MemberVO idCheck(@RequestParam String id) {
		MemberVO vo = mDAO.idCheck(id);
		/*
		 * 우리가 현재 필요한 데이터는 json 형식의 데이터다. 데이터의 숫자가 적을 경우는 해당 json형식의 데이터를 만들어 주는 것이 문제
		 * 없지만 여러개라면 문제가 될 수 있다. 코드의 길이가 늘어날 수 있고 확인하는 작업이 불편해진다.
		 * 
		 * 만약 vo의 모든 변수에 대한 데이터를 json형식으로 변환시켜야 한다면 모든 변수의 키값을 만들고 데이터를 입력해 줘야 하겠다.
		 * 다행이도스프링에서는 json문서를 쉽게 만들 수 있는 방법을 제공하고 있다. 방법] 실행함수의 반환값을 VO 타입으로 정하고 함수에
		 * 
		 * @ResponseBody 라는 어노테이션을 붙여주면 된다. VO에 선언된 변수 이름을 키값으로 하고 입력된 데이터를 value로해서
		 * json 문서를 알아서 만들어 준다.
		 * 
		 */
		return vo;
	}

	/*
	 * 파일업로드 확인용 함수
	 * 
	 * @RequestMapping("fileUp.van") public void fileUp(HttpSession session,
	 * MemberVO mVO) { try { fileSrvc.membAddProc(session, mVO); } catch (Exception
	 * e) {
	 * 
	 * } }
	 */

	@RequestMapping("test02.van")
	public ModelAndView doTest(ModelAndView mv, HashMap<String, String> map) {
		map.put("id", "euns");
		map.put("name", "전은석");
		ArrayList list = mDAO.membTest02(map);
		System.out.println("" + list.get(0).toString());
		mv.addObject("LIST", list);
		mv.setViewName("member/test");
		return mv;
	}

	/*
	 * @RequestMapping("test03.van") public ModelAndView doTest03(ModelAndView
	 * mv, @RequestParam Map<String, String> map) {
	 * System.out.println("### map ==="); map.put("id", "euns"); map.put("name",
	 * "전은석"); ArrayList list = mDAO.membTest03(map); System.out.println("" +
	 * list.get(0).toString()); mv.addObject("LIST", list);
	 * mv.setViewName("member/test"); return mv; }
	 */
}
