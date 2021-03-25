package com.springbook.biz.boardService;

import java.util.List;

import com.springbook.biz.board.EstimationVO;

public interface EstimationService {

	// CRUD 硫�����
	// insert
	void insertEstimation(EstimationVO vo);

	// update
	void updateEstimation(EstimationVO vo);

	// delete
	void deleteEstimation(EstimationVO vo);

	EstimationVO getEstimation(EstimationVO vo);

	List<EstimationVO> getMemberEstimationList(EstimationVO vo);
	
	List<EstimationVO> getEstimationList(EstimationVO vo);

	EstimationVO getEstimationDetail(EstimationVO vo);

	void insertEstimationMemo(EstimationVO vo);

	void updateEstimationConfirm(EstimationVO vo);
}
