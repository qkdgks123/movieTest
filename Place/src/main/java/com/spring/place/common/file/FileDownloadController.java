// 다중 파일 업로드하기
package com.spring.place.common.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FileDownloadController {
	
	private static final String ARTICLE_IMAGE_REPO = "C:\\shopping\\goods";
	
	@RequestMapping("/download.do")
	protected void download(
			// 이미지 파일 이름을 바로 설정
			@RequestParam("imageFileName") String imageFileName,
			@RequestParam("ORDER_SEQ_NUM") String ORDER_SEQ_NUM,
			HttpServletResponse response) throws Exception {
		
		System.out.println(" * FileDownloadController");
		System.out.println("1) imageFileName -> " + imageFileName);
		System.out.println("2) ORDER_SEQ_NUM -> " + ORDER_SEQ_NUM);

		// file 출력 스트림 생성 -> byte 단위의 OutputStream 사용
		OutputStream out = response.getOutputStream();
		
		// 글 번호와 파일 이름으로 다운로드할 파일 경로를 설정
		String downFile = ARTICLE_IMAGE_REPO + "\\" + imageFileName;
		System.out.println("3) downFile -> " + downFile);
		
		// 다운로드할 파일 객체를 생성
		File file = new File(downFile);
		System.out.println("4) file.toString() 값 -> " + file.toString());
		
		response.setHeader("Cache-Control", "no-cache");
		// 헤더에 파일 이름을 설정
		response.addHeader("Content-disposition", "attachment; fileName=" + imageFileName);
		
		FileInputStream in = new FileInputStream(file);
		byte[] buffer = new byte[1024 * 8];
		
		// 버퍼를 이용해 한 번에 8Kbyte씩 브라우저로 전송
		while(true) {
			
			// read(배열) -> 리턴값을 byte배열에 저장된 길이
			int count = in.read(buffer); // 버퍼에 읽어들인 문자 개수(길이)
			
			// 버퍼의 마지막에 도달했는지 체크(파일의 끝일 때 -1을 리턴)
			if (count == -1)
				break;
			
			// 버퍼에 저장된 길이만큼 file에 기록
			out.write(buffer, 0, count);
		}
		
		// 자원 해제
		in.close();
		out.close();
	}
}