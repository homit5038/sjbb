package com.xqx.frame.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import com.xqx.frame.model.TEmploye;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.xqx.frame.dao.TKindergartenDao;
import com.xqx.frame.model.TChildren;
import com.xqx.frame.model.TKindergarten;
import com.xqx.frame.model.TUser;
import com.xqx.frame.model.dto.TKindergartenQueryObject;
import com.xqx.frame.security.SecurityUtil;
import com.xqx.frame.util.Validator;
@Service
public class KindergartenServiceImpl implements KindergartenService {
	@Autowired
	TKindergartenDao kindergartenDao;
	@Override
	public String saveKindergarten(TKindergarten kindergarten) {
		TUser user = (TUser) SecurityUtil.getCurrentUser();
		int it=user.getKindergarten().size();
		if (kindergarten != null) {
			TKindergarten kindergartened =kindergartenDao.findByKindergartenById(kindergarten.getId());
					/*if(kindergartened!= null) {
						BeanUtils.copyProperties(kindergartened, kindergarten);	
					}*/

            kindergarten.setFisSubKingdergarten(kindergarten.getFisSubKingdergarten()==null?"0":kindergarten.getFisSubKingdergarten());
		    if(kindergarten.getCreateUserID()==null&&!Validator.isNull(kindergartened)){
				kindergarten.setCreateUserID(kindergartened.getId());
			}
			kindergarten.getUser().add(user);
			kindergartenDao.save(kindergarten);
			return "ok";
		}else{
			return "error";
		}
	}

	@Override
	public Page<TKindergarten> findAll(Pageable pageable) {
		return kindergartenDao.findAll(pageable);

	}


	@Override
	public TKindergarten findKindergartenById(long id) {
		
		//kindergartenDao.findByKindergartenById(id);
		return kindergartenDao.findByKindergartenById(id);
	}

	@Override
	public List<TKindergarten> findKindergartenByName(String name) {
/*		
		List<TKindergarten> kindergarten;
		if (name != null) {
		     kindergarten= kindergartenDao.findByKindergartename(name);
			
		}else{
			 kindergarten = kindergartenDao.findAll();
		}
	
		return kindergarten;*/
		
		return null;
		//return kindergartenDao.findAll();
	}

	@Override
	public Page<TKindergarten> findAll(Pageable pageable,final TKindergartenQueryObject queryObject) {
		
		TUser user = (TUser) SecurityUtil.getCurrentUser();
		 Page<TKindergarten> page = kindergartenDao.findAll(new Specification<TKindergarten>() {
	            @Override
	            public Predicate toPredicate(Root<TKindergarten> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
	                List<Predicate> predicates = new ArrayList<Predicate>();
	            
	                if (!Validator.isNull(user)&&(user.getId()!=1)){
	                	 predicates.add(builder.and(builder.equal(root.get("createUserID").as(String.class), user.getId())));
	                }
	          
					if (queryObject.getKindergartenname()!= null && !"".equals(queryObject.getKindergartenname().trim())) {
	                    predicates.add(builder.and(builder.like(root.get("Kindergartenname").as(String.class), "%" + queryObject.getKindergartenname().trim() + "%")));
	                }
					
					if (queryObject.getLoginName() != null && !"".equals(queryObject.getLoginName().trim())) {
						Join<TKindergarten, TUser> statusJoin = root.join(root.getModel().getSingularAttribute("id", TUser.class), JoinType.LEFT);
						predicates.add(builder.and(builder.equal(statusJoin.get("loginName").as(String.class), queryObject.getLoginName())));
						
					}
				    
	                return query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
	            }
	        }, pageable);
		
		  return page;
	}

	@Override
	public void deleteTKindergarten(long id) {
	
		TKindergarten kindergart = kindergartenDao.findByKindergartenById(id);
		kindergart.getUser().remove(kindergart);
		kindergartenDao.delete(kindergart);
	}

}
