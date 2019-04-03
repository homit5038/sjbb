package com.xqx.frame.form;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;

/**
 * 分页查询条件
 * @author Administrator
 */
public class PageableQueryObject implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -1652368561720895709L;
    /**
     * 页数 默认第一页
     */
    private int page = 1;
    /**
     * 页容量
     */
    private int limit = Integer.valueOf("2");
    /**
     * 总页数
     */
    private int totalPages;

    public PageableQueryObject() {
    }

    public PageableQueryObject(int page, int pageSize) {
        this.page = page;
        this.limit = pageSize;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public Pageable getPageable() {
        if (this.page > 0 && this.limit > 0) {
            return new PageRequest(this.page - 1, this.limit);
        } else {
            return new PageRequest(0, this.limit);
        }
    }

}
