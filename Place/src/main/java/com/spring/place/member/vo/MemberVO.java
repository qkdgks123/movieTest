package com.spring.place.member.vo;

import java.sql.Date;

import org.springframework.stereotype.Component;

// id가 memberVO인 빈을 자동 생성하도록 설정
@Component("memberVO")
public class MemberVO {
	
	private String member_id;
	private String member_pw;
	private String member_name;
	private String tel1;
	private String tel2;
	private String tel3;
	private String email1;
	private String email2;
	private int point;
	private Date joinDate;
	
	
	public String getTel1() {
		return tel1;
	}

	public void setTel1(String tel1) {
		this.tel1 = tel1;
	}

	public String getTel2() {
		return tel2;
	}

	public void setTel2(String tel2) {
		this.tel2 = tel2;
	}

	public String getTel3() {
		return tel3;
	}

	public void setTel3(String tel3) {
		this.tel3 = tel3;
	}

	public String getEmail1() {
		return email1;
	}

	public void setEmail1(String email1) {
		this.email1 = email1;
	}

	public String getEmail2() {
		return email2;
	}

	public void setEmail2(String email2) {
		this.email2 = email2;
	}


	public MemberVO() {
		
	}
	
	

	/**
	 * @param member_id
	 * @param member_pw
	 * @param member_name
	 * @param tel1
	 * @param tel2
	 * @param tel3
	 * @param email1
	 * @param email2
	 * @param point
	 * @param joinDate
	 */
	public MemberVO(String member_id, String member_pw, String member_name, String tel1, String tel2, String tel3,
			String email1, String email2, int point, Date joinDate) {
		super();
		this.member_id = member_id;
		this.member_pw = member_pw;
		this.member_name = member_name;
		this.tel1 = tel1;
		this.tel2 = tel2;
		this.tel3 = tel3;
		this.email1 = email1;
		this.email2 = email2;
		this.point = point;
		this.joinDate = joinDate;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public String getMember_pw() {
		return member_pw;
	}

	public void setMember_pw(String member_pw) {
		this.member_pw = member_pw;
	}

	public String getMember_name() {
		return member_name;
	}

	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}

	

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	@Override
	public String toString() {
		return "MemberVO [member_id=" + member_id + ", member_pw=" + member_pw + ", member_name=" + member_name
				+ ", tel1=" + tel1 + ", tel2=" + tel2 + ", tel3=" + tel3 + ", email1=" + email1 + ", email2=" + email2
				+ ", point=" + point + ", joinDate=" + joinDate + "]";
	}

	
}