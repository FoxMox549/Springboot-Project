package com.wms.common;

import lombok.Data;
import org.springframework.web.servlet.tags.EditorAwareTag;

@Data
public class Result {

    private int code; // code 100/200
    private String msg; // success/fail
    private Long total; // total records
    private Object data; // data

    public static Result fail() {
        return result(200, "fail", 0L, null);
    }

    public static Result suc() {
        return result(100, "success", 0L, null);
    }

    public static Result suc(Object data) {
        return result(100, "success", 0L, data);
    }

    public static Result suc(Object data, Long total) {
        return result(100, "success", total, data);
    }

    private static Result result(int code, String msg, Long total, Object data) {
        Result res = new Result();
        res.setData(data);
        res.setMsg(msg);
        res.setCode(code);
        res.setTotal(total);

        return res;
    }

}
