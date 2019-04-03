package com.xqx.frame.tags;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import com.xqx.frame.dao.PropertyDao;
import com.xqx.frame.model.TProperty;


/**
 * 
 * @author Administrator
 * 
 */
public class PropertyTag extends TagSupport {

	private static final long serialVersionUID = 1L;
	private String name = null;// 返回的集合名称
	/*******以下是参数*******/
	private Long fId;//父ID
	
	private Long bId;
	private String remark;
	
	private String inRemark;
	private String fOperateType;
	private Long QAID;

	PropertyDao propertyDao = null;

	@Override
	public int doStartTag() throws JspException {
		ServletContext servletContext = pageContext.getServletContext();
		ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		propertyDao = (PropertyDao) ctx.getBean("propertyDao");
		return super.doStartTag();
	}

	@Override
	public int doEndTag() throws JspException {

		try {
			if(fId!=null&&fId!=0L){
				List<TProperty> proList = null;
				if(remark != null && !"".equals(remark)){
					proList = propertyDao.getByFidAndRemark(fId, remark);
				}else if(inRemark != null && !"".equals(inRemark)){
					proList = propertyDao.getByFidAndInRemark(fId, StringToList(inRemark));
				}else {
					if(!"SQ".equals(fOperateType)){
						proList = propertyDao.getByFid(fId);
						
					}
					
					
				}
	
				pageContext.getRequest().setAttribute(name,proList);
			}
			if(bId!=null&&bId!=0L){
				TProperty pro = propertyDao.findOne(bId);
				pageContext.getRequest().setAttribute(name,pro);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return EVAL_BODY_INCLUDE;
	}
	
	public List<String> StringToList(String str){
		List<String> strList = new ArrayList<String>();
		if (str == null || "".equals(str)) {
			return strList;
		}
		String[] arr = str.split(",");
		for(String st: arr) {
			strList.add(st);
		}
		return strList;
		
	}

	public String getInRemark() {
		return inRemark;
	}

	public void setInRemark(String inRemark) {
		this.inRemark = inRemark;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getfId() {
		return fId;
	}

	public void setfId(Long fId) {
		this.fId = fId;
	}

	public Long getbId() {
		return bId;
	}

	public void setbId(Long bId) {
		this.bId = bId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getfOperateType() {
		return fOperateType;
	}

	public void setfOperateType(String fOperateType) {
		this.fOperateType = fOperateType;
	}

	public Long getQAID() {
		return QAID;
	}

	public void setQAID(Long qAID) {
		QAID = qAID;
	}

}
