package com.spring.place.member.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.spring.place.member.vo.MemberVO;

public interface MemberDAO {
	
	// * 회원 목록
	public List selectAllMembers() throws DataAccessException;
	
	// * 회원 등록
	public int insertMember(MemberVO memberVO) throws DataAccessException;
	
	// * 회원 수정
	public int updateMember(MemberVO memberVO) throws DataAccessException;
	
	// * 회원 삭제
	public int deleteMember(String id) throws DataAccessException;
	
	// * 회원 상세
	public MemberVO selectMemberInfo(String id) throws DataAccessException;
	
	// * 비밀번호 일치 유무
	public String selectMemberDBPwd(MemberVO memberVO)throws DataAccessException;
	
	// * 로그인(입력한 ID와 비밀번호로 회원 정보 조회)
	public MemberVO selectMemberInfo(MemberVO memberVO) throws DataAccessException;
}