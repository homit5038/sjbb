package com.xqx.frame.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.xqx.frame.model.TGrade;
import com.xqx.frame.model.TProperty;

@Repository
public interface PropertyDao extends JpaRepository<TProperty,Long> {

	@Query("from TProperty p where p.fId = :fid and p.fName = :fname  order by p.fSort ASC")
	public List<TProperty> getByFidAndName(@Param("fid")Long fid,@Param("fname")String fname);
	
	@Query("from TProperty p where p.fId = :fid order by p.fSort ASC")
	public List<TProperty> getByFid(@Param("fid")Long fid);
	
	@Query("from TProperty p where p.fId = :fid and p.id not in:ids order by p.fSort ASC")
	public List<TProperty> getByFidAndIdNotIn(@Param("fid")Long fid,@Param("ids")List<Long> ids);
	
	@Query("from TProperty p where p.fId = :fid and p.fRemak = :remark order by p.fSort ASC")
	public List<TProperty> getByFidAndRemark(@Param("fid")Long fid,@Param("remark")String remark);
	
	Page<TProperty> findAll(Specification<TProperty> spec,Pageable page);
	/**
	 * 根据等级注册类型的fRemak，获取等级注册类型
	 * 不要做其他用，切记
	 * @param remark
	 * @return
	 */
	@Query("from TProperty p where p.fRemak = :remark order by p.fSort ASC")
	List<TProperty> getByRemark(@Param("remark")String remark);

	@Query("from TProperty p where p.fMemo in ('BG','BZ') and p.fId like '16%'")
	List<TProperty> findByLikeFidAndStatus();

	@Query("from TProperty p where p.id=:fId or CONVERT(varchar(10), p.fId) like :typeId order by p.fSort ASC")
	List<TProperty> findByLikeFid(@Param("fId")Long fId, @Param("typeId")String typeId);

	@Query("from TProperty p where p.fId = :fid and p.fRemak in :remarks order by p.fSort ASC")
	List<TProperty> getByFidAndInRemark(@Param("fid")Long fid, @Param("remarks")List<String> remarks);

    /**
     * 获取配置证书开头列表
     * @param fId
     * @param fMemo
     * @return
     */
    @Query("from TProperty p where p.fId = :fId and p.fMemo= :fMemo order by p.fSort ASC")
	List<TProperty> configList(@Param("fId") Long fId,@Param("fMemo")String fMemo);
}
