package com.xqx.frame.dao;


import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.xqx.frame.model.SysSequence;



@Repository
public interface SysSequenceDao extends CrudRepository<SysSequence, Long>{

/*	@Query("from SysSequence s where s.name=:name")
	SysSequence findByName(@Param("name")String name);*/
	
	@Procedure(name="getSystemSeq")
	String getSystemSeq(@Param("name")String name);
}
