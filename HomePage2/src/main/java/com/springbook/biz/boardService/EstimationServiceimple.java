package com.springbook.biz.boardService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.springbook.biz.board.EstimationVO;

@Service("estimationService")
public class EstimationServiceimple implements EstimationService {

	@Autowired
	private EstimationDAO estimationDAO;

	@Override
	public void insertEstimation(EstimationVO vo) {
		estimationDAO.insertEstimation(vo);
	}

	@Override
	public void updateEstimation(EstimationVO vo) {
		estimationDAO.updateEstimation(vo);
	}

	@Override
	public void deleteEstimation(EstimationVO vo) {
		estimationDAO.deleteEstimation(vo);
	}

	@Override
	public EstimationVO getEstimation(EstimationVO vo) {
		return estimationDAO.getEstimation(vo);
	}

	@Override
	public List<EstimationVO> getMemberEstimationList(EstimationVO vo) {
		return estimationDAO.getMemberEstimationList(vo);
	}

	@Override
	public List<EstimationVO> getEstimationList(EstimationVO vo) {
		return estimationDAO.getEstimationList(vo);
	}

	@Override
	public EstimationVO getEstimationDetail(EstimationVO vo) {
		return estimationDAO.getEstimationDetail(vo);
	}

	@Override
	public void insertEstimationMemo(EstimationVO vo) {
		estimationDAO.insertEstimationMemo(vo);
		
	}

	@Override
	public void updateEstimationConfirm(EstimationVO vo) {
		estimationDAO.updateEstimationConfirm(vo);
		
	}

}
