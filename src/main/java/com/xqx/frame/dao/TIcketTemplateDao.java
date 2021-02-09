package com.xqx.frame.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;


import com.xqx.frame.model.TIcketTemplate;


@Repository
public interface TIcketTemplateDao extends JpaRepository<TIcketTemplate, Long> {




}
