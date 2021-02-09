package com.xqx.frame.model.query;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.criteria.Predicate;

import com.xqx.frame.model.TClasses;
import com.xqx.frame.model.TGrade;
import com.xqx.frame.util.DateUtil;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;
import com.slyak.spring.jpa.TemplateQueryObject;
import com.xqx.frame.form.PageableQueryObject;
import com.xqx.frame.model.TChildren;


/**
 * 区域查询对象
 *
 * @author Chengjun
 */
@TemplateQueryObject
public class childrenQuery extends PageableQueryObject {
	private static final long serialVersionUID = 1L;
	protected Long id;
	private String childName;
	private String childSchoolId;
	private String selectDate;

	private String childGradeId;
	private String childClassId;






	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getChildName() {
		return childName;
	}

	public void setChildName(String childName) {
		this.childName = childName;
	}

	public String getJobNumber() {
		return childSchoolId;
	}
	public void setJobNumber(String childSchoolId) {
		this.childSchoolId = childSchoolId;
	}

	public String getSelectDate() {
		return selectDate;
	}

	public void setSelectDate(String selectDate) {
		this.selectDate = selectDate;
	}

	public String getChildGradeId() {
		return childGradeId;
	}

	public void setChildGradeId(String childGradeId) {
		this.childGradeId = childGradeId;
	}

	public String getChildClassId() {
		return childClassId;
	}

	public void setChildClassId(String childClassId) {
		this.childClassId = childClassId;
	}

	public Specification<TChildren> getSpecification() {
		return (root, criteriaQuery, cb) -> {
			List<Predicate> restricts = new ArrayList<>();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


			if(("1").equals(selectDate)) {

				try {
					restricts.add(cb.equal(root.<Date>get("childBirthday"),
							sdf.parse(sdf.format(DateUtil.getDayBegin()))));

				} catch (ParseException e) {
					e.printStackTrace();
				}
			}


			if(("2").equals(selectDate)) {

				try {
					restricts.add(cb.between(root.<Date> get("childBirthday"),
							sdf.parse(sdf.format(DateUtil.getBeginDayOfYesterday())),
							sdf.parse(sdf.format(DateUtil.getEndDayOfYesterDay()))));



				} catch (ParseException e) {
					e.printStackTrace();
				}
			}


			if(("3").equals(selectDate)) {

				try {
					restricts.add(cb.equal(root.<Date>get("childBirthday"),
							sdf.parse(sdf.format(DateUtil.getNextDay(DateUtil.getDayBegin(),2)))));

				} catch (ParseException e) {
					e.printStackTrace();
				}
			}

			if(("4").equals(selectDate)) {

				try {
					restricts.add(cb.between(root.<Date> get("childBirthday"),
							sdf.parse(sdf.format(DateUtil.getBeginDayOfWeek())),
							sdf.parse(sdf.format(DateUtil.getEndDayOfWeek()))));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}


			if(("5").equals(selectDate)) {

				try {
					restricts.add(cb.between(root.<Date> get("childBirthday"),
							sdf.parse(sdf.format(DateUtil.getBeginDayOfMonth())),
							sdf.parse(sdf.format(DateUtil.getEndDayOfMonth()))));

				} catch (ParseException e) {
					e.printStackTrace();
				}
			}













			if (StringUtils.hasText(childGradeId)) {
				restricts.add(cb.equal(root.<TGrade>get("grade").<Long>get("id"), childGradeId.trim()));
			}

			if (StringUtils.hasText(childClassId)) {
				restricts.add(cb.equal(root.<TClasses>get("classe").<Long>get("id"),childClassId.trim()));
			}

			if (StringUtils.hasText(childName)) {
				restricts.add(cb.like(root.get("childName"), "%" + childName.trim() + "%"));
			}
			if (StringUtils.hasText(childSchoolId)) {
				restricts.add(cb.like(root.get("childSchoolId"), "%" + childSchoolId.trim() + "%"));
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
