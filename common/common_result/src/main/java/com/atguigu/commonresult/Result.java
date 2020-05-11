package com.atguigu.commonresult;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Result {

    private boolean success;

    private Integer status;

    private String message;

    private Map<String, Object> data = new HashMap<>();

    /**
     * 私有化构造方法
     */
    private Result() {}

    /**
     * 返回this, 链式编程
     */
    public static Result ok() {
        Result result = new Result();
        result.setSuccess(true);
        result.setMessage("成功");
        result.setStatus(ReturnCode.SUCCESS);
        return result;
    }

    public static Result error() {
        Result result = new Result();
        result.setSuccess(false);
        result.setMessage("失败");
        result.setStatus(ReturnCode.ERROR);
        return result;
    }

    public Result success(boolean success) {
        this.setSuccess(success);
        return this;
    }

    public Result message(String message) {
        this.setMessage(message);
        return this;
    }

    public Result status(Integer status) {
        this.setStatus(status);
        return this;
    }

    public Result data(String key, Object value) {
        this.data.put(key, value);
        return this;
    }
    public Result data(Map<String, Object> data) {
        this.setData(data);
        return this;
    }




}
