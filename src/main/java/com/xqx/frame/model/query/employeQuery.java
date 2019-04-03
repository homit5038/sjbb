package com.xqx.frame.model.query;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import com.slyak.spring.jpa.TemplateQueryObject;
import com.xqx.frame.form.PageableQueryObject;
import com.xqx.frame.model.TEmploye;
import com.xqx.frame.model.TUser;
import com.xqx.frame.security.SecurityUtil;


/**
 * 区域查询对象
 * 用来查询{@link  com.xqx.yszjjg.entity.po.Company}实体
 *
 * @author Chengjun
 */
@TemplateQueryObject
public class employeQuery extends PageableQueryObject {
	private static final long serialVersionUID = 1L;
	protected Long id;

	private String employeName;

	private String jobNumber;




	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getEmployeName() {
		return employeName;
	}


	public void setEmployeName(String employeName) {
		this.employeName = employeName;
	}

	public String getJobNumber() {
		return jobNumber;
	}


	public void setJobNumber(String jobNumber) {
		this.jobNumber = jobNumber;
	}



	
	public Specification<TEmploye> getSpecification() {
		return (root, criteriaQuery, cb) -> {
			List<Predicate> restricts = new ArrayList<>();
			if (StringUtils.hasText(employeName)) {
				restricts.add(cb.like(root.get("employeName"), "%" + employeName.trim() + "%"));
			}
			if (StringUtils.hasText(jobNumber)) {
				restricts.add(cb.like(root.get("jobNumber"), "%" + jobNumber.trim() + "%"));
			}
			if (id != null) {
				restricts.add(cb.equal(root.get("id"), id));
			}
			
			Predicate[] pre = new Predicate[restricts.size()];
			criteriaQuery.where(restricts.toArray(pre));
			return criteriaQuery.getRestriction();
		};
	}
}
