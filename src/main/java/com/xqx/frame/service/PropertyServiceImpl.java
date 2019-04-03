package com.xqx.frame.service;
import com.xqx.frame.dao.PropertyDao;
import com.xqx.frame.model.TGrade;
import com.xqx.frame.model.TProperty;
import com.xqx.frame.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Service
public class PropertyServiceImpl implements PropertyService{
	@Autowired
	PropertyDao propertyDao;

	@Override
	public List<TProperty> getByFidAndName(Long fid,String fname){
		return propertyDao.getByFidAndName(fid,fname);
	}
	
	@Override
	public List<TProperty> getByFid(Long fid){
		return propertyDao.getByFid(fid);
	}
	
	@Override
	public List<TProperty> getByFidAndRemak(Long fid,String fRemak){
		return propertyDao.getByFidAndRemark(fid, fRemak);
	}
	
	@Override
	public TProperty getById(Long id){
		return propertyDao.findOne(id);
	}

    @Override
    public String save(TProperty entity) {
    	
    	if (entity != null) {
    		propertyDao.save(entity);
			return "ok";
		}else{
			return "error";
		}
    	
       
    }

    /* (non-Javadoc)
	 * @see com.xqx.fdczzxt.service.PropertyService#findOne(java.lang.Long)
	 */
	@Override
	public TProperty findOne(Long id) {

		return propertyDao.findOne(id);
	}

	@Override
	public List<TProperty> findByLikeFid(Long typeId) {
		if (typeId == null) {
			return null;
		}
		return propertyDao.findByLikeFid(typeId, typeId + "%");
	}
	
	public List<TProperty> getByRemark(String string){
		return propertyDao.getByRemark(string);
	}
	
	

	@Override
	public Page<TProperty> findAll(final String fname,Pageable pageable) {
		
		return propertyDao.findAll(new Specification<TProperty>() {
			
			@Override
			public Predicate toPredicate(Root<TProperty> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				
                List<Predicate> predicates = new ArrayList<Predicate>();
                
                if(!StringUtils.isEmpty(fname)){
                	
                	 if(PubUtil.isNumerics(fname)){
                		  predicates.add(cb.equal(root.get("fId"), fname)); 
                		 
                	 }else {
                		  predicates.add(cb.like(root.<String> get("fValue"), "%" + fname + "%")); 
                		  
                	 }
                	
                     
                }  
                
                
                query.where(predicates.toArray(new Predicate[predicates.size()]));
                return null;  
            }
		}, pageable);
	}
	
	
    /**
     * 获取配置证书开头列表
     * @param fMemo
     * @param fId
     * @return
     */
    @Override
    public List<TProperty> configList(String fMemo, Long fId) {
        return propertyDao.configList(fId,fMemo);
    }
    
    
	@Override
	public void delete(long id) {
		TProperty grade = propertyDao.findOne(id);

		propertyDao.delete(grade);

	}
    
}
