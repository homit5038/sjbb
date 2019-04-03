package com.xqx.frame.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.xqx.frame.dao.SystemLogDao;
import com.xqx.frame.dao.TExpertDao;
import com.xqx.frame.exception.ParameterCheckException;
import com.xqx.frame.model.SystemLog;
import com.xqx.frame.model.TExpert;
import com.xqx.frame.util.ExcelUtil;

@Service
public class ExpertServiceImpl implements ExpertService{

	@Autowired
	TExpertDao expertDao;

	@Autowired
	HttpServletRequest request;
	
	@Autowired
	SystemLogDao logDao;
	
	@Override
	public String saveExpert(TExpert expert) throws ParameterCheckException {
		if (expert != null) {
			// 新建的专家
			if (expert.getId() == null) {
				// 判断专家是否存在
				List<TExpert> expExists = expertDao.findByAll(expert.getExpertName(),
						expert.getAssessmentStructure(), expert.getExpertEmail(), 
						expert.getPhoneNum());
				if (expExists != null && expExists.size()>0) {
					return ("exist");
				}
				expert.createAuditInfo();
				//日志记录
				SystemLog log = new SystemLog(null, null, "新增专家信息，专家姓名："+expert.getExpertName()+"，专家所属机构："+expert.getAssessmentStructure()+"；");
				logDao.save(log);
			} else {
				// 修改已存在的专家
				TExpert expExist = expertDao.findOne(expert.getId());
				expert.setCreateTime(expExist.getCreateTime());
				expert.setCreateUserID(expExist.getCreateUserID());
				expert.setCreatorIp(expExist.getCreatorIp());
				expert.updateAuditInfo();
				//日志记录
				SystemLog log = new SystemLog(null, null, "修改专家信息，专家姓名："+expert.getExpertName()+"，专家所属机构："+expert.getAssessmentStructure()+"；");
				logDao.save(log);
			}
			expertDao.save(expert);
			return "ok";
		}else{
			return "error";
		}
	}
		

	@Override
	public TExpert findExpertById(long id) {
		return expertDao.findOne(id);
	}

	@Override
	public List<TExpert> findExpertByName(String name) {
		return expertDao.findByName(name);
	}
	
	@Override
	public List<TExpert> findExpertByAll(String name, String pgjg,
			String email, String phone) {
		return expertDao.findByAll(name, pgjg, email, phone);
	}

