package com.isbein.cloud.common.basic.mybatis;

public abstract class PageFilter extends OrderFilter {
    /**
     * 页码
     */
    private int pageNum = 1;
    /**
     * 每页条数
     */
    private int pageSize = 10;
    /**
     * limit
     * 自身计算，不用set
     */
    private int limit = 10;
    /**
     * offset
     * 自身计算，不用set
     */
    private int offset = 0;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
        reCalculate();
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
        reCalculate();
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }


    private void reCalculate(){
        this.limit = this.pageSize;
        if (this.pageSize > 0){
            this.pageNum = this.pageNum > 0?this.pageNum : 1;
            this.offset = (this.pageNum - 1) * this.limit;
        }
    }
}