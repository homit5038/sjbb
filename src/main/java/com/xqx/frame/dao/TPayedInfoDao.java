package com.xqx.frame.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xqx.frame.model.TPayedInfo;

@Repository
public interface TPayedInfoDao extends JpaRepository<TPayedInfo, Long>  {

}