	@Override
	public Page<TExpert> findAll(final String name,final String pgjg,final String email,final String phone,
			final String yxx,final String flag,final String beginTime, final String endTime,Pageable pageable) {
		
		return expertDao.findAll(new Specification<TExpert>() {
			
			@Override
			public Predicate toPredicate(Root<TExpert> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<Predicate>();
                if(!StringUtils.isEmpty(yxx)){
    				if ("1".equals(yxx)) {
    					// 有效
    					predicates.add(cb.equal(root.<Integer> get("availability"), 1));
    				}else if ("0".equals(yxx)) {
    					predicates.add(cb.equal(root.<Integer> get("availability"), 0));
    				} 
                }else{
                	predicates.add(cb.equal(root.<Integer> get("availability"), 1));
                }
                if(!StringUtils.isEmpty(name)){  
                    predicates.add(cb.equal(root.get("expertName"), name));  
                }  
                if(!StringUtils.isEmpty(pgjg)){  
                    predicates.add(cb.equal(root.get("assessmentStructure"), pgjg));  
                } 
                if(!StringUtils.isEmpty(email)){  
                    predicates.add(cb.equal(root.get("expertEmail"), email));  
                }
                if(!StringUtils.isEmpty(flag)){  
                    predicates.add(cb.equal(root.get("flag"), flag));  
                }
                if(!StringUtils.isEmpty(phone)){  
                    predicates.add(cb.equal(root.get("phone"), phone));  
                }
                if (!StringUtils.isEmpty(beginTime)
						&& !StringUtils.isEmpty(endTime)) {
					SimpleDateFormat sdf = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss");
					try {
						predicates.add(cb.between(root.<Date> get("createTime"),
										sdf.parse(beginTime+" 00:00:00"),
										sdf.parse(endTime+" 23:59:59")));
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
                query.where(predicates.toArray(new Predicate[predicates.size()]));
                return null;  
            }
		}, pageable);
	}

	@Override
	public List<TExpert> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteExpert(long id) {
		TExpert expert = expertDao.findOne(id);
		expert.setAvailability(0);
		//日志记录
		SystemLog log = new SystemLog(null, null, "删除专家信息，专家姓名："+expert.getExpertName()+"，专家所属机构："+expert.getAssessmentStructure()+"；");
		logDao.save(log);
		expertDao.save(expert);
	}

	@Override
	public Map<String, Object> impData(MultipartFile multipartFile)throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		List<SystemLog> logs = new ArrayList<SystemLog>();
		String message = "";
		if (!multipartFile.isEmpty()) { // 文件不为空
			Workbook hssfWorkbook = null;
			try {
				hssfWorkbook = new XSSFWorkbook(multipartFile.getInputStream()); // 获得工作簿对象
			} catch (Exception ex) {
				hssfWorkbook = new HSSFWorkbook(multipartFile.getInputStream());
			}

			List<TExpert> expertList = new ArrayList<TExpert>();//成功导入专家
			List<TExpert> repExpert = new ArrayList<TExpert>();//重复专家
			for(int sheetNum = 0;sheetNum<hssfWorkbook.getNumberOfSheets();sheetNum++){
				Sheet hssfSheet = hssfWorkbook.getSheetAt(sheetNum);
				if(hssfSheet==null){
					continue;
				}
				for(int rowNum=1;rowNum<=hssfSheet.getLastRowNum();rowNum++){
					Row hssfRow = hssfSheet.getRow(rowNum);
					if(hssfRow==null){
						continue;
					}
					//判断上传的文件格式是否正确
					if(hssfRow!=null && rowNum == 1){
						Row hssfRowHead = hssfSheet.getRow(1);//表头
						String firstRowXM = ExcelUtil.getValue(hssfRowHead.getCell(1)).replace(" ", "");
						String firstRowPGJG = ExcelUtil.getValue(hssfRowHead.getCell(2)).replace(" ", "");
						String firstRowEmail = ExcelUtil.getValue(hssfRowHead.getCell(3)).replace(" ", "");
						String firstRowPhone = ExcelUtil.getValue(hssfRowHead.getCell(4)).replace(" ", "");
						String firstRowFlag = ExcelUtil.getValue(hssfRowHead.getCell(5)).replace(" ", "");
						if (!"专家姓名".equals(firstRowXM)
								|| !"评估机构".equals(firstRowPGJG)
								|| !"电子邮箱".equals(firstRowEmail)
								|| !"电话".equals(firstRowPhone)
								|| !"专家抽取类型".equals(firstRowFlag)) {
							message = "FormatErro";
							map.put("message", message);
							return map;
						}
					}

					String XM = ExcelUtil.getValue(hssfRow.getCell(1));
					String PGJG = ExcelUtil.getValue(hssfRow.getCell(2));
					if (StringUtils.isEmpty(XM) && StringUtils.isEmpty(PGJG) || rowNum == 1) {
						continue;
					}

					map = getExcelContent(hssfWorkbook, hssfRow);
					map.put("rowNum", String.valueOf(rowNum + 1));
					message = (String) map.get("message");
					if ("ok".equals(message)) {
						String name = (String) map.get("name");
						String pgjg = (String) map.get("PGJG");
						String email = (String) map.get("email");
						String phone = (String) map.get("phone");
						String flag = (String) map.get("flag");
						List<TExpert> oexpert = expertDao.findByAll(name,pgjg,email,phone);
						if(oexpert.isEmpty()&&rowNum>1){//库中不存在该专家时，导入改专家，否则跳过
							TExpert expert = new TExpert();
							expert.setExpertName(name);
							expert.setExpertEmail(email);
							expert.setAssessmentStructure(pgjg);
							expert.setPhoneNum(phone);
							expert.setFlag(Integer.valueOf(flag));
							expert.setAvailability(1);
							expertList.add(expert);
							// 日志记录
							SystemLog log = new SystemLog(null, null, "导入专家信息操作，专家姓名："+name+"，专家所属机构："+PGJG+";");
							logs.add(log);
						} else {
							repExpert.addAll(oexpert);
						}
					} else {
						return map;
					}
				}
			}
			//保存专家信息
			if(!expertList.isEmpty()){
				expertDao.save(expertList);
				map.put("expertList", expertList);
			}
			//保存专家信息
			if(!repExpert.isEmpty()){
				map.put("repExpert", repExpert);
			}
			if(!logs.isEmpty()){
				logDao.save(logs);
			}
			if (map.isEmpty()) {
				map.put("message", "isEmpty");
				return map;
			} else {
				return map;
			}

		} else {
			// 文件为空时，返回一个message
			map.put("message", "isEmpty");
			return map;
		}
	}

	public Map<String, Object> getExcelContent(Workbook hssfWorkbook,Row hssfRow){
		// 信息
		String message = "ok";
		String cellNum = "";

		Map<String, Object> map = new HashMap<String, Object>();
		String name = ExcelUtil.getValue(hssfRow.getCell(1));
		map.put("name", name);
		if (StringUtils.isEmpty(name)) { // 姓名
			message = "noname";
			cellNum = "1";
			map.put("message", message);
			map.put("cellNum", cellNum);
			return map;
		}

		String PGJG = ExcelUtil.getValue(hssfRow.getCell(2));
		map.put("PGJG", PGJG);
		if (StringUtils.isEmpty(PGJG)) { // 评估机构
			message = "noPGJG";
			cellNum = "2";
			map.put("message", message);
			map.put("cellNum", cellNum);
			return map;
		}

		String email = ExcelUtil.getValue(hssfRow.getCell(3));
		map.put("email", email);
		if (!StringUtils.isEmpty(email) && !email.matches("^[A-Za-z0-9]{1,40}@[A-Za-z0-9]{1,40}\\.[A-Za-z]{2,3}$")) { // 电子邮箱
			message = "emailError";
			cellNum = "3";
			map.put("message", message);
			map.put("cellNum", cellNum);
			return map;
		}

		String phone = ExcelUtil.getValue(hssfRow.getCell(4));//手机号码
		map.put("phone", phone);
		if (!StringUtils.isEmpty(phone) && !phone.matches("^[0-9]{1,14}$")) { // 电子邮箱
			message = "phoneError";
			cellNum = "4";
			map.put("message", message);
			map.put("cellNum", cellNum);
			return map;
		}
		
		String flag = ExcelUtil.getValue(hssfRow.getCell(5));//抽取类型
		if("不可选".equals(flag)){
			flag="-1";
		}else if("必选".equals(flag)){
			flag="1";
		}else{
			flag="0";
		}
		map.put("flag", flag);
		
		map.put("message", message);
		map.put("cellNum", cellNum);
		return map;
	}
}
