package com.spring.place.member.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.spring.place.member.vo.MemberVO;

public interface MemberService {
	
	// * 회원 목록
	public List listMembers() throws DataAccessException;
	
	// * 회원 등록
	public int addMember(MemberVO memberVO) throws DataAccessException;
	
	// * 회원 수정
	public int updateMember(MemberVO memberVO) throws DataAccessException;
	
	// * 회원 삭제
	public int removeMember(String id) throws DataAccessException;
	
	// * 회원 상세
	public MemberVO getMemberInfo(String id) throws DataAccessException;
	
	// * 비밀번호 일치 유무
	public String getMemberDBPwd(MemberVO memberVO) throws DataAccessException;
	
	// * 로그인(입력한 ID와 비밀번호로 회원 정보 조회)
	public MemberVO login(MemberVO member) throws DataAccessException;
}