package com.spring.place.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.spring.place.member.dao.MemberDAO;
import com.spring.place.member.vo.MemberVO;


@Service("memberService")
// 서비스에 Transaction처리 
@Transactional(propagation = Propagation.REQUIRED)
public class MemberSercvieImpl implements MemberService {
   
	@Autowired
	private MemberDAO memberDAO;
 
	@Override
	public List listMembers() throws DataAccessException {
		
		System.out.println("member.service - 회원 목록");
		return memberDAO.selectAllMembers();
	}

	@Override
	public int addMember(MemberVO memberVO) throws DataAccessException {
		
		System.out.println("member.service - 회원 등록");
		return memberDAO.insertMember(memberVO);
	}
	
	@Override
	public int updateMember(MemberVO memberVO) throws DataAccessException {
		
		System.out.println("member.service - 회원 수정");
		return memberDAO.updateMember(memberVO);
	}

	@Override
	public int removeMember(String id) throws DataAccessException {
		
		System.out.println("member.service - 회원 삭제");
		return memberDAO.deleteMember(id);
	}

	@Override
	public MemberVO getMemberInfo(String id) throws DataAccessException {
		
		System.out.println("member.service - 회원 상세");
		return memberDAO.selectMemberInfo(id);
	}

	@Override
	public String getMemberDBPwd(MemberVO memberVO) throws DataAccessException {
		
		System.out.println("member.service - 비밀번호 일치 유무");
		return memberDAO.selectMemberDBPwd(memberVO);
	}

	@Override
	public MemberVO login(MemberVO member) throws DataAccessException {
		
		System.out.println("member.service - 로그인");
		return memberDAO.selectMemberInfo( member);
	}
}