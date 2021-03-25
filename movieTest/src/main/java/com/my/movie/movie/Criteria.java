package com.my.movie.movie;

public class Criteria {
	private int pageNum;//������ ��ȣ
	private int amount;//�������� ��� ����
	
	private String m_code;//������ ����� ���� �۾�
	

	//���� ��ȸ�� �⺻�����ڿ��� 1������, �������� 10����
	public Criteria() {
	  this(1,10);
	}

	public Criteria(int pageNum, int amount) {
		this.pageNum=pageNum;
		this.amount=amount;
	}

 //getters/setters
	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	
	//������ ����� ���� �۾�
	public String getM_code() {
		return m_code;
	}
	public void setM_code(String m_code) {
		this.m_code = m_code;
	}
	//������ ����� ���� �۾�
	public Criteria(int pageNum, int amount, String m_code) {
		this.pageNum = pageNum;
		this.amount = amount;
		this.m_code = m_code;
	}
	
	
	

	
	
	
}

