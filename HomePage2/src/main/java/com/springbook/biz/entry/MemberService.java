package com.springbook.biz.entry;

import java.util.List;

public interface MemberService {
	// CRUD ����� �޼ҵ� ����
	// ȸ�� ���
	void addMember(MemberVO mvo);

	// ȸ�� ����
	void updateMember(MemberVO mvo);

	// ȸ�� ����
	void deleteMember(MemberVO mvo);

	// ȸ�� ���
	MemberVO getMember(MemberVO mvo);


/*	// ȸ�� ��� ��ȸ
	List<MemberVO> getMemberList(MemberVO mvo);*/
}
