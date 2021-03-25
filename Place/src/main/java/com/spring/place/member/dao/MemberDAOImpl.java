package com.spring.place.member.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.spring.place.member.vo.MemberVO;


//mybatis메소드
//여러건의 객체 얻기  List  selectList(매퍼아이디,파라미터);
//한건의 객체 얻기  Object selectOne(매퍼아이디,파라미터);
//입력 int insert(매퍼아이디,파라미터);
//수정 int update(매퍼아이디,파라미터);
//삭제 int delete(매퍼아이디,파라미터);

@Repository("memberDAO")
public class MemberDAOImpl implements MemberDAO {
    @Autowired
	private SqlSession sqlSession;
  
	@Override
	public List selectAllMembers() throws DataAccessException {
		
		System.out.println("member.dao - 회원 목록");
		
		return sqlSession.selectList("mapper.member.listAllMembers");
	}

	@Override
	public int insertMember(MemberVO memberVO) throws DataAccessException {
		
		System.out.println("member.dao - 회원 등록");
		
		return sqlSession.insert("mapper.member.insertMember", memberVO);
	}
	
	@Override
	public int updateMember(MemberVO memberVO) throws DataAccessException {
		
		System.out.println("member.dao - 회원 수정");
		return sqlSession.update("mapper.member.updateMemberInfo", memberVO);
	}

	@Override
	public int deleteMember(String id) throws DataAccessException {
		
		System.out.println("member.dao - 회원 삭제");
		
		return sqlSession.delete("mapper.member.deleteMember", id);
	}
	
	@Override
	public MemberVO selectMemberInfo(String id) throws DataAccessException {
		
		System.out.println("member.dao - 회원 상세");
		return (MemberVO) sqlSession.selectOne("mapper.member.selectMemberInfo", id);
	}

	@Override
	public String selectMemberDBPwd(MemberVO memberVO) throws DataAccessException {
		
		System.out.println("member.dao - 비밀번호 일치 유무");
		return (String) sqlSession.selectOne("mapper.member.selectMemberDBPwd",memberVO);
	}

	@Override
	public MemberVO selectMemberInfo(MemberVO memberVO) throws DataAccessException {
		
		System.out.println("member.dao - 로그인(입력한 ID와 비밀번호로 회원 정보 조회)");
		
		MemberVO mem=(MemberVO)sqlSession.selectOne("mapper.member.selectMemberInfoBymemberVO", memberVO);
		
		return mem;
	
	}
}