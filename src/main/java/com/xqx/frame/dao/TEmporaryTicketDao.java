package com.xqx.frame.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.xqx.frame.model.TEmporaryTicket;


@Repository
public interface TEmporaryTicketDao extends JpaRepository<TEmporaryTicket, Long> {


	/**
	 * 根据专家姓名查询
	 * @param name
	 * @return
	 */
	@Query("from TEmporaryTicket where templates.id = ?1")
	List<TEmporaryTicket> findTEmporaryTicketByTemplateid(Long templateid);
	
	 @Transactional
	 @Modifying
	 @Query("delete from TEmporaryTicket t where t.templates.id = ?")
	 int deleteByTEmporaryTicketTemplatesId(Long id);
	
	
	/**
	 * 根据专家姓名查询
	 * @param name
	 * @return
	 */
	@Query("from TEmporaryTicket where name = ?1")
	List<TEmporaryTicket> findByChargeItemname(String name);

}
