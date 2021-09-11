package com.isbein.cloud.common.basic.mybatis;

import java.util.List;

/**
 * 排序规则filter
 */
public abstract class OrderFilter {

    private List<OrderBy> orderBys;

    public List<OrderBy> getOrderBys() {
        return orderBys;
    }

    public void setOrderBys(List<OrderBy> orderBys) {
        this.orderBys = orderBys;
    }
}
