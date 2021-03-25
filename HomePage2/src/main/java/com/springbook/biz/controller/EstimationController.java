package com.springbook.biz.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.springbook.biz.board.EstimationListVO;
import com.springbook.biz.board.EstimationVO;
import com.springbook.biz.boardService.EstimationService;
import com.springbook.biz.common.MailUtil;
import com.springbook.biz.entry.MemberVO;

@Controller
@SessionAttributes("estimation")
public class EstimationController {
	@Autowired
	private EstimationService estimationService;

	@RequestMapping("/dataTransform.do")
	@ResponseBody
	public List<EstimationVO> dataTransform(EstimationVO vo) {
		List<EstimationVO> estimationList = estimationService.getEstimationList(vo);
		return estimationList;
	}

	@RequestMapping("/dataTransformXml.do")
	@ResponseBody
	public EstimationListVO dataTransformXML(EstimationVO vo) {
		List<EstimationVO> estimationList = estimationService.getEstimationList(vo);
		EstimationListVO estimationListVO = new EstimationListVO();
		estimationListVO.setEstimationList(estimationList);
		return estimationListVO;
	}

	@RequestMapping(value = "/insertEstimation.do", method = RequestMethod.POST)
	public String insertEstimation(EstimationVO vo) {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		String sRegDate = sdf.format(date);
		vo.setRegDate(sRegDate);
		estimationService.insertEstimation(vo);
		return "redirect:/getMemberEstimationList.do";
	}

	@RequestMapping("/updateEstimation.do")
	public String updateEstimation(@ModelAttribute("estimation") EstimationVO vo) {
		estimationService.updateEstimation(vo);
		return "getEstimationList";
	}

	@RequestMapping("/deleteEstimation.do")
	public String deleteEstimation(EstimationVO vo) {
		return "getEstimation";
	}

	@RequestMapping("/getEstimation.do")
	public String getEstimation(EstimationVO vo, Model model) {
		model.addAttribute("estimation", estimationService.getEstimation(vo));
		return "admin_page";
	}

	@RequestMapping(value = "/getMemberEstimationList.do", method = RequestMethod.GET)
	public String getMemberEstimationList(EstimationVO vo, Model model,HttpSession session) {
		vo.setId(((MemberVO)session.getAttribute("member")).getId());
		model.addAttribute("memberEstimationList", estimationService.getMemberEstimationList(vo));
		return "member_page";
	}

	@RequestMapping(value = "/getEstimationList.do", method = RequestMethod.GET)
	public String getEstimationList(EstimationVO vo, Model model) {
		model.addAttribute("estimationList", estimationService.getEstimationList(vo));
		return "admin_page";
	}
	
	@RequestMapping(value = "/estimationdetail.do", method = RequestMethod.GET)
	public String getEstimationDetail(EstimationVO vo, Model model,HttpSession session) {
		    vo = estimationService.getEstimationDetail(vo);
		model.addAttribute("estimation", vo);
		return "estimationDetail_page";
	}
	@RequestMapping(value = "/insertEstimationMemo.do", method = RequestMethod.GET)
	public String insertEstimationMemo(EstimationVO vo, Model model,HttpSession session) {
		estimationService.insertEstimationMemo(vo);
		return "redirect:/estimationdetail.do?seq="+vo.getSeq();
	}
	
	@RequestMapping(value = "/estimationConfirm.do", method = RequestMethod.GET)
	public String estimationConfirm(EstimationVO vo, Model model,HttpSession session) {
		estimationService.updateEstimationConfirm(vo);
		return "redirect:/estimationdetail.do?seq="+vo.getSeq();
	}
	
	@RequestMapping(value = "/sendMail.do", method = RequestMethod.GET)
	public String sendMail(EstimationVO vo) {
		EstimationVO esVo=estimationService.getEstimation(vo);
		MailUtil mailutil=new MailUtil();
		mailutil.naverMailSend(esVo);
		return "redirect:/getEstimationList.do";
	}
	
	
	
}
