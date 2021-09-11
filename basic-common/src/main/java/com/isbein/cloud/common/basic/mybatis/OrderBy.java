package com.isbein.cloud.common.basic.mybatis;

import java.util.ArrayList;
import java.util.List;

public class OrderBy {
    /**
     * 列
     */
    private String column;
    /**
     * 类型
     * ASC or DESC
     */
    private String type;

    public OrderBy(String column, String type) {
        this.column = column;
        this.type = type;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    /**
     * 链式构建器
     */
    public static class Builder {

        private final List<OrderBy> list = new ArrayList<>();

        public Builder add(String column, String type) {
            list.add(new OrderBy(column, type));
            return this;
        }

        public List<OrderBy> build() {
            return list;
        }
    }
}
