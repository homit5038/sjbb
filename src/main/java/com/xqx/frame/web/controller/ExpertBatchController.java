package com.xqx.frame.web.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.xqx.frame.exception.ParameterCheckException;
import com.xqx.frame.model.TExpert;
import com.xqx.frame.model.TExpertBatch;
import com.xqx.frame.model.TUser;
import com.xqx.frame.security.SecurityUtil;
import com.xqx.frame.service.ExpertBatchService;
import com.xqx.frame.util.ExcelUtil;
import com.xqx.frame.util.PubUtil;

/**
 * @date 2017-03-14
 * 
 * @Description 批次管理Controller
 */

@Controller
@SessionAttributes({ "batch"})
@RequestMapping("/batch")
public class ExpertBatchController {

	@Autowired
	private ExpertBatchService expertBatchService;
	
	/**
	 * 初始化新增批次
	 * 
	 * @param m
	 * @return
	 */
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String initCreatForm(Model m) {
		TExpertBatch batch = new TExpertBatch();
		TUser user = (TUser) SecurityUtil.getCurrentUserDetials();
		String dateStr = PubUtil.dateToString(new Date(), "yyyy年MM月dd日 HH时mm分ss秒");
		batch.setBatchName(user.getName()+" "+dateStr); //初始化批次名称为 当前登录用户家时间
		batch.setAvailability(1);
		m.addAttribute("batch", batch);
		return "batch/createOrUpdate";
	}

	/**
	 * 处理新增批次
	 * 
	 * @param batch
	 * @param bind
	 * @param status
	 * @param m
	 * @return
	 * @throws ParameterCheckException
	 */
	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public String processCreatForm(@ModelAttribute("batch") @Valid TExpertBatch batch,
			BindingResult bind, SessionStatus status, Model m)
			throws ParameterCheckException {
		if (bind.hasErrors()) {
			return "batch/createOrUpdate";
		}
		expertBatchService.saveExpertBatch(batch);
		status.setComplete();
		return "extract/extractManage";
	}

	/**
	 * 删除批次
	 * 
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/{id}/del", method = RequestMethod.POST)
	public String delExpertBatch(@PathVariable Long id) {
		expertBatchService.deleteExpertBatch(id);
		return "ok";
	}

	/**
	 * 初始化修改批次
	 * 
	 * @param id
	 * @param m
	 * @return
	 */
	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public String initUpdateForm(@PathVariable Long id, Model m) {
		TExpertBatch batch = expertBatchService.findExpertBatchById(id);
		m.addAttribute("batch", batch);
		return "batch/createOrUpdate";
	}

	/**
	 * 处理修改批次
	 * 
	 * @param batch
	 * @param bind
	 * @param status
	 * @return
	 * @throws ParameterCheckException
	 */
	@RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
	public String processUpdateForm(@ModelAttribute("batch") @Valid TExpertBatch batch,
			BindingResult bind, Model m)
			throws ParameterCheckException {
		if (bind.hasErrors()) {
			return "batch/createOrUpdate";
		}
		expertBatchService.saveExpertBatch(batch);
		m.addAttribute("batch", batch);
		m.addAttribute("result", true);
		return "extract/extractManage";
	}
	
	/**
	 * 批次列表
	 * 
	 * @param m
	 * @return
	 */
	@RequestMapping(value = "/batchlist", method = RequestMethod.GET)
	public String batchlist(Model m) {
		Map<String, Object> map = expertBatchService.findBatch();
		m.addAttribute("map", map);
		m.addAttribute("method", "get");
		return "batch/list";
	}

	/**
	 * 批次查询
	 * 
	 * @param p
	 * @param availability
	 * @param batchName
	 * @param batchStatus
	 * @param beginTime
	 * @param endTime
	 * @param m
	 * @return
	 */
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String search(@PageableDefault(page = 0, size = 10, sort = { "updateLastTime" }, direction = Direction.DESC) Pageable p,
			String availability, String batchName,
			String batchStatus, String beginTime, String endTime, Model m) {
		Map<String, Object> map = expertBatchService.findBatch();
		m.addAttribute("map", map);
		m.addAttribute("method", "post");
		return "batch/list";
	}
	
	@RequestMapping(value="{id}/exportExpertBatch")
	public void exportExpertBatch(@PathVariable("id") long id,
			HttpServletRequest request,HttpServletResponse response) throws IOException{
		TExpertBatch batch = expertBatchService.findExpertBatchById(id);
		List<TExpert> expertList = batch.getExperts();
		List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
		Map<String, Object> sheetNameMap = new HashMap<String, Object>();
		String sheetName = "批次：（"+batch.getBatchName()+"），专家数：（"
							+batch.getExtractPepleNum()+"），抽取时间：（"
							+batch.getCreateTime()+"）";
		sheetNameMap.put("sheetName", sheetName);
		listmap.add(sheetNameMap);
		Map<String, Object> tmap = null;
		for (TExpert expert : expertList) {
			tmap = new HashMap<String, Object>();
			tmap.put("name", expert.getExpertName());
			tmap.put("pgjg", expert.getAssessmentStructure());
			tmap.put("email", expert.getExpertEmail());
			tmap.put("phone", expert.getPhoneNum());
			listmap.add(tmap);
		}
		String columnNames[] = { "专家姓名","评估机构", "电子邮箱","电话"};
		String keys[] = { "name", "pgjg", "email","phone"};
		String fileName = "抽取结果" ;//+ new DateTime().toString("yyyyMMddHHmmsssss");
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		try {
			ExcelUtil.createWorkBookWithSheetName(listmap, keys, columnNames)
					.write(os);
		} catch (IOException e) {
			e.printStackTrace();
		}
		byte[] content = os.toByteArray();
		InputStream is = new ByteArrayInputStream(content);
		// 设置response参数，可以打开下载页面
		response.reset();
		response.setContentType("application/vnd.ms-excel;charset=utf-8");
		response.setHeader("Content-Disposition", "attachment;filename="
				+ new String((fileName + ".xls").getBytes(), "iso-8859-1"));
		ServletOutputStream out = response.getOutputStream();
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			bis = new BufferedInputStream(is);
			bos = new BufferedOutputStream(out);
			byte[] buff = new byte[2048];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
		} catch (final IOException e) {
			throw e;
		} finally {
			if (bis != null)
				bis.close();
			if (bos != null)
				bos.close();
		}
		return;
	}
}
