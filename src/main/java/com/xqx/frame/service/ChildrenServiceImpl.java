package com.xqx.frame.service;

import com.xqx.frame.dao.SysSequenceDao;
import com.xqx.frame.dao.TChildrenDao;
import com.xqx.frame.exception.ParameterCheckException;
import com.xqx.frame.form.ChildrenQueryVO;
import com.xqx.frame.model.TChildren;
import com.xqx.frame.model.TClasses;
import com.xqx.frame.model.TGrade;
import com.xqx.frame.model.TUser;
import com.xqx.frame.model.query.childrenQuery;
import com.xqx.frame.security.SecurityUtil;
import com.xqx.frame.util.DateUtil;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.ByteArrayOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;



@Service
public class ChildrenServiceImpl implements ChildrenService {
	@Resource
	private SysSequenceDao seqDao;
	@Autowired
	TChildrenDao childrenDao;
	@Autowired
	FileService fileservice;
	@Autowired
	UserManager userManager;
	@Autowired 
	EntityManagerFactory entityManagerFactory;
	@Override
	public String saveChildren(TChildren children) throws ParameterCheckException {

		if (children != null) {
			// 新建的专家
			String name =  SecurityContextHolder.getContext().getAuthentication().getName();
			TUser user = userManager.findTUserByLoginName(name);


			//TChildren childrens = new TChildren(user);

			//children.createAuditInfo();
			childrenDao.save(children);

			
			
			return "ok";
		}else{
			return "error";
		}
		
	}

	@Override
	public TChildren findChildrenById(long id) {
		return childrenDao.findBychildrenById(id);
	}

	
	@Override
	public ChildrenQueryVO findChildrensById(long id) {
		TChildren childrenpo=childrenDao.findOne(id);
		ChildrenQueryVO children=new ChildrenQueryVO();
		children.setChildName(childrenpo.getChildName());
		children.setClasse(childrenpo.getClasse().toString());
		children.setGrade(String.valueOf(childrenpo.getGrade().getId()));
		children.setChildBirthday(childrenpo.getChildBirthday());
		children.setChildInGartenDate(childrenpo.getChildInGartenDate());
		children.setChildSex(childrenpo.getChildSex());
		return children;
	}
	
	@Override
	public List<TChildren> findChildrenByName(String name) {
		
		if(!StringUtils.isEmpty(name)){  
		   return childrenDao.findBychildrenname(name);
		}else {
		   return childrenDao.findAll();
		}
	}
	@Override
	public List<TChildren> findBychildrennamejson(String name) {
		
		if(!StringUtils.isEmpty(name)){  
		   return childrenDao.findBychildrennamejson(name);
		}else {
		   return null;
		}
	}


	@Override
	public Page<TChildren> findAllt(childrenQuery query, Integer page, Integer size) {
		return childrenDao.findAll(query.getSpecification(), query.getPageable());
	}
	
	
	
