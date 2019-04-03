package com.xqx.frame.service;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.xqx.frame.dao.TchargeItemDao;
import com.xqx.frame.model.TChargeItem;
import com.xqx.frame.model.TGrade;




@Service
public class ChargeItemServiceImpl implements ChargeItemService {
	@Autowired
	TchargeItemDao chargeItemDao;
	@Override
	public String saveChargeItem(TChargeItem chargeitem) {

		if(chargeitem!= null) {
			chargeItemDao.save(chargeitem);
			return "ok";
		}else {
			return "error";
			
		}
	
	}

	
	@Override
	public TChargeItem findChargeitemById(Long id) {
		return chargeItemDao.findOne(id);
	}

	@Override
	public List<TChargeItem>findChargeitemByName(String name) {
		return chargeItemDao.findByChargeItemname(name);
	}
	
	


	@Override
	public void deleteChargeitem(Long id) {
		TChargeItem chargeItem=chargeItemDao.findOne(id);
		chargeItemDao.delete(chargeItem);
        

	}

	


	@Override
	public Page<TChargeItem> findAll(final String name,Pageable pageable) {
		
		return chargeItemDao.findAll(new Specification<TChargeItem>() {
			
			@Override
			public Predicate toPredicate(Root<TChargeItem> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				
                List<Predicate> predicates = new ArrayList<Predicate>();
                
                if(!StringUtils.isEmpty(name)){  
                    predicates.add(cb.equal(root.get("ItemName"), name));  
                }  
                
                query.where(predicates.toArray(new Predicate[predicates.size()]));
                return null;  
            }
		}, pageable);
	}
	
	
	
	
}
