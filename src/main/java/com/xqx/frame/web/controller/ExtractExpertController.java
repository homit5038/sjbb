package com.xqx.frame.web.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xqx.frame.exception.ParameterCheckException;
import com.xqx.frame.model.TExpert;
import com.xqx.frame.model.TExpertBatch;
import com.xqx.frame.service.ExpertBatchService;
import com.xqx.frame.service.ExtractExpertService;

/**
 * @date 2017-03-14
 * 
 * @Description 批次抽取Controller
 */

@Controller
@RequestMapping("/extract")
public class ExtractExpertController {

	@Autowired
	private ExpertBatchService expertBatchService;
	
	@Autowired
	private ExtractExpertService extractExpertService;
	

	/**
	 * 抽取页面
	 * 
	 * @param id
	 * @param m
	 * @return
	 */
	@RequestMapping(value = "/{id}/init", method = RequestMethod.GET)
	public String init(@PathVariable Long id, Model m) {
		TExpertBatch batch = expertBatchService.findExpertBatchById(id);
		m.addAttribute("batch", batch);
		return "extract/extractManage";
	}
	
	/**
	 * 通过批次ID，获取该批次初始信息
	 * 
	 * @param id
	 * @param m
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/{id}/initExtractExpert", method = RequestMethod.POST)
	public ModelAndView initExtractExpert(@PathVariable Long id, Model m) {
		ModelAndView view = new ModelAndView("/extract/extractExpert");
		TExpertBatch batch = expertBatchService.findExpertBatchById(id);
		m.addAttribute("batch", batch);
		m.addAttribute("msg", true);
		m.addAttribute("experts", batch.getExperts());
		return view;
	}
	
	/**
	 * 抽取专家
	 * 
	 * @param id
	 * @param m
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/{id}/randomExtractExpert", method = RequestMethod.POST)
	public ModelAndView randomExtractExpert(@PathVariable Long id,Integer extractPepleNum, Model m) {
		ModelAndView view = new ModelAndView("/extract/extractExpert");
		Map<String, Object> result = extractExpertService.randomExtractExpert(id, extractPepleNum);
		TExpertBatch batch = expertBatchService.findExpertBatchById(id);
		m.addAttribute("batch", batch);
		m.addAttribute("msg", result.get("msg"));
		m.addAttribute("experts", result.get("experts"));
		return view;
	}
	
	/**
	 * 抽取专家
	 * 
	 * @param id
	 * @param expertId
	 * @param expertIds
	 * @param expertFlag
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/{id}/reelectExpert", method = RequestMethod.POST)
	public TExpert reelectExpert(@PathVariable Long id, Long expertId, String expertIds, Integer expertFlag) {
		TExpert expert = extractExpertService.reelectExpert(id, expertId, expertIds, expertFlag);
		return expert;
	}
	
	/**
	 * 抽取页面
	 * 
	 * @param id
	 * @param m
	 * @return
	 * @throws ParameterCheckException 
	 */
	@RequestMapping(value = "/{id}/confirmExtract", method = RequestMethod.GET)
	public String confirmExtract(@PathVariable Long id, Model m) throws ParameterCheckException {
		extractExpertService.confirmExtract(id);
		return "redirect:/extract/"+id+"/init";
	}

	
	@RequestMapping("{type}/statistics")
	public String extractStatistics(HttpServletRequest request,Model m,@PathVariable("type") String type){
		Map<String, Object> map = extractExpertService.extractStatistics(type);
		m.addAttribute("map", map);
		m.addAttribute("type", type);
		return "/extract/extractStatistics";
	}

	
	/**
	 * 抽取专家
	 * 
	 * @param id
	 * @param expertId
	 * @param expertIds
	 * @param expertFlag
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/{id}/updateExtractExpertNoteStatus", method = RequestMethod.POST)
	public boolean updateExtractExpertNoteStatus(@PathVariable Long id, String expertIds) {
		boolean expert = extractExpertService.updateExtractExpertNoteStatus(id, expertIds);
		return expert;
	}

	/**
	 * 验证抽取的专家是否大于专家库数量
	 * 
	 * @param extractPepleNum
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/validateExtractPepleNum", method = RequestMethod.POST)
	public Map<String,Object> validateExtractPepleNum(Integer extractPepleNum) {
		return extractExpertService.validateExtractPepleNum(extractPepleNum);
	}
}