	@Override
	public Page<TChildren> findAll(final String gid,Pageable pageable) {

		return childrenDao.findAll(new Specification<TChildren>() {
			@Override
			public Predicate toPredicate(Root<TChildren> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<Predicate>();
                TUser user = SecurityUtil.getCurrentUser();
                
           	 SimpleDateFormat sdf = new SimpleDateFormat(
   								"yyyy-MM-dd HH:mm:ss");
           	System.out.println("========5========================sss"+sdf.format(DateUtil.getBeginDayOfYesterday()));
   	         if(("1").equals(gid)) {
	        
					try {
						 predicates.add(cb.equal(root.<Date>get("childBirthday"), 
								 sdf.parse(sdf.format(DateUtil.getDayBegin()))));  
						
					} catch (ParseException e) {
						e.printStackTrace();
					} 
	         }   
                
                
		         if(("2").equals(gid)) {
		        	
						try {
							predicates.add(cb.between(root.<Date> get("childBirthday"),
									sdf.parse(sdf.format(DateUtil.getBeginDayOfYesterday())),
									sdf.parse(sdf.format(DateUtil.getEndDayOfYesterDay()))));
							
							
							
						} catch (ParseException e) {
							e.printStackTrace();
						} 
		         }       
                
		         
		         if(("3").equals(gid)) {
		
						try {
							 predicates.add(cb.equal(root.<Date>get("childBirthday"), 
									 sdf.parse(sdf.format(DateUtil.getNextDay(DateUtil.getDayBegin(),2)))));  
							
						} catch (ParseException e) {
							e.printStackTrace();
						} 
		         }      
		         
		         if(("4").equals(gid)) {
		
						try {
							predicates.add(cb.between(root.<Date> get("childBirthday"),		
							sdf.parse(sdf.format(DateUtil.getBeginDayOfWeek())),
							sdf.parse(sdf.format(DateUtil.getEndDayOfWeek()))));
						} catch (ParseException e) {
							e.printStackTrace();
						} 
		         }   
		         
		         
		         if(("5").equals(gid)) {
		     		
						try {
							predicates.add(cb.between(root.<Date> get("childBirthday"),
									sdf.parse(sdf.format(DateUtil.getBeginDayOfMonth())),
									sdf.parse(sdf.format(DateUtil.getEndDayOfMonth()))));
									
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
	public Page<TChildren> findAll(final String name,final String cid,final String gid,Pageable pageable) {

		return childrenDao.findAll(new Specification<TChildren>() {
			@Override
			public Predicate toPredicate(Root<TChildren> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<Predicate>();
                TUser user = SecurityUtil.getCurrentUser();
                if(!StringUtils.isEmpty(name)){  
                    predicates.add(cb.equal(root.get("childName"), name));  
                }
                
                if(!StringUtils.isEmpty(gid)){  
                    predicates.add(cb.equal(root.<TGrade>get("grade").<Long>get("id"), gid));  
                }  
                if(!StringUtils.isEmpty(cid)){  
                    predicates.add(cb.equal(root.<TClasses>get("classe").<Long>get("id"),cid));  
                
                }  
                
              //  if (user != null) {
                   // predicates.add(cb.equal(root.get("id"),user.getId() ));  
               // }  
             
                
             
                query.where(predicates.toArray(new Predicate[predicates.size()]));
                return null;  
            }
		}, pageable);
	}

	
	@Override
	public List<Map<String, Object>> Childrenstatistics() {
		String sql = "select t.childName, t.childBirthday, t.childInGartenDate, t.attendanceIdcardNumber, t.childIdcardNumber, t.birthPlace, t.childNation, t.parentName"
				+ " from TChildren t";
		Query query = entityManagerFactory.createEntityManager().createNativeQuery(sql);		

		query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<?> result = query.getResultList();
		List<Map<String, Object>> rows = (List<Map<String, Object>>) result;
		return rows;
	}
	
	
	@Override
	public ByteArrayOutputStream exportPersonnelsExcel(List<Map<String, Object>> planes) throws Exception {//获取数据
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("住宿信息");
		HSSFRow row = sheet.createRow(0);
		HSSFCell cell1 = row.createCell((short)0);
		cell1.setCellValue("序号");
		HSSFCell cell2 = row.createCell((short)1);
		cell2.setCellValue("报名号");
		HSSFCell cell3 = row.createCell((short)2);
		cell3.setCellValue("公司/个人名称");
		HSSFCell cell4 = row.createCell((short)3);
		cell4.setCellValue("社会统一信用代码");
		HSSFCell cell5 = row.createCell((short)4);
		cell5.setCellValue("航班号");
		HSSFCell cell6 = row.createCell((short)5);
		cell6.setCellValue("起飞时间");
		HSSFCell cell7 = row.createCell((short)6);
		cell7.setCellValue("到达时间");
		HSSFCell cell8 = row.createCell((short)7);
		cell8.setCellValue("人数");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		for (int i = 0; i < planes.size(); i++) {
			row = sheet.createRow(i + 1);
			cell1 = row.createCell((short)0);
			cell1.setCellValue(Integer.valueOf(i).toString());
			cell2 = row.createCell((short)1);
			cell2.setCellValue(planes.get(i).get("childName").toString());
			cell3 = row.createCell((short)2);
			cell3.setCellValue(planes.get(i).get("childBirthday").toString());
			cell4 = row.createCell((short)3);
			cell4.setCellValue(planes.get(i).get("attendanceIdcardNumber") == null ? "" : planes.get(i).get("attendanceIdcardNumber").toString());
			cell5 = row.createCell((short)4);
			cell5.setCellValue(planes.get(i).get("childIdcardNumber").toString());
			cell6 = row.createCell((short)5);
			cell6.setCellValue(sdf.format(planes.get(i).get("childInGartenDate")));
			cell7 = row.createCell((short)6);
			cell7.setCellValue(sdf.format(planes.get(i).get("childBirthday")));
			cell8 = row.createCell((short)7);
			cell8.setCellValue(planes.get(i).get("parentName") == null ? "==" : planes.get(i).get("parentName").toString());
			
		}
		wb.write(os);
		wb.close();
		return os;
	}
	
	
	
	
	
	
	@Override
	public List<TChildren> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TChildren> findChildrenByAll(String name, String idcardNumber, String email, String phone) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteChildren(long id) {
		
		TChildren children = childrenDao.findOne(id);
		childrenDao.delete(children);
	
		//childrenDao.deleteByTChildrenId(id);
		return true;

	}

/*	@Override
	public void deleteChildren(long id) {
		TChildren children = childrenDao.findOne(id);
		childrenDao.delete(children);
		//childrenDao.deleteByTChildrenId(id);

	}*/
    @Override
    @Transactional
    public String getSystemSequence(String name) {
        return seqDao.getSystemSeq(name);
    }

	
	@Override
	public Map<String, Object> impData(MultipartFile multipartFile) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
