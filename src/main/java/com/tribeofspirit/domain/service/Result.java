package com.tribeofspirit.domain.service;


import com.tribeofspirit.common.hibernate.Page;

import java.io.Serializable;
import java.util.List;

/**
 * User: Lewis Wang
 * Date: 8/15/11
 * Time: 10:29 AM
 */
public class Result<T> implements Serializable {

    private boolean success = true;

    private T data;

    private List<T> list;

    private Page<T> page;

    private String msg;

    private Result() {
    }

    private Result(String msg) {
        this.msg = msg;
    }

    private Result(T data) {
        this.data = data;
    }

    private Result(List<T> list) {
        this.list = list;
    }

    private Result(Page<T> page) {
        this.page = page;
    }

    private Result(T data, String msg) {
        this.data = data;
        this.msg = msg;
    }

    private Result(List<T> list, String msg) {
        this.list = list;
        this.msg = msg;
    }

    private Result(Page<T> page, String msg) {
        this.page = page;
        this.msg = msg;
    }

    private Result(boolean success, String msg) {
        this.success = false;
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public T getValue() {
        return data;
    }

    public List<T> getList() {
        return list;
    }

    public Page<T> getPage() {
        return page;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMsg() {
        return msg;
    }

    public static <T> Result<T> success() {
        return new Result<T>();
    }

    public static <T> Result<T> success(String msg) {
        return new Result<T>(msg);
    }

    public static <T> Result<T> success(Page<T> page) {
        return new Result<T>(page);
    }

    public static <T> Result<T> success(List<T> list) {
        return new Result<T>(list);
    }

    public static <T> Result<T> success(T t) {
        return new Result<T>(t);
    }

    public static <T> Result<T> success(Page<T> page, String msg) {
        return new Result<T>(page, msg);
    }

    public static <T> Result<T> success(List<T> list, String msg) {
        return new Result<T>(list, msg);
    }

    public static <T> Result<T> success(T t, String msg) {
        return new Result<T>(t, msg);
    }

    public static <T> Result<T> failure(String msg) {
        return new Result<T>(false, msg);
    }
}
