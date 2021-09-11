package com.isbein.cloud.common.basic.mybatis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Criteria<T> {


    private static final String OP_EQUAL = "eq";

    private static final String OP_NOT_EQUAL = "neq";

    private static final String OP_LIKE = "lk";

    private static final String OP_NOT_LIKE = "nlk";

    private static final String OP_BETWEEN = "btw";

    private static final String OP_NOT_BETWEEN = "nbtw";

    private static final String OP_IN = "in";

    private static final String OP_NOT_IN = "nin";

    private static final String OP_IS_NULL = "isnull";

    private static final String OP_NOT_IS_NULL = "nisnull";

    private static final String OP_GREAT_THAN = "gt";

    private static final String OP_LESS_THAN = "lt";

    private static final String OP_GREAT_THAN_OR_EQUAL = "egt";

    private static final String OP_LESS_THAN_OR_EQUAL = "elt";

    /**
     * 操作符
     */
    private String op;
    /**
     * 操作值
     */
    private List<T> val;

    public Criteria(String op, List<T> val) {
        this.op = op;
        this.val = val;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public List<T> getVal() {
        return val;
    }

    public void setVal(List<T> val) {
        this.val = val;
    }

    /**
     * 等于
     * @param value     值
     * @param <T>       T
     * @return
     */
    public static <T> Criteria<T> equal(T value){
        List<T> a = new ArrayList<>();
        a.add(value);
        return new Criteria<>(OP_EQUAL,a);
    }


    public static <T> Criteria<T> notEqual(T value){
        List<T> a = new ArrayList<>();
        a.add(value);
        return new Criteria<>(OP_NOT_EQUAL,a);
    }


    @SafeVarargs
    public static <T> Criteria<T> in(T ...array){
        List<T> aa = new ArrayList<>(array.length);
        aa.addAll(Arrays.asList(array));
        return new Criteria<>(OP_IN,aa);
    }

    public static <T> Criteria<T> in(List<T> list){
        return new Criteria<>(OP_IN,list);
    }

    @SafeVarargs
    public static <T> Criteria<T> notIn(T ...array){
        List<T> aa = new ArrayList<>(array.length);
        aa.addAll(Arrays.asList(array));
        return new Criteria<>(OP_NOT_IN,aa);
    }

    public static <T> Criteria<T> notIn(List<T> list){
        return new Criteria<>(OP_NOT_IN,list);
    }

    public static Criteria<String> like(String value){
        List<String> aa = new ArrayList<>(1);
        aa.add(value);
        return new Criteria<>(OP_LIKE,aa);
    }

    public static <T> Criteria<T> greatThan(T value){
        List<T> aa = new ArrayList<>(1);
        aa.add(value);
        return new Criteria<>(OP_GREAT_THAN,aa);
    }

    public static <T> Criteria<T> lessThan(T value){
        List<T> aa = new ArrayList<>(1);
        aa.add(value);
        return new Criteria<>(OP_LESS_THAN,aa);
    }

    public static <T> Criteria<T> greatThanOrEqual(T value){
        List<T> aa = new ArrayList<>(1);
        aa.add(value);
        return new Criteria<>(OP_GREAT_THAN_OR_EQUAL,aa);
    }

    public static <T> Criteria<T> lessThanOrEqual(T value){
        List<T> aa = new ArrayList<>(1);
        aa.add(value);
        return new Criteria<>(OP_LESS_THAN_OR_EQUAL,aa);
    }


    public static <T> Criteria<T> between(T minVal,T maxVal){
        List<T> aa = new ArrayList<>(2);
        aa.add(minVal);
        aa.add(maxVal);
        return new Criteria<>(OP_BETWEEN,aa);
    }
}
