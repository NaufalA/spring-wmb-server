package com.warungmakanbahari.warungmakanbahari.shared.dtos;

import org.springframework.data.domain.Page;

public class PagedResponse<T> {
    private Integer page;
    private Integer size;
    private Iterable<T> data;
    private Integer count;
    private Integer totalPage;
    private Long totalCount;

    public PagedResponse() {
    }

    public PagedResponse(Page<T> pagedResult) {
        this.page = pagedResult.getNumber();
        this.size = pagedResult.getSize();
        this.count = pagedResult.getNumberOfElements();
        this.totalCount = pagedResult.getTotalElements();
        this.totalPage = pagedResult.getTotalPages();
        this.data = pagedResult.getContent();
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Iterable<T> getData() {
        return data;
    }

    public void setData(Iterable<T> data) {
        this.data = data;
    }
}
