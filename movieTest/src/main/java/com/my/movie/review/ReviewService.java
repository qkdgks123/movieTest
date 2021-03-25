package com.my.movie.review;

import java.util.List;

import com.my.movie.movie.Criteria;
import com.my.movie.user.CustomerVO;

public interface ReviewService {
	// CRUD ����� �޼ҵ� ����
	// ���� ���
	public void insertReview(ReviewVO vo);
	//��ȭ���� ����ѹ� 1�� �ʱ�ȭ
	public List<ReviewVO> chkFirstReview(String m_code);

	
	//���� ��� ���, ����¡ó������
	public List<ReviewVO> getReviewList(Criteria cri);
	//����¡ó�� ����
	public int getTotal(Criteria cri);

	
	//������ ���
	public List<ReviewVO> getReviewList(ReviewVO vo);
	//�ش� ��ȭ ��������
	public float getReviewAvg(String m_code);
	
	//���� ����
	public void deleteReview(ReviewVO vo);
	
	//���� ������ ���� �缳���� ���� �۾�
	public int getReviewListFromDelete(ReviewVO vo);
	public void reNameReview(ReviewVO vo);
	
	
	


	
}
