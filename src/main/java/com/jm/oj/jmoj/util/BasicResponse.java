package com.jm.oj.jmoj.util;


public class BasicResponse {
    private Boolean success;
    private Object data;

    public void setData(Object data) {
        this.data = data;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Boolean getSuccess() {
        return success;
    }

    public Object getData() {
        return data;
    }

    public static com.jm.oj.jmoj.util.BasicResponse create(Boolean success, Object data) {
        com.jm.oj.jmoj.util.BasicResponse res = new com.jm.oj.jmoj.util.BasicResponse();
        res.setSuccess(success);
        res.setData(data);
        return res;
    }

    public static com.jm.oj.jmoj.util.BasicResponse create(Object data) {
        return com.jm.oj.jmoj.util.BasicResponse.create(true, data);
    }
}
