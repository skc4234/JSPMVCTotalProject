package com.sist.model;

import java.util.*;

import com.sist.controller.Controller;
// 1. 브라우저 요청 받기
// 2. 요청 처리=>DAO, OpenAPI 등으로 요청처리
// 3. 결과값을 request/session 에 담아서 JSP로 전송
// 데이터 전송 : request.setAttribute() / session.setAttribute()
//                   대부분 사용            사용자 정보/장바구니 등
//                                        프로그램 종료시까지 기억
import com.sist.controller.RequestMapping;
import com.sist.dao.SeoulDAO;
import com.sist.vo.SeoulVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@Controller
public class SeoulModel {
	private String[] table= {
		"",
		"seoul_location",
		"seoul_nature",
		"seoul_shop",
		"seoul_hotel"
	};
	
	private String[] title= {
			"",
			"서울 명소",
			"서울 자연",
			"서울 쇼핑",
			"서울 호텔"
		};
	
	@RequestMapping("seoul/list.do")
	public String seoul_lit(HttpServletRequest request,HttpServletResponse respone) {
		String page=request.getParameter("page");
		if(page==null) page="1";
		String tno=request.getParameter("tno");
		if(tno==null) tno="1";
		
		int curPage=Integer.parseInt(page);
		Map map=new HashMap();
		map.put("table", table[Integer.parseInt(tno)]);
		map.put("start", (curPage*12)-12);
		
		List<SeoulVO> list=SeoulDAO.seoulListData(map);
		int totalPage=SeoulDAO.seoulTotalPage(map);
		
		// 데이터 전송
		request.setAttribute("list", list);
		request.setAttribute("curPage", curPage);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("tno", tno);
		request.setAttribute("title", title[Integer.parseInt(tno)]);
		
		return "../seoul/list.jsp";
	}
	
	// Annotation: 구분자(자동 호출)
	// 밑에 있거나 옆에 있는 메소드 / 클래스 / 멤버변수 처리
	@RequestMapping("seoul/detail.do")
	public String seoul_detail(HttpServletRequest request, HttpServletResponse response) {
		String no=request.getParameter("no");
		String tno=request.getParameter("tno");
		
		Map map = new HashMap();
		map.put("table", table[Integer.parseInt(tno)]);
		map.put("no", Integer.parseInt(no));
		
		SeoulVO vo=SeoulDAO.seoulDetailData(map);
		
		request.setAttribute("vo", vo);
		request.setAttribute("tno", tno);
		
		return "../seoul/detail.jsp";
	}
}
