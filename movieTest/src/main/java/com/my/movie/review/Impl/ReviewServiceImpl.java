package com.my.movie.review.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.movie.movie.Criteria;
import com.my.movie.review.ReviewService;
import com.my.movie.review.ReviewVO;

@Service("reviewService")
public class ReviewServiceImpl implements ReviewService{
	@Autowired
	private ReviewDAO reviewDAO;

	//���� ���ó��
	@Override
	public void insertReview(ReviewVO vo) {
		reviewDAO.insertReview(vo);
	}
	//��ȭ���� ����ѹ� 1�� �ʱ�ȭ
	@Override
	public List<ReviewVO> chkFirstReview(String m_code) {
		return reviewDAO.chkFirstReview(m_code);
	}
	
	
	//������ ���. ����¡���� �۾�
	@Override
	public List<ReviewVO> getReviewList(Criteria cri) {
		return reviewDAO.getReviewList(cri);
	}
	//����¡���� �۾�
	@Override
	public int getTotal(Criteria cri) {
		return reviewDAO.getReviewCount(cri);
	}
	
	
	//������ ���
	@Override
	public List<ReviewVO> getReviewList(ReviewVO vo) {
		return reviewDAO.getReviewList(vo);
	}
	///�ش� ��ȭ ��������
	@Override
	public float getReviewAvg(String m_code) {
		return reviewDAO.getReviewTotal(m_code);
	}
	
	//���� ����
	@Override
	public void deleteReview(ReviewVO vo) {
		reviewDAO.deleteReview(vo);
	}
	
	//���� ������ ���� �缳���� ���� �۾�
	@Override
	public int getReviewListFromDelete(ReviewVO vo) {
		return reviewDAO.getReviewListFromDelete(vo);
	}
	@Override
	public void reNameReview(ReviewVO vo) {
		reviewDAO.reNameReview(vo);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	

	

}
