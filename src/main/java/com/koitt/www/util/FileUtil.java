package com.koitt.www.util;
/**
 * 이 클래스는 파일 업로드에 필요한 기능을 처리하기 위해서 만든 유틸리티적 클래스이다.
 * 
 * 
 * @author 전은석
 * @since 2019-12-16
 * @version v.1.0
 * @see
 * 
 * 		변경이력
 * 			2019.12.16	-	클래스 제작	-담당자 : 황태은
 * 
 */

import java.io.*;

public class FileUtil {
	/*
	 	파일 이름이 중복되면 이전에 업로드한 파일에 덮어쓰기가 된다.
	 	따라서, 혹시 같은 이름의 파일이 존재하면
	 	파일 이름을 바꿔서 저장 해줘야 이전에
	 	업로드했던 동일한 이름의 파일도 유지가 되고
	 	내가 현재 업로드하는 파일도 저장이 된다.
	 */
	public static String rename(String path, String oldName) {
		/*
		 	정책설정]
		 		혹시 같은 이름의 파일이 존재하면_1을 붙여서 이름을 바꾸는 방식을 사용하자.
		 		예]
		 			sample.jsp 라는 파일을 업로드 하려 하는데
		 			이미 업로드된 파일의 이름이 존재한다면
		 			sample_1.jpg라는 이름으로 변경시켜서 저장하자.
		 */
		int count =0;//동일한 파일의 경우 붙여질 번호를 기억할 변수
		/*
		 	sample_1.jpg이 이을 경우는
		 	sample_2.jpg라는 이름으로 파일이 만들어져야 될것이다.
		 
		 */
		String tmpName = oldName; //현재 이름을 따로 기억해 놓는다.
		
		File file = new File(path,oldName);
		// 먼저 oldName으로 만들어진 파일이 있는지 체크한다.
		
		while(file.exists()) {
			//이미 존재하는 경우는
			//따라서 다름이름으로 바꿔줘야 한다.
			//이름을 바꿔줘야 한다.
			//이 떄 우리는 _에 숫자를 붙여서 파일이름을 만들기로 했다.
			
			count++;
			int len = tmpName.lastIndexOf(".");		//파일이름이 asdf.jpg일경우 .을 찾는다
			String tmp1 = tmpName.substring(0, len);	//a부터.앞까지
			oldName = tmp1 + "_" + count + tmpName.substring(len); //asdf + _ + count + .뒤의확장자명 
			
			file = new File(path, oldName);
		}
		// 이 줄이 실행이 되는 경우는 oldName으로 입력한 파일이 없는 경우 또는
		// newName 으로 파일이 없는 경우 즉, file.exists()의 결과가 false 인 경우
		
		return oldName;

	}
	
}
