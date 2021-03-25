package com.springbook.biz.boardService;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.springbook.biz.board.EstimationVO;
import com.springbook.biz.entry.MemberVO;

@Repository("estimationDAO")
@EnableTransactionManagement
public class EstimationDAO {

	@PersistenceContext
	private EntityManager em;

	@Transactional
	public void insertEstimation(EstimationVO vo) {
		System.out.println("jpa vo : " + vo);
		em.persist(vo);
	}

	@Transactional
	public void updateEstimation(EstimationVO vo) {
		em.merge(em.find(EstimationVO.class, vo.getSeq()));
	}

	@Transactional
	public void deleteEstimation(EstimationVO vo) {
		em.remove(em.find(EstimationVO.class, vo.getSeq()));
	}

	
	public EstimationVO getEstimation(EstimationVO vo) {
		return em.find(EstimationVO.class, vo.getSeq());
	}

	@Transactional
	public List<EstimationVO> getMemberEstimationList(EstimationVO vo) {
		System.out.println(vo.getId());
		TypedQuery<EstimationVO> query = em.createQuery("SELECT e FROM EstimationVO e WHERE e.id=:id",
				EstimationVO.class);
		List<EstimationVO> list = query.setParameter("id", vo.getId()).getResultList();
		System.out.println("쿼리결과:" + list);
		return list;
		/*String jpql = "from EstimationVO e WHERE e.id=:id order by e.seq desc";
		List<EstimationVO> list = em.createQuery(jpql).getResultList();
		System.out.println("쿼리 결과 : " + list + list.size());
		return list;*/
		/*
		 * TypedQuery<EstimationVO>
		 * query=em.createQuery("SELECT e FROM EstimationVO e WHERE e.id",
		 * EstimationVO.class); = query.setParameter("id", vo.getId()).getResultList();
		 * System.out.println("쿼리 결과 : "+ estimation); return estimation;
		 */
	}

	@Transactional
	public List<EstimationVO> getEstimationList(EstimationVO vo) {
		String jpql = "from EstimationVO e order by e.seq desc";
		List<EstimationVO> list = em.createQuery(jpql).getResultList();
		System.out.println(list.size());
		return list;
	}

	public EstimationVO getEstimationDetail(EstimationVO vo) {
		TypedQuery<EstimationVO> query = em.createQuery("SELECT e FROM EstimationVO e WHERE e.seq = :seq",
				EstimationVO.class);
		EstimationVO evo = query.setParameter("seq", vo.getSeq()).getSingleResult();
		System.out.println("member to find with id:" + evo);
		return evo;
	}

	@Transactional
	public void insertEstimationMemo(EstimationVO vo) {
		System.out.println("insertMemo:"+vo);
		EstimationVO evo=em.find(EstimationVO.class, vo.getSeq());
		evo.setMemo(vo.getMemo());
		System.out.println(evo);
		em.merge(evo);
		
	}
	
	@Transactional
	public void updateEstimationConfirm(EstimationVO vo) {
		EstimationVO evo=em.find(EstimationVO.class, vo.getSeq());
		evo.setPurchase(vo.getPurchase());
		em.merge(evo);
	}
}
