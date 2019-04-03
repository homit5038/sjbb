package com.xqx.frame.form;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author yao
 */
public class BaseController<T> {
	
	/**
	 * 分页接口
	 * @param page
	 * @param limit
	 * @return
	 */
	 public JSONObject pageList(Integer page,Integer limit,List<T> objList){
		 	limit=limit>objList.size()?objList.size():limit;
		 	objList = page*limit>objList.size()?objList.subList((page-1)*limit,objList.size()):objList.subList((page-1)*limit,page*limit);
	 		JSONArray ss=JSONArray.fromObject(objList);
			JSONObject json = new JSONObject();
			json.put("code",0);
			json.put("msg","");
			json.put("count",objList.size());
			json.put("data",ss);
			return json;
		 	 
	 }
}
