// 브라우저에서 요청하면 모든 글 정보를 조회한 후 ModelAndView 객체에 바인딩하여 JSP로 전달
package com.spring.place.board.controller;

import java.io.File;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.spring.place.member.vo.MemberVO;
import com.spring.place.board.service.BoardService;
import com.spring.place.board.vo.ArticleVO;

@Controller("boardController")
public class BoardControllerImpl implements BoardController {

	private static final String ARTICLE_IMAGE_REPO = "C:\\board\\article_image";
	
	@Autowired
	BoardService boardService;
	
	@Autowired
	ArticleVO articleVO;
	

	
	@Override
	@RequestMapping(value= "/board/listArticles.do", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView listArticles(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println("board.controller - 게시글 리스트");
		
		// 인터셉터에서 전달된 뷰이름을 가져옴
		String viewName = (String)request.getAttribute("viewName");
		//String viewName = getViewName(request);
		// 모든 글 정보를 조회
		List articlesList = boardService.listArticles();
		ModelAndView mav = new ModelAndView(viewName);
		// 조회한 글 정보를 바인딩한 후 JSP로 전달
		mav.addObject("articlesList", articlesList);
		System.out.println("viewName->"+viewName);
		return mav;
	}

	@Override
	@RequestMapping(value="/board/addNewPlace.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public ResponseEntity addNewPlace(
			MultipartHttpServletRequest multipartRequest,
			HttpServletResponse response) throws Exception {

		System.out.println("board.controller - 게시글 등록");
		
		multipartRequest.setCharacterEncoding("utf-8");
		
		// 글 정보를 저장하기 위한 articleMap을 생성
		Map<String, Object> articleMap = new HashMap<String, Object>();
		Enumeration enu = multipartRequest.getParameterNames();
		
		// 글쓰기창에서 전송된 글 정보를 Map에 key/value로 저장
		while(enu.hasMoreElements()) {
			
			String name = (String)enu.nextElement();
			String value = multipartRequest.getParameter(name);
			
			articleMap.put(name, value);
			System.out.println("name -> " + name + ", value -> " + value);
		}
		
		// 업로드한 이미지 파일 이름을 가져옴
		String imageFileName = upload(multipartRequest);
		
		HttpSession session = multipartRequest.getSession();
		
		// DB에 저장하기 위한 데이터 세팅
		MemberVO memberVO = (MemberVO) session.getAttribute("memberInfo");
		
		// 세션에 저장된 회원 정보로부터 회원ID를 가져옴
		String id = memberVO.getMember_id();
		
		// 글 레벨, 부모 글 번호, 회원ID, 이미지 파일 이름을 articleMap에 저장
		articleMap.put("level", 0); // 원글이면 parentNO가 0
		articleMap.put("parentNO", 0); // 원글이면 parentNO가 0
		articleMap.put("id", id);
		articleMap.put("imageFileName", imageFileName);
		
		String message;
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		try {
			// DB 저장하기
			// 글 정보가 저장된 articleMap을 Service클래스의 addArticle() 메서드로 전달
			int ORDER_SEQ_NUM = boardService.addNewPlace(articleMap);
			System.out.println("addNewPlace의 ORDER_SEQ_NUM -> " + ORDER_SEQ_NUM);
			
			// 글 정보를 추가한 후 업로드한 이미지 파일을 글 번호로 명명한 폴더로 이동
			if(imageFileName != null && imageFileName.length() != 0) {
				
				File srcFile = new 
				File(ARTICLE_IMAGE_REPO+"\\"+"temp"+"\\"+imageFileName);
				File destDir = new File(ARTICLE_IMAGE_REPO+"\\"+ORDER_SEQ_NUM);
				FileUtils.moveFileToDirectory(srcFile, destDir,true);
			}
	
			// 새 글을 추가한 후 메시지를 전달
			message = "<script>";
			message += " alert('새글을 추가했습니다.');";
			message += " location.href='" + multipartRequest.getContextPath() + "/board/listArticles.do';";
			message +=" </script>";
			
		    resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		    
		} catch(Exception e) {
			
			File srcFile = new File(ARTICLE_IMAGE_REPO + "\\" + "temp" + "\\" + imageFileName);
			srcFile.delete(); // 임시 저장된 파일 삭제
			
			// 오류 발생 시 오류 메시지를 전달
			message = " <script>";
			message += " alert('오류가 발생했습니다. 다시 시도해 주세요');');";
			message += " location.href='" + multipartRequest.getContextPath() + "/board/articleForm.do'; ";
			message += " </script>";
			
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			e.printStackTrace();
		}
		
		return resEnt;
	}
	
	@Override
	@RequestMapping(value="/board/viewArticle.do", method = RequestMethod.GET)
	public ModelAndView viewArticle(@RequestParam("ORDER_SEQ_NUM") int ORDER_SEQ_NUM, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("board.controller - 게시글 상세");
		
		//String viewName = (String)request.getAttribute("viewName");
		String viewName = getViewName(request);
		System.out.println("viewArticle의 ORDER_SEQ_NUM->"+ORDER_SEQ_NUM);
		articleVO = boardService.viewArticle(ORDER_SEQ_NUM);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		mav.addObject("article", articleVO);
		System.out.println("mav값 확인 -> " + mav);
		
		return mav;
	}
	
	@RequestMapping(value="/board/modArticle.do" ,method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity modArticle(
			MultipartHttpServletRequest multipartRequest,
			HttpServletResponse response) throws Exception {
		
		System.out.println("board.controller - 게시글 수정");
		
	    multipartRequest.setCharacterEncoding("utf-8");
	    
		Map<String,Object> articleMap = new HashMap<String, Object>();
		Enumeration enu = multipartRequest.getParameterNames();
		
		while(enu.hasMoreElements()) {
			
			String name = (String)enu.nextElement();
			String value = multipartRequest.getParameter(name);
			System.out.println("수정내역:"+ name+"수정 값:"+value);
			
			articleMap.put(name,value);
		}
		
		String imageFileName= upload(multipartRequest);
		HttpSession session = multipartRequest.getSession();
		MemberVO memberVO = (MemberVO) session.getAttribute("memberInfo");
		String id = memberVO.getMember_id();
		
		// 수정
		articleMap.put("id", id);
		articleMap.put("imageFileName", imageFileName);
		
		// 파라미터로 넘어온 글 번호
		String ORDER_SEQ_NUM = (String)articleMap.get("ORDER_SEQ_NUM");
		
		String message;
		ResponseEntity resEnt=null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
	    try {
	    	// DB에 수정본 바영
	    	boardService.modArticle(articleMap);

	    	if(imageFileName != null && imageFileName.length() != 0) {

	    		File srcFile = new File(ARTICLE_IMAGE_REPO + "\\" + "temp" + "\\" + imageFileName);
	    		File destDir = new File(ARTICLE_IMAGE_REPO + "\\" + ORDER_SEQ_NUM);
	    		FileUtils.moveFileToDirectory(srcFile, destDir, true);
	         
	    		String originalFileName = (String)articleMap.get("originalFileName");
	    		File oldFile = new File(ARTICLE_IMAGE_REPO + "\\" + ORDER_SEQ_NUM + "\\" + originalFileName);
	    		oldFile.delete();
	    	}
	    	
	    	message = "<script>";
			message += "alert('글을 수정했습니다.');";
			message += "location.href='" + multipartRequest.getContextPath() + "/board/viewArticle.do?ORDER_SEQ_NUM=" + ORDER_SEQ_NUM + "';";
			//message += "location.href='" + multipartRequest.getContextPath() + "/board/listArticles.do';";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			
	    } catch(Exception e) {
	    	
	    	File srcFile = new File(ARTICLE_IMAGE_REPO + "\\" + "temp" + "\\" + imageFileName);
	    	srcFile.delete();
		      
			message = "<script>";
			message += "alert('오류가 발생했습니다. 다시 수정해주세요');";
			message += "location.href='" + multipartRequest.getContextPath() + "/board/viewArticle.do?ORDER_SEQ_NUM=" + ORDER_SEQ_NUM + "';";
			message +=" </script>";
						  
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			
	    }
	    return resEnt;
	}
	
	@Override
	@RequestMapping(value="/board/removeArticle.do", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity removeArticle(
			@RequestParam("ORDER_SEQ_NUM") int ORDER_SEQ_NUM,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println("board.controller - 게시글 삭제");
		
		response.setContentType("text/html; charset=UTF-8");
		
		String message;
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		try {
			// 글 번호에 해당하는 내용을 삭제
			boardService.removeArticle(ORDER_SEQ_NUM);
			
			// 저장된 이미지 파일은 글 번호의 디렉토리에 저장
			File destDir = new File(ARTICLE_IMAGE_REPO + "\\" + ORDER_SEQ_NUM);
			// 글 번호로 저장된 디렉토리를 삭제
			FileUtils.deleteDirectory(destDir);
			
			message = "<script>";
			message += "alert('게시글을 삭제했습니다.');";
			message += "location.href='" + request.getContextPath() + "/board/listArticles.do';";
			message += "</script>";
			
		    resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		       
		} catch(Exception e) {
			
			message = "<script>";
			message += "alert('작업중 오류가 발생했습니다. 다시 시도해 주세요.');";
			message += "location.href='" + request.getContextPath() + "/board/listArticles.do';";
			message += "</script>";
			
		    resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		    e.printStackTrace();
		}
		
		return resEnt;
	}
	
	@RequestMapping(value = "/board/*Form.do", method =  {RequestMethod.GET, RequestMethod.POST} )
	private ModelAndView form(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("board.controller - 게시글/답글 등록폼");
		
		HttpSession session = request.getSession();
		MemberVO memberVO = (MemberVO) session.getAttribute("memberInfo");	
		
		String parentNO = request.getParameter("parentNO");
		System.out.println("1) parentNO 값 확인 -> " + parentNO);
		String level = request.getParameter("level");
		System.out.println("1) level 값 확인 -> " + level);

		if(parentNO != null) {			
			session.setAttribute("parentNO", parentNO);
		}
		
		if(level != null) {
			session.setAttribute("level", level);
		}
		
		System.out.println("2) parentNO 값 확인 -> " + parentNO);
		System.out.println("2) level 값 확인 -> " + level);
		
		String viewName = (String)request.getAttribute("viewName");
		System.out.println("form의 viewName -> " + viewName);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		
		return mav;
	}
	
	@Override
	@RequestMapping(value="/board/addReply.do", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity addReply(MultipartHttpServletRequest multipartRequest, HttpServletResponse response) throws Exception {

		System.out.println("board.controller - 답글 등록");
		
		multipartRequest.setCharacterEncoding("utf-8");
		
		// 답글 정보를 저장하기 위한 articleMap을 생성
		Map<String, Object> articleMap = new HashMap<String, Object>();
		Enumeration enu = multipartRequest.getParameterNames();
		
		// 답글쓰기 창에서 전송된 답글 정보를 Map에 key/value로 저장
		while(enu.hasMoreElements()) {
			
			String name = (String)enu.nextElement();
			String value = multipartRequest.getParameter(name);
			
			articleMap.put(name, value);
			System.out.println("name -> " + name + ", value -> " + value);
		}
		
		// 업로드한 이미지 파일 이름을 가져옴
		String imageFileName = upload(multipartRequest);
		
		HttpSession session = multipartRequest.getSession();
		
		// DB에 저장하기 위한 데이터 세팅
		MemberVO memberVO = (MemberVO) session.getAttribute("memberInfo");
		
		// 세션에 저장된 회원 정보로부터 회원ID를 가져옴
		String id = memberVO.getMember_id();
	
		System.out.println("title:"+ articleMap.get("goods_name"));
		System.out.println("goods_contents:"+ articleMap.get("goods_contents"));
		
		// 답글 레벨, 부모 글 번호, 회원ID, 이미지 파일 이름을 articleMap에 저장
		articleMap.put("parentNO", Integer.parseInt(multipartRequest.getParameter("parentNO")));
		articleMap.put("id", id);
		/* articleMap.put("goods_name", multipartRequest.getParameter("goods_name")); */
		articleMap.put("goods_name", articleMap.get("goods_name"));
		articleMap.put("imageFileName", imageFileName);
		articleMap.put("level",0);
		//articleMap.put("goods_contents","aaa");
	
		System.out.println("level:::"+articleMap.get("level"));
		
		System.out.println("id-->"+id);
		System.out.println("parentNO-->"+ Integer.parseInt(multipartRequest.getParameter("parentNO")));
		System.out.println("goods_name-->"+multipartRequest.getParameter("goods_name"));
		
		
		String message;
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		try {
			// DB 저장하기
			// 답글 정보가 저장된 articleMap을 Service클래스의 addArticle() 메서드로 전달
			int ORDER_SEQ_NUM = boardService.addReply(articleMap);
			System.out.println("ORDER_SEQ_NUM -> " + ORDER_SEQ_NUM);
			
			// 답글 정보를 추가한 후 업로드한 이미지 파일을 답글 번호로 명명한 폴더로 이동
			if(imageFileName != null && imageFileName.length() != 0) {
				
				File srcFile = new 
				File(ARTICLE_IMAGE_REPO + "\\" + "temp" + "\\" + imageFileName);
				File destDir = new File(ARTICLE_IMAGE_REPO + "\\" + ORDER_SEQ_NUM);
				FileUtils.moveFileToDirectory(srcFile, destDir,true);
			}
	
			// 새 답글을 추가한 후 메시지를 전달
			message = "<script>";
			message += "alert('새 답글을 추가했습니다.');";
			message += "location.href='" + multipartRequest.getContextPath() + "/board/listArticles.do';";
			message += "</script>";
			
		    resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		    
		} catch(Exception e) {
			
			File srcFile = new File(ARTICLE_IMAGE_REPO + "\\" + "temp" + "\\" + imageFileName);
			srcFile.delete(); // 임시 저장된 파일 삭제
			
			// 오류 발생 시 오류 메시지를 전달
			message = "<script>";
			message += "alert('오류가 발생했습니다. 다시 시도해 주세요');');";
			message += "location.href='" + multipartRequest.getContextPath() + "/board/articleForm.do'; ";
			message += "</script>";
			
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			e.printStackTrace();
		}
		
		return resEnt;
	}
	
	// 업로드한 파일 이름을 얻은 후 반환; 한 개 이미지 업로드 하기
	private String upload(MultipartHttpServletRequest multipartRequest) throws Exception{
		
		System.out.println("board.controller - 파일 업로드 처리");
		String imageFileName = null;
		Iterator<String> fileNames = multipartRequest.getFileNames();
		
		while(fileNames.hasNext()) {
			
			String fileName = fileNames.next();
			MultipartFile mFile = multipartRequest.getFile(fileName);
			imageFileName = mFile.getOriginalFilename();
			File file = new File(ARTICLE_IMAGE_REPO + "\\" + fileName);
			
			if(mFile.getSize() != 0) { // File Null Check
				if(! file.exists()) { // 경로상에 파일이 존재하지 않을 경우
					if(file.getParentFile().mkdirs()) { // 경로에 해당하는 디렉토리들을 생성
						file.createNewFile(); // 이후 파일 생성
					}
				}
				mFile.transferTo(new File(ARTICLE_IMAGE_REPO + "\\" + "temp" + "\\" + imageFileName)); //임시로 저장된 multipartFile을 실제 파일로 전송
			}
		}
		return imageFileName;
	}
	// request 객체로부터 viewName을 만드는 메소드; 요청명에서 뷰이름을 반환
		private String getViewName(HttpServletRequest request) throws Exception {

			String contextPath = request.getContextPath();
			String uri = (String) request.getAttribute("javax.servlet.include.request_uri");

			if (uri == null || uri.trim().equals("")) {
				uri = request.getRequestURI();
			}

			int begin = 0;
			if (!((contextPath == null) || ("".equals(contextPath)))) {
				begin = contextPath.length();
			}

			int end;
			if (uri.indexOf(";") != -1) {
				end = uri.indexOf(";");
			} else if (uri.indexOf("?") != -1) {
				end = uri.indexOf("?");
			} else {
				end = uri.length();
			}

			String fileName = uri.substring(begin, end);
			if (fileName.indexOf(".") != -1) {
				fileName = fileName.substring(0, fileName.lastIndexOf("."));
			}
			if (fileName.lastIndexOf("/") != -1) {
				fileName = fileName.substring(fileName.lastIndexOf("/", 1), fileName.length());
			}
			return fileName;
		}
}