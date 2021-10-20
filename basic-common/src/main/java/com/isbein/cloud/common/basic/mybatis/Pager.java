package com.isbein.cloud.common.basic.mybatis;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

public class Pager<T> {

    /**
     * 总条数
     */
    @JSONField
    private long total;
    /**
     * 页码
     */
    @JSONField(ordinal = 10)
    private int pageNum = 1;
    /**
     * 每页大小
     */
    @JSONField(ordinal = 20)
    private int pageSize = 10;
    /**
     * 列表数据
     */
    @JSONField(ordinal = 30)
    private List<T> list;
    /**
     * 观察者模式，绑定filter
     */
    private final PageFilter pageFilter;

    public Pager(PageFilter pageFilter){
        this.pageNum = pageFilter.getPageNum();
        this.pageSize = pageFilter.getPageSize();
        if (pageSize > 0){
            pageNum = pageNum <= 0?1:pageNum;
        }
        this.pageFilter = pageFilter;
        this.pageFilter.setPageNum(pageNum);
    }

    private Pager(){
        this.pageFilter = new PageFilter(){};
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        pageSize = pageSize <= 0?10:pageSize;
        pageNum = pageNum <= 0?1:pageNum;
        this.total = total;
        int maxPageNum = (int)Math.round(Math.ceil(total * 1.0d / pageSize));
        pageNum = Math.min(pageNum, maxPageNum);
        pageNum = pageNum > 0?pageNum:1;
        this.pageFilter.setPageNum(pageNum);
        this.pageFilter.setPageSize(pageSize);
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }


    public static class Builder<T2>{
        private long total;
        private int pageNum = 1;
        private int pageSize = 10;
        private List<T2> list;

        public Builder<T2> setTotal(long total) {
            this.total = total;
            return this;
        }

        public long getTotal() {
            return total;
        }

        public int getPageNum() {
            return pageNum;
        }

        public Builder<T2> setPageNum(int pageNum) {
            this.pageNum = pageNum;
            return this;
        }

        public int getPageSize() {
            return pageSize;
        }

        public Builder<T2> setPageSize(int pageSize) {
            this.pageSize = pageSize;
            return this;
        }

        public List<T2> getList() {
            return list;
        }

        public Builder<T2> setList(List<T2> list) {
            this.list = list;
            return this;
        }

        public Pager<T2> build(){
            Pager<T2> pager = new Pager<>();
            pager.setList(list);
            pager.setPageNum(pageNum);
            pager.setPageSize(pageSize);
            pager.setTotal(total);
            return pager;
        }
    }
}
