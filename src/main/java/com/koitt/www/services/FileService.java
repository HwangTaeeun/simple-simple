package com.koitt.www.services;

import java.io.File;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

import com.koitt.www.dao.FileDAO;
import com.koitt.www.util.FileUtil;
import com.koitt.www.vo.FileVO;
import com.koitt.www.vo.MemberVO;

public class FileService {

	Object dao;

	public void setDAO(Object dao) {
		this.dao = dao;
	};

	// 파일업로드를 처리할 함수
	public String singleUpProc(HttpSession session, MultipartFile multi) {
		// 이 함수는 파일을 업로드 하기 위해서 컨트롤러에서 업로드할 파일의 정보를 받아와야 한다.
		// 그정보는 MUltipartFile 이라는 타입으로 전송이 될것이고
		// 거기서 꺼내서 사용해야 한다

		// 저장 이름을 기억할 변수
		System.out.println("dd");
		String saveName = "";
		
		Long len;
		
		String spath = session.getServletContext().getRealPath("resources/upload");
		System.out.println("spath ==== " + spath);
		/*
		 * String path = this.getClass().getResource("/").getPath(); int idx =
		 * path.indexOf("/WEB-INF"); path = path.substring(0,idx) + "/resources/upload";
		 * System.out.println("srvc path : " + path );
		 */

		//String rePath = spath.substring(0, spath.indexOf("\\.metadata"));
//		String rePath = "D:\\test\\simple-simple\\src\\main\\webapp\\resources\\upload";
		String rePath = "D:\\test\\simple-simple\\src\\main\\webapp\\resources\\upload";
		System.out.println("$$$$$$$$$$$ repath : " + rePath);
		// 먼저 클라이언트가 업로드한 원본이름을 알아낸다.
		String oriName = "";
		try {
			System.out.println("######################");
			oriName = multi.getOriginalFilename();
			System.out.println("*****************");
			System.out.println("ori == " + oriName);
		} catch(Exception e) {
			return "";
		}

		// 파일을 올리지 않은 경우
		/*
		 * if (oriName == null || oriName.length() == 0) { System.out.println("안올림");
		 * return ""; }
		 */

		// 이 라인이 실행된다는 의미는 업로드할 파일이 존재한다는 이야기 이다.
		// 혹시 업로드할 파일하고 중복되는 이름의 파일이 이미 존재하는지 검사해서
		// 있는경우는 다른이름으로 저장을 해야 한다.
		String tmp = FileUtil.rename(spath, oriName);

		// 저장할 이름이 생겼으므로 준비된 변수에 기억해 놓는다.
		saveName = FileUtil.rename(spath, oriName);

		// 이제 업로드된 파일을 우리의 정상적인 경로에 저장한다.
		// 이 이름인 이후 데이터베이스에 등록할 때 사용해야 한다.
		// 따라서 이름을 기억해 놓을 필요가 생겼다.

		/*
		 * FileInputStream fin = null; //기본스트림 BufferedInputStream bin =null;
		 * //보조스트림(필터스트림) PrintStream ps = null; //보조스티림(기본스트림을 내부적으로 만들어서 사용한다.)
		 */
		try {
			File file = new File(spath, saveName);
			multi.transferTo(file);
			
//            len = mVO.getFile().getSize();
			// 작업경로에 복사
			file = new File(rePath, saveName);
			multi.transferTo(file);

			/*
			 * fin = new FileInputStream(file); bin = new BufferedInputStream(fin);
			 */
			// ps = new PrintStream(p)
			System.out.println("########complete#########");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			/*
			 * try{ fin.close(); bin.close(); ps.close(); }catch(Exception e){}
			 */
		}
		
		return saveName;
	}

	// 다중 파일 업로드 처리 함수
	  public String[] uploadProc(HttpSession session, MultipartFile[] multi) { 
		  // 이함수는 다중 파일 업로드를 처리할 함수
		  // 그런데 단일 파일을 처리할 함수를 이미 만들어 놓았다.
		  // 따라서 위에서 만든 함수를 호출해서 반복처리만 해주면 될 것이다.
		  
		  String[] saveName = new String[multi.length];
		  
		  for(int i=0; i< multi.length; i++) {
			  saveName[i] = singleUpProc(session, multi[i]); 
		  }
		  
		  return saveName;
	  }

	  // 회원가입 파일 업로드 처리 함수
	 public int membAddProc(HttpSession session, MemberVO mVO) {
		 int cnt=0;
		 
		 String saveName = singleUpProc(session, mVO.getsFile());
		 	FileVO fVO = new FileVO();
			fVO.setMno(mVO.getMno());
			fVO.setOriName(mVO.getsFile().getOriginalFilename());
			fVO.setSaveName(saveName);
			fVO.setDir();
			fVO.setLen(mVO.getsFile().getSize());
			
			cnt = ((FileDAO)dao).insertPhoto(fVO);
		 
		 return cnt;
	 }
}
