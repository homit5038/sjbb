package com.xqx.frame.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xqx.frame.dao.TPayedInfoDao;
import com.xqx.frame.model.TPayedInfo;
@Service
public class TPayedInfoServiceImpl implements TPayedInfoService {
	@Autowired
	TPayedInfoDao payedinfoDao;
	
	@Override
	public List<TPayedInfo> findTPayedInfoByid(Long childId) {
	
		return  payedinfoDao.findPayedInfoBychildId(childId);
	}

	public List<TPayedInfo> findTPayedInfoByupdateUserID(Long UserID) {
		
		return  payedinfoDao.findPayedInfoByUserID(UserID);
	}

}
