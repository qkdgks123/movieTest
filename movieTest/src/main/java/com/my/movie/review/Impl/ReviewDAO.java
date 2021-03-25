package com.my.movie.review.Impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.my.movie.movie.Criteria;
import com.my.movie.movie.MovieVO;
import com.my.movie.review.ReviewVO;


@Repository("ReviewDAO")
public class ReviewDAO {
	@Autowired
	private SqlSessionTemplate mybatis;
		
	// ���� ���
	public void insertReview(ReviewVO vo) {
		mybatis.insert("review.insertReview", vo);
	}
	//��ȭ���� ����ѹ� 1�� �ʱ�ȭ
	public List<ReviewVO> chkFirstReview(String m_code) {
		return mybatis.selectList("review.chkFirstReview", m_code);
	}

	
	//������ ���, ����¡���� �۾�
	public List<ReviewVO> getReviewList(Criteria cri) {
		return mybatis.selectList("review.getListWithPaging", cri);
	}
	//����¡���� �۾�
	public int getReviewCount(Criteria cri) {
		return mybatis.selectOne("review.getTotalCount", cri);
	}
	
	
	// ������ ���
	public List<ReviewVO> getReviewList(ReviewVO vo) {
		return mybatis.selectList("review.getReviewList", vo);
	}
	//�ش� ��ȭ ��������
	public float getReviewTotal(String m_code) {
		return mybatis.selectOne("review.getReviewAvg", m_code);
	}
	
	//���� ����
	public void deleteReview(ReviewVO vo) {
		mybatis.delete("review.deleteReview", vo);
	}
	
	//���� ������ ���� �缳���� ���� �۾�
	public int getReviewListFromDelete(ReviewVO vo) {
		return mybatis.selectOne("review.getReviewListFromDelete", vo);
		
	}
	public void reNameReview(ReviewVO vo) {
		mybatis.update("review.reNameReview", vo);
		
	}

	
	
	

	
}
